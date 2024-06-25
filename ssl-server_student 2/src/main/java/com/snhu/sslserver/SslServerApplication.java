package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;     
import java.security.MessageDigest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
		
		@RestController	//Route to enable check sum
		class ServerController{
		    @RequestMapping("/hash")
		    public String myHash(){
		    	String data = "Hello Nick Wyrwas!";
		    	String hashHex = generateChecksum(data);
		    	
		    	return "<p>Data: " + data + "</p><p>Algorithm: SHA-256</p><p>Checksum: " + hashHex + "</p>";
		    	
		    }
		    public String generateChecksum(String data) {
		    	  try {
		    		  MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    		  byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
		    				  return bytesToHex(hash);
		    	  } catch (Exception e) {
		    		  return "Error generating checksum";
		    	  }
		    	  }
		    public String bytesToHex(byte[] hash) {
		    	StringBuilder hexString = new StringBuilder(2 * hash.length);
		    	for (int i = 0; i < hash.length; i++) {
		    		String hex = Integer.toHexString(0xff & hash[i]);
		    		if (hex.length() == 1) {
		    			hexString.append('0');
		    		}
		    		hexString.append(hex);
		    	}
		    	return hexString.toString();
		    }
	}
}

