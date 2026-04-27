<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="member.project.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="member.project.form.label.title" path="title"/>
	<acme:form-textarea code="member.project.form.label.description" path="description"/>
	<acme:form-textarea code="member.project.form.label.keyWords" path="keyWords"/>
	<acme:form-moment 	code="member.project.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="member.project.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="member.project.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="member.project.form.label.draft-mode" path="draftMode"/>
																		
	<acme:button code="member.project.form.button.inventions" action="/member/invention/list?projectId=${id}"/>
	<acme:button code="member.project.form.button.audit-report" action="/member/audit-report/list?projectId=${id}"/>
	<acme:button code="member.project.form.button.campaign" action="/member/campaign/list?projectId=${id}"/>
	<acme:button code="member.project.form.button.sponsorship" action="/member/sponsorship/list?projectId=${id}"/>
	<acme:button code="member.project.form.button.strategy" action="/member/strategy/list?projectId=${id}"/>
	
<%--
	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="member.project.form.button.parts" action="/member/part/list?projectId=${id}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="member.project.form.button.parts" action="/member/part/list?projectId=${id}"/>
			<acme:submit code="member.project.form.button.update" action="/member/project/update"/>
			<acme:submit code="member.project.form.button.delete" action="/member/project/delete"/>
			<acme:submit code="member.project.form.button.publish" action="/member/project/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="member.project.form.button.create" action="/member/project/create"/>
		</jstl:when>		
	</jstl:choose>
--%>
</acme:form>