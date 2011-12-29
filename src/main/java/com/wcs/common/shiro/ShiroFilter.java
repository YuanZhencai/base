package com.wcs.common.shiro;


import java.lang.reflect.Field;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

public class ShiroFilter extends AbstractShiroFilter {

    /**
     * Configures this instance based on the existing {@link org.apache.shiro.web.env.WebEnvironment} instance
     * available to the currently accessible {@link #getServletContext() servletContext}.
     *
     * @see org.apache.shiro.web.env.EnvironmentLoaderListener
     * @since 1.2
     */
	
	BeanManager beanManager;
	
    @Override
    public void init() throws Exception {
    	 ServletContext ctx = filterConfig.getServletContext();
    	 beanManager = (BeanManager) ctx
    	    .getAttribute("javax.enterprise.inject.spi.BeanManager");
    	 
    	  for (Field field : this.getClass().getDeclaredFields()) {
    	   if (field.isAnnotationPresent(Inject.class)) {
    	    try {
    	     field.set(this, getBean(field.getType()));
    	    } catch (IllegalArgumentException e) {
    	     throw new ServletException("Can't inject into "
    	       + field.getName(), e);
    	    } catch (IllegalAccessException e) {
    	     throw new ServletException("Can't inject into "
    	       + field.getName(), e);
    	    }
    	   }
    	  }
    	  
    	ShiroDbRealm shiroDbRealm = this.getBean(ShiroDbRealm.class);
    	WebSecurityManager securityManager = new DefaultWebSecurityManager(shiroDbRealm);

        setSecurityManager(securityManager);

//        FilterChainResolver resolver = env.getFilterChainResolver();
//        if (resolver != null) {
//            setFilterChainResolver(resolver);
//        }
    }
    
    private <T> T getBean(Class<? extends T> clazz) {
  	  @SuppressWarnings("unchecked")
  	  Bean<T> bean = (Bean<T>) beanManager.getBeans(clazz).iterator().next();
  	  CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
  	  @SuppressWarnings("unchecked")
  	  T obj = (T) beanManager.getReference(bean, clazz, ctx);
  	  return obj;
  	 }

}