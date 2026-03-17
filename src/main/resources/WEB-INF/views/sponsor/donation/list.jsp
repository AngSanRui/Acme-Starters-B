<%--
- list.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for auditor particular
- purposes.  The copyright owner does not offer auditor warranties or representations, nor do
- they accept auditor liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="sponsor.donation.list.label.name" path="name" width="25%"/>	
	<acme:list-column code="sponsor.donation.list.label.notes" path="notes" width="25%"/>	
	<acme:list-column code="sponsor.donation.list.label.money" path="money" width="25%"/>	
	<acme:list-column code="sponsor.donation.list.label.kind" path="kind" width="25%"/>
</acme:list>

<jstl:if test="${showCreate}">
	<acme:button code="sponsor.donation.list.button.create" action="/sponsor/donation/create?sponsorshipId=${sponsorshipId}"/>
</jstl:if>