<%--
- list.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="fundraiser.strategy.list.label.ticker" path="ticker" width="25%"/>
	<acme:list-column code="fundraiser.strategy.list.label.name" path="name" width="25%"/>
	<acme:list-column code="fundraiser.strategy.list.label.months-active" path="monthsActive" width="25%"/>
	<acme:list-column code="fundraiser.strategy.list.label.expected-percentage" path="expectedPercentage" width="25%"/>
	<acme:list-column code="fundraiser.strategy.list.label.draft-mode" path="draftModeLabel" width="10%"/>
</acme:list>

<acme:button code="fundraiser.strategy.list.button.create" action="/fundraiser/strategy/create"/>