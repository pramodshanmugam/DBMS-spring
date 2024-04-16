package com.example.artworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.artworks.controller", "com.example.artworks.service"})
public class ArtworksApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArtworksApplication.class, args);
	}


}
