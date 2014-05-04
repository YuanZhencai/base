package com.wcs.autosave;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BrowserIdFilter implements Filter {
    public static String BROWSERID = "BROWSERID"; // cookie name
    public static int IDAGE = 3600 * 24 * 365 * 3; // three years

    public static String getBrowserId(HttpServletRequest httpRequest) {
        String browserId = null;
        Cookie cookies[] = httpRequest.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (BROWSERID.equals(cookies[i].getName())) {
                    browserId = cookies[i].getValue();
                    break;
                }
            }
        return browserId;
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (getBrowserId(httpRequest) == null) {
            // The BROWSERID cookie has not been found. This must be
            // the first time the user accesses the application.
            // Use the current session's ID as the value for
            // the BROWSERID cookie.
            HttpServletResponse httpResponse
                = (HttpServletResponse) response;
            String browserId = httpRequest.getSession().getId();
            Cookie browserCookie = new Cookie(BROWSERID, browserId);
            browserCookie.setMaxAge(IDAGE);
            browserCookie.setPath(httpRequest.getContextPath());
            httpResponse.addCookie(browserCookie);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
