<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<% String rptnam = (String)session.getAttribute("reportName"); %>
<% Object reportSource = session.getAttribute("reportSource"); %>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="crviewer" %>
<crviewer:viewer reportSourceType="reportingComponent" viewerName="<%= rptnam %>" reportSourceVar="reportSource" isOwnPage="true">
	<crviewer:report reportName="<%= rptnam %>" />
</crviewer:viewer>