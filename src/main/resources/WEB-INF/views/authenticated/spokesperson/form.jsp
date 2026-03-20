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
	<acme:form-textbox 	code="authenticated.spokesperson.form.label.cv" path="cv"/>
	<acme:form-textbox 	code="authenticated.spokesperson.form.label.achievements" path="achievements"/>
	<acme:form-checkbox	code="authenticated.spokesperson.form.label.licensed" path="licensed"/>
	
	<jstl:if test="${_command == 'create'}">
		<acme:submit code="authenticated.spokesperson.form.button.create" action="/authenticated/spokesperson/create"/>
	</jstl:if>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.spokesperson.form.button.update" action="/authenticated/spokesperson/update"/>
	</jstl:if>
</acme:form>