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

<%@taglib prefix="jstl" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly ="false">
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber" readonly = "true"/>
	<acme:form-textarea code="employer.application.form.label.job" path="job" readonly = "true"/>
	<acme:form-textarea code="employer.application.form.label.worker" path="worker" readonly = "true"/>
	<acme:form-moment code="employer.application.form.label.creationMoment" path="creationMoment" readonly = "true"/>
	<acme:form-textbox code="employer.application.form.label.status" path="status" readonly = "true"/>
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly = "true"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly = "true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly = "true"/>
	
	<jstl:if test="${!isPending && command == 'show'}">
			<jstl:set var= "readOnly" value = "true"/>
	</jstl:if>
	
	<acme:form-textarea code="employer.application.form.label.rejectJustification" path="rejectJustification" readonly = "${readOnly}"/>
	
	<%-- Botones en la vista show --%>
	
	<acme:form-submit test="${(command == 'show' && isPending) || command == 'accept' || command == 'reject'}"		
		code="employer.job.form.button.accept"
		action="/employer/application/accept"/>
		
	<acme:form-submit test="${(command == 'show' && isPending) || command == 'accept' || command == 'reject'}"		
		code="employer.job.form.button.reject"
		action="/employer/application/reject"/>
		
	
	<acme:form-return code="employer.application.form.label.button.return"/>
</acme:form>