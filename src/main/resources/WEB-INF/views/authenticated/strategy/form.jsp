<%--
- form.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this artefact. It has been tested carefully, but it is not guaranteed for authenticated particular
- purposes.  The copyright owner does not offer authenticated warranties or representations, nor do
- they accept authenticated liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<%--
	<acme:form-textbox 	code="authenticated.strategy.form.label.fundraiser" path="fundraiser"/>
	--%>
	<acme:form-textbox 	code="authenticated.strategy.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="authenticated.strategy.form.label.name" path="name"/>
	<acme:form-textarea code="authenticated.strategy.form.label.description" path="description"/>
	<acme:form-moment 	code="authenticated.strategy.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="authenticated.strategy.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="authenticated.strategy.form.label.more-info" path="moreInfo"/>
	<acme:form-double code="authenticated.strategy.form.label.months-active" path="monthsActive"/>
	<acme:form-double code="authenticated.strategy.form.label.expected-percentage" path="expectedPercentage"/>
	
	<acme:button code="authenticated.strategy.form.button.tactics" action="/authenticated/tactic/list?strategyId=${id}"/>
	<acme:button code="authenticated.strategy.form.button.fundraiser" action="/authenticated/fundraiser/show?fundraiserId=${fundraiserId}"/>
</acme:form>
