<%--
- list.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for authenticated particular
- purposes.  The copyright owner does not offer authenticated warranties or representations, nor do
- they accept authenticated liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.part.list.label.name" path="name" width="20%"/>
	<acme:list-column code="authenticated.part.list.label.description" path="description" width="40%"/>
	<acme:list-column code="authenticated.part.list.label.cost" path="cost" width="20%"/>
	<acme:list-column code="authenticated.part.list.label.kind" path="kind" width="20%"/>
</acme:list>
