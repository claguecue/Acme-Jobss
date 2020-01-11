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
	<jstl:if test="${finalMode == false && command == 'create'}">
		<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMPX-JOBX"/>
		<acme:form-textbox code="employer.job.form.label.title" path="title"/>
		<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
		<acme:form-money code="employer.job.form.label.salary" path="salary"/>
		<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
		<acme:form-textarea code="employer.job.form.label.description" path="description"/>
		<acme:form-hidden path="finalMode"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false && command != 'create'}">
		<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMPX-JOBX"/>
		<acme:form-textbox code="employer.job.form.label.title" path="title"/>
		<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
		<acme:form-money code="employer.job.form.label.salary" path="salary"/>
		<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
		<acme:form-textarea code="employer.job.form.label.description" path="description"/>
		<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode"/>
	</jstl:if>

	<jstl:if test="${finalMode == true }">
		<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMPX-JOBX" readonly="true"/>
		<acme:form-textbox code="employer.job.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="employer.job.form.label.deadline" path="deadline" readonly="true"/>
		<acme:form-money code="employer.job.form.label.salary" path="salary" readonly="true"/>
		<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" readonly="true"/>
		<acme:form-textarea code="employer.job.form.label.description" path="description" readonly="true"/>
		<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	

	<acme:form-hidden path="id"/>
	
	<acme:form-submit test="${command != 'create' && listOremEmpty}" code="employer.job.form.label.orem.create" action="/employer/orem/create?id=${id}" method="get"/>
	<acme:form-submit test="${command != 'create' && !listOremEmpty}" code="employer.job.form.label.orem.show" action="/employer/orem/list?id=${id}" method="get"/>

	<acme:form-submit test="${command != 'create' && !listDutyEmpty}" code="employer.job.form.label.duties" action="/employer/duty/list?id=${id}" method="get"/>
	<acme:form-submit test="${command != 'create' }" code="employer.job.form.label.duties.create" action="/employer/duty/create?id=${id}" method="get"/>
	<acme:form-submit test="${command != 'create' && !listAuditEmpty}" code="employer.job.form.label.audit" action="/authenticated/audit/list?id=${id}" method="get"/>
	
	<acme:form-submit test="${command == 'show' && finalMode == false}" code="employer.job.form.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show' && listAppEmpty}" code="employer.job.form.button.delete" action="/employer/job/delete"/>
	
	<acme:form-submit test="${command == 'create' }" code="employer.job.form.button.create" action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update' }" code="employer.job.form.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete' }" code="employer.job.form.button.delete" action="/employer/job/delete"/>
	
	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>

