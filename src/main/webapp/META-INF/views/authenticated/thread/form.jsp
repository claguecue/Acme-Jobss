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
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
	<jstl:if test="${command != 'create' }">
		<acme:form-moment code="authenticated.thread.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:form-hidden path="id"/>
	<acme:form-submit test="${!messagesEmpty}" code="authenticated.thread.form.label.messages" action="/authenticated/message/list?id=${id}" method="get"/> 

  	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>
