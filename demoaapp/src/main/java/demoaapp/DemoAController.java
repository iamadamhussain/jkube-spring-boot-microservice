package demoaapp;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/demoa")
@Slf4j
public class DemoAController {
	static String H="-H";
	@Autowired
    private RestTemplate restTemplate;
	  @GetMapping("/message")
	    public String getMessage() {
	      log.info("Inside getMessage method of DemoAController");
	        Date createDated=new Date();
	        String msg="Hi !! WelCome to DemoA"+": Date:"+createDated.toString();
	        return msg;
	    }
	  
	
	   @PostMapping(value = "/get_user")
	    public String getPostResponse(){
		   
		   String url="http://demobapp:8080/demob/send_user";
				   String url1="http://localhost:8080/demob/send_user";
	        HttpHeaders headers=new HttpHeaders();
	        headers.set("Content-type","application/json");
	        headers.set("Ce-id","1");

	        headers.set("Ce-source","fss-example");
	   

	        headers.set("Ce-type","fss.myapplication");
	        headers.set("Ce-specversion","1.0");

	        headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
	        String body="{\"username\": \"javadeveloper\", \"firstName\": \"venu\", \"lastName\": \"gopal\", \"age\": 23}";
	        
	        HttpEntity<String> entity=new HttpEntity<String>(body, headers);
	        
	        System.out.println("-------calling DemoB---------------");
	        String response= restTemplate.exchange(url,HttpMethod.POST,entity,String.class).getBody();
	      
	        
	        return response;
	    }
	 

	 
}
