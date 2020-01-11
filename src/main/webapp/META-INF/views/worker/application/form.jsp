<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" />
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="worker.application.form.label.creationMoment" path="creationMoment" />
		<acme:form-textbox code="worker.application.form.label.status" path="status" />
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textbox code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />
	
	<jstl:if test="${ !listOremEmpty }">
		<acme:form-textarea code="worker.application.form.label.answer" path="answer" />
		
		<jstl:if test="${ !noHasPassword }">
			<jstl:if test="${command != 'create' }">
				<acme:form-password code="worker.application.form.label.marker" path="marker" />
			</jstl:if>
			<jstl:if test="${command == 'create' }">
				<acme:form-textbox code="worker.application.form.label.marker" path="marker" />
			</jstl:if>
			<acme:form-password code="worker.application.form.label.password" path="password" />
		</jstl:if>
		
		<jstl:if test="${ noHasPassword && hasMarker}">
			<acme:form-textbox code="worker.application.form.label.marker" path="marker" />
		</jstl:if>
	</jstl:if>
		
	
	<acme:form-submit test="${command == 'create'}" code="worker.application.form-buttom.create" action="/worker/application/create"/>
	<acme:form-return code="worker.application.form.button.return" />
	
</acme:form>