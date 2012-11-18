package md.trur.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import md.trur.model.Point;
import md.trur.model.Transport;
import md.trur.model.TransportType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service("lineService")
public class LineService {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Map<Integer, TransportType> getTransportTypes() {
		final Map<Integer, TransportType> map = new HashMap<Integer, TransportType>();
		jdbcTemplate.query(
				"select tt.id, tt.name, t.id, t.name"
				+ " from TrurApp.TransportType tt"
				+ " join TrurApp.Transport t on t.TransportTypeId=tt.id",
				new MapSqlParameterSource(),
				new RowMapper<TransportType>() {
				      public TransportType mapRow(final ResultSet rs, final int rowNum) throws SQLException {
				    	  Integer transportTypeId = rs.getInt(1);
				    	  TransportType tt = map.get(transportTypeId);
				    	  if (tt == null) {
				    		  tt = new TransportType();
				    		  tt.setId(transportTypeId);
				    		  tt.setName(rs.getString(2));
				    	  }
				    	  Transport t = new Transport();
				    	  t.setId(rs.getInt(3));
				    	  t.setName(rs.getString(4));
				    	  tt.addTransport(t);
				    	  map.put(tt.getId(), tt);
				    	  return tt;
				      }
				});
		return map;
	}

	public List<Point> getPoints(Integer transportTypeId, Integer transportId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("transportTypeId", transportTypeId);
		params.put("transportId", transportId);
		return jdbcTemplate.query(
				"select p.id, p.position, p.lat, p.lng"
				+ " from TrurApp.Point p"
				+ " join TrurApp.Transport t on t.Id=p.TransportId"
				+ " where t.TransportTypeId=:transportTypeId and t.id=:transportId"
				+ " order by p.position",
				params,
				new RowMapper<Point>() {
				      public Point mapRow(final ResultSet rs, final int rowNum) throws SQLException {
				    	  return new Point(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4));
				      }
				});
	}
}
