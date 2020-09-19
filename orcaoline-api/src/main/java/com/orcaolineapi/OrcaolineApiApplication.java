package com.orcaolineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.orcaolineapi.config.property.OrcaOnlineApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(OrcaOnlineApiProperty.class)
public class OrcaolineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrcaolineApiApplication.class, args);
	}
}
