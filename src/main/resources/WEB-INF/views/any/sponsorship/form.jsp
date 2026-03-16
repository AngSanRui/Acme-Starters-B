<%--
- form.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this artefact. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<%--
	<acme:form-textbox 	code="any.sponsorship.form.label.sponsor" path="sponsor"/>
	--%>
	<acme:form-textbox 	code="any.sponsorship.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="any.sponsorship.form.label.name" path="name"/>
	<acme:form-textarea code="any.sponsorship.form.label.description" path="description"/>
	<acme:form-moment 	code="any.sponsorship.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="any.sponsorship.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="any.sponsorship.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="any.sponsorship.form.label.draft-mode" path="draftMode"/>
	<acme:form-double 	code="any.sponsorship.form.label.months-active" path="monthsActive"/>
	<acme:form-money 	code="any.sponsorship.form.label.total-money" path="totalMoney"/>
	
	<acme:button code="any.sponsorship.form.button.donations" action="/any/donation/list?sponsorshipId=${id}"/>
	<acme:button code="any.sponsorship.form.button.sponsor" action="/any/sponsor/show?sponsorId=${sponsor.id}"/>
</acme:form>
