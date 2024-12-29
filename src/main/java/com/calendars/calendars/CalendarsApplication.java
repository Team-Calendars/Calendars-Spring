package com.calendars.calendars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CalendarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarsApplication.class, args);
	}

}
