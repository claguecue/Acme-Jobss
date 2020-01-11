<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="auditor.audit.list.label.title" path="title" width="35%"/>
	<acme:list-column code="auditor.audit.list.label.body" path="body" width="65%"/>
</acme:list>