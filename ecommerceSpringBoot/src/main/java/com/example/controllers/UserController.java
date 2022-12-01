package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {
	
	private UserService uServ;
	
	@PostMapping("/register")
	public User register(@RequestBody Object o) {
		return uServ.registerUser(o.firstName, o.lastName, o.email, o.password);
	}


}	
class Object{
	public String firstName;
	public String lastName;
	public String email;
	public String password;

}
