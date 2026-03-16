<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="authenticated.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="authenticated.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="authenticated.campaign.form.label.description" path="description"/>
	<acme:form-moment 	code="authenticated.campaign.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="authenticated.campaign.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="authenticated.campaign.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="authenticated.campaign.form.label.draft-mode" path="draftMode"/>
	
	<acme:button code="authenticated.campaign.form.button.milestones" action="/authenticated/milestone/list?campaignId=${id}"/>
</acme:form>
