<%--
- form.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this artefact. It has been tested carefully, but it is not guaranteed for member particular
- purposes.  The copyright owner does not offer member warranties or representations, nor do
- they accept member liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="member.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="member.invention.form.label.name" path="name"/>
	<acme:form-textarea code="member.invention.form.label.description" path="description"/>
	<acme:form-moment 	code="member.invention.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="member.invention.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="member.invention.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="member.invention.form.label.draft-mode" path="draftMode"/>
	<acme:form-double 	code="member.invention.form.label.months-active" path="monthsActive"/>
	<acme:form-money 	code="member.invention.form.label.cost" path="cost"/>
<%--
	<acme:button code="member.invention.form.button.parts" action="/member/part/list?inventionId=${id}"/>
	<acme:button code="member.invention.form.button.inventor" action="/member/inventor/show?inventorId=${inventor.id}"/>
--%>
</acme:form>
