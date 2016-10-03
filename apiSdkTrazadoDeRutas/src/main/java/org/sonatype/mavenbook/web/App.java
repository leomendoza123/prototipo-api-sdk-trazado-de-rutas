package org.sonatype.mavenbook.web;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.client.config.ClientConfig;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import java.util.List;

public class App {

  public static String getJson() {
	try {

		ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

		WebResource webResource = client
		   .resource("http://www.mocky.io/v2/57e0477b100000e803f3a4ea");
  
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        
        


		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		Example result =  response.getEntity(Example.class);
    
        //ObjectMapper mapper = new ObjectMapper();
        //Example obj = mapper.readValue(result, Example.class);


		return result.getFirstName(); 

	  } catch (Exception e) {

		e.printStackTrace();
		return "Error fetching the data "+ e;

	  }

	}
}