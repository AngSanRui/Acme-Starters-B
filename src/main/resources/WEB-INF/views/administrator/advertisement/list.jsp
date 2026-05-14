<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.advertisement.list.label.slogan" path="slogan" width="30%"/>
	<acme:list-column code="administrator.advertisement.list.label.picture" path="picture" width="30%"/>
	<acme:list-column code="administrator.advertisement.list.label.target" path="target" width="30%"/>
</acme:list>
<acme:button code="administrator.advertisement.list.button.create" action="/administrator/advertisement/create"/>

