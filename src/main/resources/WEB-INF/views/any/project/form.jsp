<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="any.project.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="any.project.form.label.title" path="title"/>
	<acme:form-textarea code="any.project.form.label.description" path="description"/>
	<acme:form-textarea code="any.project.form.label.keyWords" path="keyWords"/>
	<acme:form-moment 	code="any.project.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="any.project.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="any.project.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="any.project.form.label.draft-mode" path="draftMode"/>
																		
	<acme:button code="any.project.invention.form.button.inventions" action="/any/invention/projectList?projectId=${id}"/>
<%--
	<acme:button code="any.project.form.button.audit-report" action="/any/audit-report/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.campaign" action="/any/campaign/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.sponsorship" action="/any/sponsorship/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.strategy" action="/any/strategy/list?projectId=${id}"/>
	

	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="any.project.form.button.parts" action="/any/part/list?projectId=${id}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="any.project.form.button.parts" action="/any/part/list?projectId=${id}"/>
			<acme:submit code="any.project.form.button.update" action="/any/project/update"/>
			<acme:submit code="any.project.form.button.delete" action="/any/project/delete"/>
			<acme:submit code="any.project.form.button.publish" action="/any/project/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="any.project.form.button.create" action="/any/project/create"/>
		</jstl:when>		
	</jstl:choose>
--%>
</acme:form>