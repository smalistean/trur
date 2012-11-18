package md.trur.model;


public class Line {
	private Point[] points;

//	@JsonIgnore
	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
	
//	public Double[][] getCoordinates() {
//		Double[][] coordinates = new Double[points.length][2];
//		for (int i = 0; i < points.length; i++) {
//			Point point = points[i];
//			coordinates[i][0] = point.getLat();
//			coordinates[i][1] = point.getLng();
//		}
//		return coordinates;
//	}
	
	public String getType() {
		return "LineString";
	}
}
