<%--
- list.jsp
-
- Copyright (c) 2019 Lizseth Esquen, based on Rafael Corchuelo DP files
-
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
  <acme:list-column  code="authenticated.company-record.list.label.name" path="name" width="40%" />
  <acme:list-column  code="authenticated.company-record.list.label.sector" path="sector" width="40%" />
  <acme:list-column code="authenticated.company-record.list.label.starNumber" path="starNumber" width="20%" /> 
</acme:list>