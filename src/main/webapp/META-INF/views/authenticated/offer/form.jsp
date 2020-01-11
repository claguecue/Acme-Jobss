<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.offer.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment 
	code="authenticated.offer.form.label.creationMoment" 
	path="creationMoment"
	readonly="true"/>
	</jstl:if>
	<acme:form-moment code="authenticated.offer.form.label.limitDate" path="limitDate"/>
	<acme:form-textbox code="authenticated.offer.form.label.descriptionText" path="descriptionText"/>
	<acme:form-money code="authenticated.offer.form.label.minAmount" path="minAmount"/>
	<acme:form-money code="authenticated.offer.form.label.maxAmount" path="maxAmount"/>
	<acme:form-textbox code="authenticated.offer.form.label.ticker" path="ticker"/>
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="authenticated.offer.form.label.accept" path="accept"/>
	</jstl:if>
	
	
	<jstl:if test="${command=='create' }">
	<acme:form-submit 
	code= "authenticated.offer.form.button.create"
	action= "/authenticated/offer/create"/>
	</jstl:if>
	
	
	<acme:form-return code="authenticated.offer.form.button.return"/>
</acme:form>
