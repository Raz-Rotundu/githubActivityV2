package com.lumius.APIgetterV2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Activity Utils -- A collection of static functions for querying gitHub API and parsing through the results
 * @author Razvan Rotundu
 */
public class ActivityUtils {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * Parses a list of JsonNode objects into a list of human readible strings based on the action type
	 * @param nodes a list of JsonNode objects
	 * @return A list of readable strings corresponding to the action represented by the node
	 */
	public static List<String> parseStrings(List<JsonNode> nodes) {
		List<String> out = new ArrayList<String>();
		
		Map<Pair<String, String>, Integer> frequencies = new HashMap<>();
  	
		// get frequencies 
		for(JsonNode node : nodes) {
			String type = node.get("type").asText();
			String repo = node.get("repo").get("name").asText();
			JsonNode payload = node.get("payload");
			String action = "";
			
	  		switch(type) {
	  		case("PushEvent"):
	  			Integer size = Integer.valueOf(payload.get("size").asText());
	  			
	  		
	  			// Condense number of commits to each repo
	  			Pair<String, String> p = new Pair<String, String>(type, repo);
	  			if(frequencies.containsKey(p)) {
	  				frequencies.replace(p, frequencies.get(p) + size);
	  			}
	  			else {
	  				frequencies.put(p, size);
	  			}
	  			break;
	  		case("CreateEvent"):
	  			out.add(String.format("Created repo %s", repo));
	  			break;
	  		case("ForkEvent"):
	  			String forkee = payload.get("forkee").asText();
	  			out.add(String.format("Forked %s from %s", forkee, repo));
	  			break;
	  		case("WatchEvent"):
	  			action = payload.get("action").asText();
	  			out.add(String.format("%s watching %s", action, repo));
	  			break;
	  		case("IssuesEvent"):
	  			action = payload.get("action").asText();
	  			String issue = payload.get("issue").asText();
	  			out.add(String.format("%s, %s in %s", action, issue, repo));
	  			break;
	  		}
		}
		frequencies.forEach((k,v) -> {
			String s = String.format("Made %d commits to %s", v, k.getV());
			out.addFirst(s);
		});
		return out;
	}
  
  /**
   * Converts an input Stream to a list of JSON Node objects
   * @param instream the input stream containing the serialized JSON objects
   * @return A list of JsonNode objects
   */
  public static List<JsonNode> toList(InputStream instream){
	  try {
		  	 return OBJECT_MAPPER.readValue(instream, new TypeReference<List<JsonNode>>() {});
	  }
	  catch(IOException e) {
		  throw new UncheckedIOException(e);
	  }
  }
  
  /**
   * Makes an HTTP GET request to the given URL, and returns the response 
   * @param url the URL of the endpoint to be making the request to
   * @return Optional HttpResponse in the form of an InputStream
   */
  public static Optional<HttpResponse<InputStream>> makeRequest(String url) {
  	HttpClient client = HttpClient.newHttpClient();
  	HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();
  	try {
  		return  Optional.of(client.send(req, HttpResponse.BodyHandlers.ofInputStream()));
  		
  	}
  	catch(InterruptedException | IOException e) {
  		System.out.print(e.getMessage());
  		return Optional.empty();
  	}
  }
}
