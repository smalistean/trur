package md.trur.model;

import java.util.ArrayList;
import java.util.List;

public class TransportType {
	private Integer id;
	private String name;
	private List<Transport> transports;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Transport> getTransports() {
		return transports;
	}
	public void setTransports(List<Transport> transports) {
		this.transports = transports;
	}
	public void addTransport(Transport t) {
		if (transports == null) {
			transports = new ArrayList<Transport>();
		}
		this.transports.add(t);
	}
}
