<%--
- form.jsp
-
- Copyright (C) 2012-2026 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:print code="manager.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.total-numbers-project"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.deviation-average-projects"/>
		</th>
		<td>
			<acme:print value="${deviationOfTheAverageNumOfProjects}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.minimum-deviation-effort"/>
		</th>
		<td>
			<acme:print value="${minimumDeviationOfEffort}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.maximum-deviation-effort"/>
		</th>
		<td>
			<acme:print value="${maximumDeviationOfEffort}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:print code="manager.dashboard.form.label.average-deviation-effort"/>
		</th>
		<td>
			<acme:print value="${averageDeviationOfEffort}"/>
		</td>
	</tr>	
</table>

<h2>
	<acme:print code="manager.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "REJECTED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${ratioOfPendingApplications}"/>, 
						<jstl:out value="${ratioOfAcceptedApplications}"/>, 
						<jstl:out value="${ratioOfRejectedApplications}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>

