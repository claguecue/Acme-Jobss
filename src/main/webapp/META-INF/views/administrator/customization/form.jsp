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
	

	<acme:form-double code="administrator.customization.form.label.spamThreshold" path="spamThreshold"/>
	<acme:form-textbox code="administrator.customization.form.label.spamWords" path="spamWords"/>
	<acme:form-submit code="administrator.customization.form.button.update" action="/administrator/customization/update"/>

	 
	
  	<acme:form-return code="administrator.customization.form.button.return"/>
</acme:form>
