package md.trur.model;

public class Point {
	private Integer id;
	private Integer position;
	private Double lat;
	private Double lng;
	
	public Point() { }
	
	public Point(Integer id, Integer position, Double lat, Double lng) {
		this.id = id;
		this.position = position;
		this.lat = lat;
		this.lng = lng;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public String getType() {
		return "Point";
	}
}
