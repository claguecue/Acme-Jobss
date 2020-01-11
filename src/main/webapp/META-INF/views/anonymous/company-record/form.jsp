<%--
- form.jsp
-
- Copyright (c) 2019 Lizseth Esquen.
  path se le llama atributos en jsp
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company-record.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.company-record.form.label.ceo" path="ceo"/>
	<acme:form-textbox code="anonymous.company-record.form.label.description" path="description"/>
	<acme:form-url code="anonymous.company-record.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.company-record.form.label.email" path="email"/>
	<acme:form-textbox code="anonymous.company-record.form.label.phone" path="phone"/>	 
	<acme:form-checkbox code="anonymous.company-record.form.label.isInc" path="isInc"/>
	<acme:form-textbox code="anonymous.company-record.form.label.starNumber" path="starNumber"/>
  	<acme:form-return code="anonymous.company-record.form.button.return"/>
</acme:form>
