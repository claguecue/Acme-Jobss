<%--
- form.jsp
-
- Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
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
	<acme:form-url code="sponsor.commercial-banner.form.label.picture" path="picture"/>
	<acme:form-url code="sponsor.commercial-banner.form.label.targetURL" path="targetURL" />
	<acme:form-textarea code="sponsor.commercial-banner.form.label.slogan" path="slogan" />
	
	<acme:form-panel code="sponsor.commercial-banner.form.label.creditCard">
	
	<acme:form-textbox code="sponsor.commercial-banner.form.label.creditCardNumber" path="creditCardNumber" />	

	<acme:form-textbox code="sponsor.commercial-banner.form.label.holderName" path="holderName" />
	<acme:form-integer code="sponsor.commercial-banner.form.label.expirationMonth" path="expirationMonth" placeholder="01"/> 
	<acme:form-integer code="sponsor.commercial-banner.form.label.expirationYear" path="expirationYear" placeholder="2019"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.cvv" path="cvv" placeholder="1234"/> 

	
	</acme:form-panel>
	
	
  	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
</acme:form>
