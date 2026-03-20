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
	<acme:form-textbox 	code="authenticated.tactic.form.label.name" path="name"/>
	<acme:form-textarea code="authenticated.tactic.form.label.notes" path="notes"/>
	<acme:form-integer 	code="authenticated.tactic.form.label.expectedPercentage" path="expectedPercentage"/>
	<acme:form-textbox 	code="authenticated.tactic.form.label.kind" path="kind"/>
</acme:form>
