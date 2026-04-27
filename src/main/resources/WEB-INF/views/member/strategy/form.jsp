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

	<acme:form-textbox 	code="member.strategy.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="member.strategy.form.label.name" path="name"/>
	<acme:form-textarea code="member.strategy.form.label.description" path="description"/>
	<acme:form-moment 	code="member.strategy.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="member.strategy.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="member.strategy.form.label.more-info" path="moreInfo"/>
</acme:form>
