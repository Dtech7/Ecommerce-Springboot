package com.example.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Receipt;
import com.example.models.User;
import com.example.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {
	
	private UserService uServ;
	
	@PostMapping("/register")
	public User register(@RequestBody LinkedHashMap<String, String> body) {
		String firstName = body.get("firstName");
		String lastName = body.get("lastName");
		String email = body.get("email");
		String password = body.get("password");
		String address = body.get("address");
		String phoneNumber = body.get("phoneNumber");
		
		return uServ.registerUser(firstName, lastName, email, password, address, phoneNumber);
	}

}
