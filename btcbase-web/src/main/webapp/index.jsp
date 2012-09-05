<%
	Object obj = session.getAttribute("currentUser");
	
	if (obj==null) {
		System.out.println("===============login");
		response.sendRedirect(request.getContextPath()+"/faces/login.xhtml");
	} else {
		System.out.println("===============main");
		response.sendRedirect(request.getContextPath()+"/faces/main.xhtml");
	}
%>
