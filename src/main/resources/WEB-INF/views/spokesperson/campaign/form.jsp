<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="spokesperson.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="spokesperson.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="spokesperson.campaign.form.label.description" path="description"/>
	<acme:form-moment 	code="spokesperson.campaign.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="spokesperson.campaign.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="spokesperson.campaign.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="spokesperson.campaign.form.label.draft-mode" path="draftMode"/>
	
	<acme:button code="spokesperson.campaign.form.button.milestones" action="/spokesperson/milestone/list?campaignId=${id}"/>
</acme:form>
