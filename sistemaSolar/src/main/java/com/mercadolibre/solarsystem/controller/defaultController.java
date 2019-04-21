package com.mercadolibre.solarsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defaultController {

		@GetMapping(value="/")
		public ResponseEntity<String> defaultPath(){
			return ResponseEntity.ok("default page");
		}

}
