package md.trur.controller;

import java.util.Map;

import md.trur.model.Line;
import md.trur.model.Point;
import md.trur.model.TransportType;
import md.trur.service.LineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MainController {
	
	@Autowired
	private LineService lineService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	@RequestMapping(value="/trasee", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, TransportType> getTraseeInJSON() {
		return lineService.getTransportTypes();
	}

	@RequestMapping(value="/trasee/{transportTypeId}/{transportId}", method = RequestMethod.GET)
	@ResponseBody
	public Line getTraseeInJSON(
			@PathVariable Integer transportTypeId,
			@PathVariable Integer transportId) {
		Line line = new Line();
		line.setPoints(lineService.getPoints(transportTypeId, transportId).toArray(new Point[0]));
		return line;
	}
	
	@RequestMapping(value = "/trasee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
    public void save(@RequestBody Point[] points) {
//		lineService
		System.out.println("bla");
    }
}
