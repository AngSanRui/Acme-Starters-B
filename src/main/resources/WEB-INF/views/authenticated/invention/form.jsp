<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
<%--
	<acme:form-textbox 	code="any.invention.form.label.inventor" path="inventor"/>
	--%>
	<acme:form-textbox 	code="authenticated.invention.form.label.ticker" path="ticker"/>
	<acme:form-textbox 	code="authenticated.invention.form.label.name" path="name"/>
	<acme:form-textarea code="authenticated.invention.form.label.description" path="description"/>
	<acme:form-moment 	code="authenticated.invention.form.label.start-moment" path="startMoment"/>
	<acme:form-moment 	code="authenticated.invention.form.label.end-moment" path="endMoment"/>
	<acme:form-url 		code="authenticated.invention.form.label.more-info" path="moreInfo"/>
	<acme:form-checkbox	code="authenticated.invention.form.label.draft-mode" path="draftMode"/>
	<%--
	<acme:form-double 	code="any.invention.form.label.months-active" path="monthsActive"/>
	<acme:form-integer 	code="any.invention.form.label.cost" path="cost"/>
	--%>
	
	<%--
	<acme:button code="authenticated.invention.form.button.parts" action="/any/part/list?inventionId=${id}"/>
	<acme:button code="authenticated.invention.form.button.inventor" action="/any/inventor/show?inventorId=${inventor.id}"/>
	--%>
</acme:form>