package com.example.authService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		
		// .env load ho raha hai
	       Dotenv dotenv = Dotenv.configure()
	               .directory("./") // root folder
	               .ignoreIfMalformed()
	               .ignoreIfMissing()
	               .load();

	       // env variables ko Spring ke liye set kar rahe
	       System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
	       System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
	       System.setProperty("GOOGLE_SCOPE", dotenv.get("GOOGLE_SCOPE"));
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
