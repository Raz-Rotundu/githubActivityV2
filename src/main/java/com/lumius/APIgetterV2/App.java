package com.lumius.APIgetterV2;

import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class App 
{
    public static void main( String[] args )
    {
    	if(args.length != 1) {
    		System.out.println("Usage: GitHubActivityV2 <USERNAME>");
    		return;
    	} else {
    		String url = "https://api.github.com/users/" + args[0] + "/events";
    		Optional<HttpResponse<InputStream>> response = ActivityUtils.makeRequest(url);
    		if(response.isEmpty()) {
    			System.out.println("An error has occurred, response is empty");
    			return;
    		} else {
    			Integer reply = response.get().statusCode();
    			if(reply != 200) {
    				System.out.printf("Failed to find profile (Error %s)%n", reply);
    				return;
    			} else {
        			InputStream contents = response.get().body();
        			List<String> stringsList = ActivityUtils.parseStrings(ActivityUtils.toList(contents));
        		
        			stringsList.stream()
        			.forEach(System.out::println);
    			}

    			
    		}
    		
    	}

    	
    	
    }
}
