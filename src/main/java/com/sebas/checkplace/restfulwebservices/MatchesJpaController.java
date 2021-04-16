package com.sebas.checkplace.restfulwebservices;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.springframework.web.bind.annotation.*;


import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

@CrossOrigin(origins="*")
@RestController
public class MatchesJpaController {

	@GetMapping(path = "/jpa/matches")
	public List<String> getMatches() {
		System.out.println("Calling to cassandra database");
		List<String> matches = new ArrayList<>();
		Cluster cluster = Cluster.builder().addContactPointsWithPorts(new InetSocketAddress("cassandra_matches",9042)).build();
		Session session = cluster.connect("checkplace_keyspace");
		String query = "select * from matches";
		ResultSet rs = session.execute(query);

		Iterator<Row> i = rs.iterator();
		System.out.println("Getting rows");
		while (i.hasNext()) {
			JSONObject match = new JSONObject();
			Row row = i.next();
			int id = row.getInt("match_id");
			String white_player = row.getString("white_player");
			String black_player = row.getString("black_player");
			String result = row.getString("result");
			int year = row.getInt("year");
			String match_description = row.getString("match");
			match.put("match_id", id);
			match.put("white_player", white_player);
			match.put("black_player", black_player);
			match.put("result", result);
			match.put("year", year);
			match.put("match", match_description);
			matches.add(match.toJSONString());
		}
		return matches;
	}

}
