

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.invention.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="authenticated.invention.list.label.name" path="name" width="20%"/>
	<acme:list-column code="authenticated.invention.list.label.start-moment" path="startMoment" width="30%"/>
	<acme:list-column code="authenticated.invention.list.label.end-moment" path="endMoment" width="30%"/>
</acme:list>
