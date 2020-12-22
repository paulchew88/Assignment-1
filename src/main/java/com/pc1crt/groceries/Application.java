package com.pc1crt.groceries;

import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
