package com.jmant69.CustomerAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jmant69.CustomerAPI.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;

}
