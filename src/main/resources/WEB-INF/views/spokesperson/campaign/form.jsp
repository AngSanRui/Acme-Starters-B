<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="spokesperson.campaign.form.label.ticker" path="ticker" readonly="${!draftMode}"/>
	<acme:form-textbox 	code="spokesperson.campaign.form.label.name" path="name" readonly="${!draftMode}"/>
	<acme:form-textarea code="spokesperson.campaign.form.label.description" path="description" readonly="${!draftMode}"/>
	<acme:form-moment 	code="spokesperson.campaign.form.label.start-moment" path="startMoment" readonly="${!draftMode}"/>
	<acme:form-moment 	code="spokesperson.campaign.form.label.end-moment" path="endMoment" readonly="${!draftMode}"/>
	<acme:form-url 		code="spokesperson.campaign.form.label.more-info" path="moreInfo" readonly="${!draftMode}"/>
	<acme:form-select code="spokesperson.campaign.form.label.project" path="project" choices="${project}"/>
	
	<jstl:choose>
		<jstl:when test="${_command != 'create'}">
			<acme:form-double 	code="spokesperson.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
			<acme:form-integer 	code="spokesperson.campaign.form.label.effort" path="effort" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|link') && draftMode == false}">
			<acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>	
			<acme:submit code="spokesperson.campaign.form.button.link" action="/spokesperson/campaign/link"/>						
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish|link') && draftMode == true}">
			<acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
			<acme:submit code="spokesperson.campaign.form.button.update" action="/spokesperson/campaign/update"/>
			<acme:submit code="spokesperson.campaign.form.button.delete" action="/spokesperson/campaign/delete"/>
			<acme:submit code="spokesperson.campaign.form.button.publish" action="/spokesperson/campaign/publish"/>
			<acme:submit code="spokesperson.campaign.form.button.link" action="/spokesperson/campaign/link"/>				
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="spokesperson.campaign.form.button.create" action="/spokesperson/campaign/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
