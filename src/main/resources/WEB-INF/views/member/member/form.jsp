<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="true">
	<acme:form-textbox 	code="member.member.form.label.userAccount" path="userAccount"/>
	<acme:form-textbox 	code="member.member.form.label.fullName" path="fullName"/>
	<acme:form-textarea code="member.member.form.label.email" path="email"/>
</acme:form>

