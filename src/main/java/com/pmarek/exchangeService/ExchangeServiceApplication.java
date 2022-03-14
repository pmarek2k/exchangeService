package com.pmarek.exchangeService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeServiceApplication {

	final static Logger logger = LogManager.getLogger(ExchangeServiceApplication.class);

	public static void main(String[] args) {
		logger.info("Starting application.");
		SpringApplication.run(ExchangeServiceApplication.class, args);
	}

}
