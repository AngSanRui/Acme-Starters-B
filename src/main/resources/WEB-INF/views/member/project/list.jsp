<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="member.project.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="member.project.list.label.title" path="title" width="20%"/>
	<acme:list-column code="member.project.list.label.start-moment" path="startMoment" width="20%"/>
	<acme:list-column code="member.project.list.label.end-moment" path="endMoment" width="20%"/>
</acme:list>
