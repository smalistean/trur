<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="<c:url value="/resources/leaflet/leaflet.css" />" />
<!--[if lte IE 8]>
     <link rel="stylesheet" href="<c:url value="/resources/leaflet/leaflet.ie.css" />" />
 <![endif]-->
<script src="<c:url value="/resources/leaflet/leaflet-src.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery-1.8.2.js" />"></script>
<script>
	CM_URL = 'http://localhost:8080/trur/image/{s}/{x}/{y}/{z}';
</script>
<style>
#coordinatesTable {font-size: 13px;}
#coordinatesTable tr td {border: 1px solid blue;}
</style>
</head>
<body>
	<script>
		var traseuJSON;
		$(function() {
			var map = L.map('map').setView([47.0231, 28.83808], 13);
			L.tileLayer(CM_URL, {
			    attribution: '',
			    maxZoom: 18
			}).addTo(map);
			
			map.on('click', onMapClick);
			
			$.getJSON('<c:url value="/trasee" />', function (results) {
		    	traseuJSON = results;
			    var traseuTypeId = $("#traseuTypeId");
			    $.map(traseuJSON, function(v, i) {
			    	traseuTypeId.append("<option value='" + v.id + "'>" + v.name + "</option>");
	   		    });
			    $("#traseuTypeId").change();
		    });
			
			$("#traseuTypeId").change(function () {
				var id = $(this).val();
				var traseuId = $("#traseuId");
				traseuId.find('option').remove();
				var transports = traseuJSON[id].transports;
				$(transports).each(function(i, v) {
					traseuId.append("<option value='" + v.id + "'>" + v.name + "</option>");
	   		    });
				traseuId.change();
			});
			
			$("#traseuId").change(function () {
				var traseuType = $("#traseuTypeId").val();
				var traseu = $(this).val();
				var url = '${pageContext.request.contextPath}/trasee/' + traseuType + '/' + traseu;
			    $.getJSON(url, function (results) {
			    	map.removeLayer(traseuLayer);
			    	traseuLayer = L.geoJson().addTo(map);
			    	results.coordinates = pointsToCoordinates(results.points);
			    	traseuLayer.addData(results);
			    	//L.geoJson(results).addTo(map);
			    	//de pus puncte pa harta
			    	//de desenat inputuri
			    	drawPointsTable(results.points);
			    });
			});
			var traseuLayer = L.geoJson().addTo(map);
			
			$("#save").click(function() {
				$.ajax({
					  url: '<c:url value="/trasee" />',
					  data: { vars: newPoints},
					  type: 'post',
					  success: function(data) {
						  $("#traseuId").change();
						  newPoints = new Array();
					  }
			    });
			})
		});
		
		function pointsToCoordinates(points) {
			var coordinates = new Array(points.length);
			for (var i = 0; i < points.length; i++) {
				coordinates[i] = [points[i].lat, points[i].lng];
			}
			return coordinates;
		}
		
		function drawPointsTable(points) {
			var table = $("#coordinatesTable");
			table.find('tr').remove();
			for (var i = 0; i < points.length; i++) {
				table.append("<tr><td class='clickable' dbId='" + points[i].id + "'>" + (i + 1) + "</td><td>" + points[i].lat + "</td><td>" + points[i].lng + "</td>");
			}
			$(".clickable").click(function() {
				//daca starea era inactiva
				//deseneaza pe harta
				//fa inputuri
				//starea activa seteaza
				//la click pe harta se introduc valorile in inputuri
				//daca era activa
				//sterge de pe harta
				//sterge inputuri
				//la click pe harta comportament vechi
			});
		}
		
		var newPoints = new Array();
		
		function onMapClick(e) {
			var i = $("#coordinatesTable tr").length;
			$("#coordinatesTable").append("<tr><td class='clickable'>" + (i + 1)
					+ "</td><td>" + e.latlng.lng + "</td><td>"
					+ e.latlng.lat + "</td>");
			newPoints[i] = [e.latlng.lng, e.latlng.lat];
			//console.log("{" + e.latlng.lng + ", " + e.latlng.lat + "},");
			$("#save").removeAttr("disabled");
		}
	</script>
	<div style="width:20%;float:left;">
		<select id="traseuTypeId"></select>
		<select id="traseuId"></select>
		<input id="save" type="button" value="Save" disabled="disabled"/>
		<div style="height:578px;overflow:scroll;">
			<table id="coordinatesTable"></table>
		</div>
	</div>
	<div id="map" style="width:80%;height:600px;"></div>
</body>
</html>