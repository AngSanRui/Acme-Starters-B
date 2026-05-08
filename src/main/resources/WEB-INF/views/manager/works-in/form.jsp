<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-select 	code="manager.project.form.label.username" path="member" choices="${members}" readonly ="${acme:anyOf(_command, 'show')}"/>
	<acme:form-select 	code="manager.project.form.label.role" path="role" choices="${roles}" readonly ="${acme:anyOf(_command, 'show')}"/>

<jstl:choose>	
<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish')}">
	<acme:submit code="manager.member.list.button.deleteMember" action="/manager/works-in/delete?id=${id}"/>
</jstl:when>
<jstl:when test="${acme:anyOf(_command, 'create')}">
	<acme:submit code="manager.member.list.button.createMember" action="/manager/works-in/create?projectId=${projectId}"/>
</jstl:when>
</jstl:choose>

</acme:form>

