package com.example.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.ICException;
import com.example.exceptions.UAEException;
import com.example.models.User;
import com.example.services.UserService;

import lombok.AllArgsConstructor;

/*Used if we implement validation
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.example.validation.UserValidation;
*/



@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {
	
	private UserService uServ;
	
/*Used if we implement validation
 * 	@InitBinder
	protected void initBinder(WebDataBinder bin) {
		if(User.class.isAssignableFrom(bin.getTarget().getClass())) {
			bin.addValidators(new UserValidation());
		}
	}*/
	
/*---------------------------Register User------------------------------------*/
	@PostMapping("/register")
	public User register(@RequestBody User u) {
		return uServ.registerUser(u);
	}
	
	@PostMapping("/register")
	public User register(@RequestBody LinkedHashMap<String, String> body) {
		String firstName = body.get("firstName");
		String lastName = body.get("lastName");
		String email = body.get("email");
		String password = body.get("password");
		User nUser  = new User(firstName, lastName, email, password);

		return uServ.registerUser(nUser);
	}
	
	
/*---------------------------LogIn User----------------------------------------*/
	@PostMapping("/logIn")
	public User logInUser(@RequestBody LinkedHashMap<String, String> body) {
		String email = body.get("email");
		String password = body.get("password");
		return uServ.logInUser(email, password);
	}
	
/*--------------------------Update user----------------------------------------*/
	@PutMapping("/")
	public User updateUser(@RequestBody User u) {
		return uServ.updateUser(u);
	}
	
/*--------------------------Exception Handlers-----------------------------------*/
	@ExceptionHandler({ICException.class})
	public ResponseEntity<String> handleIC(){
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({UAEException.class})
	public ResponseEntity<String> handleUAE(){
		return new ResponseEntity<>("Email already exist", HttpStatus.CONFLICT);
	}

}	

