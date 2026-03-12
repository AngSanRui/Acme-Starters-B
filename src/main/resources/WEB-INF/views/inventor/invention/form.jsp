<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox 	code="inventor.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="inventor.invention.form.label.name" path="name"/>
	<acme:form-textarea code="inventor.invention.form.label.description" path="description"/>
	<acme:form-moment 	code="inventor.invention.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="inventor.invention.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="inventor.invention.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="inventor.invention.form.label.draft-mode" path="draftMode"/>
	<acme:form-double 	code="inventor.invention.form.label.months-active" path="monthsActive"/>
	<acme:form-money 	code="inventor.invention.form.label.cost" path="cost"/>
	
	<acme:button code="inventor.invention.form.button.parts" action="/inventor/part/list?inventionId=${id}"/>

</acme:form>