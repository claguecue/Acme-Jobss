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
	<acme:form-url code="sponsor.non-commercial-banner.form.label.picture" path="picture" placeholder="http://www.acme.com/imgfile[.jpg|.png|.gif]"/>
	<acme:form-url code="sponsor.non-commercial-banner.form.label.targetURL" path="targetURL" />
	<acme:form-textarea code="sponsor.non-commercial-banner.form.label.slogan" path="slogan" />	
	<acme:form-url code="sponsor.non-commercial-banner.form.label.jingle" path="jingle" placeholder="http://www.acme.com/audfile[.mp3|.wav|.ogg]"/>
	
  	<acme:form-return code="sponsor.non-commercial-banner.form.button.return"/>
</acme:form>
