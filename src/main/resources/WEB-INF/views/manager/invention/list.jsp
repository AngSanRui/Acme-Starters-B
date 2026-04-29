<%--
- list.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for member particular
- purposes.  The copyright owner does not offer member warranties or representations, nor do
- they accept member liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="member.invention.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="member.invention.list.label.name" path="name" width="20%"/>
	<acme:list-column code="member.invention.list.label.start-moment" path="startMoment" width="30%"/>
	<acme:list-column code="member.invention.list.label.end-moment" path="endMoment" width="30%"/>
</acme:list>
