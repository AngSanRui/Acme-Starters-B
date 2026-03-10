<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<%--
	preguntar si esto cuenta como mostrar el perfil
	<acme:list-column code="authenticated.invention.list.label.inventor" path="inventor" width="20%"/>
	--%>
	<acme:list-column code="authenticated.invention.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="authenticated.invention.list.label.name" path="name" width="20%"/>
	<acme:list-column code="authenticated.invention.list.label.description" path="description" width="30%"/>
</acme:list>