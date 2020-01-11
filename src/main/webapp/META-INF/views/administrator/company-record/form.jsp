<%--
- form.jsp
-
- Copyright (c) 2019 Lizseth Esquen.
  path se le llama atributos en jsp
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.company-record.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company-record.form.label.ceo" path="ceo"/>
	<acme:form-textbox code="administrator.company-record.form.label.description" path="description"/>
	<acme:form-url code="administrator.company-record.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.company-record.form.label.email" path="email"/>
	<acme:form-textbox code="administrator.company-record.form.label.phone" path="phone" placeholder= "+492 (1231) 76582595"/>	 
	<acme:form-checkbox code="administrator.company-record.form.label.isInc" path="isInc"/>
	<acme:form-textbox code="administrator.company-record.form.label.starNumber" path="starNumber"/>
  	 
  	<acme:form-submit test= "${command == 'show' }"
  	 code="administrator.company-record.form-buttom.update" 
  	 action="/administrator/company-record/update"/>
  	 
  	 <acme:form-submit test= "${command == 'show' }"
  	 code="administrator.company-record.form-buttom.delete" 
  	 action="/administrator/company-record/delete"/>
  	 
  	 <acme:form-submit test= "${command == 'create' }"
  	 code="administrator.company-record.form-buttom.create" 
  	 action="/administrator/company-record/create"/>
  	 
  	 <acme:form-submit test= "${command == 'update' }"
  	 code="administrator.company-record.form-buttom.update" 
  	 action="/administrator/company-record/update"/>
  	 
  	 <acme:form-submit test= "${command == 'delete' }"
  	 code="administrator.company-record.form-buttom.delete" 
  	 action="/administrator/company-record/delete"/>
  	 	
  	<acme:form-return code="anonymous.company-record.form.button.return"/>
</acme:form>