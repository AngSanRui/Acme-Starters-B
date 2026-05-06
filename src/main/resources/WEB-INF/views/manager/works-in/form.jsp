<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select 	code="manager.works-in.form.label.role" choices="${roles}" path="role"/>
	<acme:form-select 	code="manager.works-in.form.label.member" choices="${members}" path="member"/>
	
	<acme:submit code="manager.works-in.form.label.create" action="/manager/works-in/create?projectId=${projectId}"/>
</acme:form>