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

	<acme:form-textbox code="provider.request.form.label.ticker" path="ticker" placeholder="RABCD-1234" />
	
	<acme:form-textbox code="provider.request.form.label.title" path="title" />
	<acme:form-moment code="provider.request.form.label.deadline" path="deadline" />
	<acme:form-textarea code="provider.request.form.label.text" path="text" />
	<acme:form-money code="provider.request.form.label.reward" path="reward" />
    <acme:form-checkbox code="provider.request.form.label.accept" path="accept"/> 
	
	
	<jstl:if test="${command=='create' }">
		<acme:form-submit code="provider.request.form.button.create" action="/provider/request/create"/>
	</jstl:if>

  	<acme:form-return code="provider.request.form.button.return"/>
</acme:form>
