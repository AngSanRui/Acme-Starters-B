<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="any.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="any.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="any.campaign.form.label.description" path="description"/>
	<acme:form-moment 	code="any.campaign.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="any.campaign.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="any.campaign.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="any.campaign.form.label.draft-mode" path="draftMode"/>
	<acme:form-double 	code="any.campaign.form.label.monthsActive" path="monthsActive"/>
	<acme:form-integer 	code="any.campaign.form.label.effort" path="effort"/>

	
	<acme:button code="any.campaign.form.button.milestones" action="/any/milestone/list?campaignId=${id}"/>
	<acme:button code="any.campaign.form.button.spokesperson" action="/any/spokesperson/show?spokespersonId=${spokesperson.id}"/>
</acme:form>
