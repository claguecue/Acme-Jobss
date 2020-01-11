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
	<acme:form-textarea code="employer.orem.form.label.text" path="text"/>
	<acme:form-url code="employer.orem.form.label.marker" path="marker"/>
	
	
	<acme:form-submit test="${command == 'show' }" code="employer.orem.form.button.update" action="/employer/orem/update"/>
	<acme:form-submit test="${command == 'show' }" code="employer.orem.form.button.delete" action="/employer/orem/delete"/>
	<acme:form-submit test="${command == 'create' }" code="employer.orem.form.button.create" action="/employer/orem/create"/> 
	<acme:form-submit test="${command == 'update' }" code="employer.orem.form.button.update" action="/employer/orem/update"/>
	<acme:form-submit test="${command == 'delete' }" code="employer.orem.form.button.delete" action="/employer/orem/delete"/>
	
	<acme:form-return code="employer.orem.form.button.return"/>
</acme:form>


