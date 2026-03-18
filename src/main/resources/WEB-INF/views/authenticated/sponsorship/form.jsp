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
	<acme:form-textbox 	code="authenticated.sponsorship.form.label.sponsor" path="sponsor"/>
	--%>
	<acme:form-textbox 	code="authenticated.sponsorship.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="authenticated.sponsorship.form.label.name" path="name"/>
	<acme:form-textarea code="authenticated.sponsorship.form.label.description" path="description"/>
	<acme:form-moment 	code="authenticated.sponsorship.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="authenticated.sponsorship.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="authenticated.sponsorship.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="authenticated.sponsorship.form.label.draft-mode" path="draftMode"/>
	
	<acme:button code="authenticated.sponsorship.form.button.donations" action="/authenticated/donation/list?sponsorshipId=${id}"/>
	<acme:button code="authenticated.sponsorship.form.button.sponsor" action="/authenticated/sponsor/show?sponsorId=${sponsor.id}"/>
</acme:form>
