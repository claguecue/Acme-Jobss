<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:list>

	<acme:list-column code="authenticated.offer.list.label.ticker" path="ticker" width="25%" />

	<acme:list-column code="authenticated.offer.list.label.title" path="title" width="25%" />
	<acme:list-column code="authenticated.offer.list.label.minAmount" path="minAmount" width="25%" />
	<acme:list-column code="authenticated.offer.list.label.maxAmount" path="maxAmount" width="25%" />

	



</acme:list>