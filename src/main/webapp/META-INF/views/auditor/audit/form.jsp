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
	<acme:form-textbox code="auditor.audit.form.label.title" path="title"/>
	<acme:form-textbox code="auditor.audit.form.label.body" path="body"/>
	<jstl:if test="${command != 'create' }">
		<acme:form-moment code="auditor.audit.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-checkbox code="auditor.audit.form.label.status" path="status"/>
	
	
	<acme:form-hidden path="id"/>
	<acme:form-submit test="${command == 'show' }" code="auditor.audit.form.button.update" action="/auditor/audit/update"/>
	<acme:form-submit test="${command == 'show' }" code="auditor.audit.form.button.delete" action="/auditor/audit/delete"/>
	<acme:form-submit test="${command == 'create' }" code="auditor.audit.form.button.create" action="/auditor/audit/create"/> 
	<acme:form-submit test="${command == 'update' }" code="auditor.audit.form.button.update" action="/auditor/audit/update"/>
	<acme:form-submit test="${command == 'delete' }" code="auditor.audit.form.button.delete" action="/auditor/audit/delete"/>
	
	<acme:form-return code="auditor.audit.form.button.return"/>
</acme:form>


