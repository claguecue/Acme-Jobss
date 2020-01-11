<%--
- form.jsp
-
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
	<jstl:if test="${command!='create' }">
		<acme:form-moment code="consumer.offer.form.label.creationMoment" path="creationMoment" readonly='true'/>
	</jstl:if>
	
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker" placeholder="OABCD-12345" />
	
	<acme:form-textbox code="consumer.offer.form.label.title" path="title" />
	<acme:form-moment code="consumer.offer.form.label.limitDate" path="limitDate" />
	<acme:form-textarea code="consumer.offer.form.label.descriptionText" path="descriptionText" />
	<acme:form-money code="consumer.offer.form.label.minAmount" path="minAmount" />
	<acme:form-money code="consumer.offer.form.label.maxAmount" path="maxAmount" />
	<acme:form-checkbox code="consumer.offer.form.label.accept" path="accept"/>
	<jstl:if test="${command=='create' }">
		<acme:form-submit code="consumer.offer.form.button.create" action="/consumer/offer/create"/>
	</jstl:if>
		
  	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>
