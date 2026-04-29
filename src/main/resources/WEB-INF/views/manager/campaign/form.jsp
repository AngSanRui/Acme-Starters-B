<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="manager.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="manager.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="manager.campaign.form.label.description" path="description"/>
	<acme:form-moment 	code="manager.campaign.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="manager.campaign.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="manager.campaign.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="manager.campaign.form.label.draft-mode" path="draftMode"/>
	<acme:form-double 	code="manager.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
	<acme:form-integer 	code="manager.campaign.form.label.effort" path="effort" readonly="true"/>
</acme:form>
