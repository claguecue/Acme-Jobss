<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="auditor.job.list.label.reference" path="reference" width="34%"/>
	<acme:list-column code="auditor.job.list.label.deadline" path="deadline" width="33%"/>
	<acme:list-column code="auditor.job.list.label.title" path="title" width="33%"/>
</acme:list>