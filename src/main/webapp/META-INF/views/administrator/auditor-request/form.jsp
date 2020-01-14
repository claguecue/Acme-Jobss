<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="administrator.auditor-request.contextData"/> <acme:print value="${username}"/></h4><br>
	<acme:form-textbox code="administrator.auditor-request.form.label.username" path="username" readonly="true"/>
	<acme:form-textbox code="administrator.auditor-request.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.auditor-request.form.label.responsibilityStatement" path="statement" readonly="true"/>
	
	<jstl:if test="${status == 'Pending'}">
		<acme:form-submit code="administrator.auditor-request.form.button.accept" action="/administrator/auditor-request/accept"/>
		<acme:form-submit code="administrator.auditor-request.form.button.reject" action="/administrator/auditor-request/reject"/> 
	</jstl:if>
	<acme:form-return code="administrator.auditor-request.form.button.return"/>
</acme:form>
