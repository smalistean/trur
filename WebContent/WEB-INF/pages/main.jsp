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
</head>
<body>
	<script>
		$(function() {
			var map = L.map('map').setView([47.0231, 28.83808], 13);
			L.tileLayer(CM_URL, {
			    attribution: '',
			    maxZoom: 18
			}).addTo(map);
			
			//map.on('click', onMapClick);
		});
		
		function onMapClick(e) {
		    alert("You clicked the map at " + e.latlng);
		}
	</script>
	<div id="map" style="height: 600px;"></div>
</body>
</html>