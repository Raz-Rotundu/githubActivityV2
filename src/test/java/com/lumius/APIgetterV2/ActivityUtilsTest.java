package com.lumius.APIgetterV2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


class ActivityUtilsTest {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static final String URL = "https://api.github.com/users/kamranahmedse/events";
	
	//Reference stuff
	public static HttpResponse<InputStream> testStream;
	public static List<JsonNode> testList;
	
	// Common request
	public static Optional<HttpResponse<InputStream>> testRep;

	
	
	@BeforeAll
	public static void setup(){
		HttpClient client = HttpClient.newHttpClient();
    	HttpRequest req = HttpRequest.newBuilder(URI.create(URL)).build();
    	try {
    		//Set up reference stream
        	testStream = client.send(req, HttpResponse.BodyHandlers.ofInputStream());

        	//set up reference list
        	testList = OBJECT_MAPPER.readValue(testStream.body(), new TypeReference<List<JsonNode>>() {});
        	
        	//The test of the request
        	testRep = ActivityUtils.makeRequest(URL);
        	
        	
    	}
    	catch(IOException | InterruptedException e){
    		System.out.println("error");
    	}
	}
	@Test
	void testParseStrings() {
		List<String> testStrings = ActivityUtils.parseStrings(testList);
		String[] words = testStrings.getFirst().split(" ");
		assertEquals("Pushed", words[0]);
	}
	
	@Test
	void testToList() {
		if (testRep.isEmpty()) {
			//Auto fail
			assertTrue(false);
		} else {
			assertEquals(30, ActivityUtils.toList(testRep.get().body()).size());
		}
	}
	

}
