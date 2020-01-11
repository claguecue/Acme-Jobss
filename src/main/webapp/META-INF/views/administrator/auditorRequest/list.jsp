<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.auditorRequest.list.label.firm" path="firm" width="30%"/>
	<acme:list-column code="administrator.auditorRequest.list.label.responsibilityStatement" path="statement" width="50%"/>
	<acme:list-column code="administrator.auditorRequest.list.label.status" path="status" width="20%"/>
</acme:list>