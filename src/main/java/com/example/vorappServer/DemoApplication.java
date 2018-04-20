package com.example.vorappServer;


import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.User;
import com.example.vorappServer.repo.DimRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {


	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	@Autowired
	private static DimRepo dimRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}
