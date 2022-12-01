package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.ICException;
import com.example.exceptions.UAEException;
import com.example.models.User;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {
	
	private UserRepository userRepo;
	
	public User registerUser(String firstName, String lastName, String email, String password) {
		User u = new User(firstName, lastName, email, password);
		
		try {
			User newU = userRepo.save(u);
			return newU;
		}catch(Exception e) {
			throw new UAEException();
		}
	}
	
	public User logiUser(String email, String password) {
		User u = userRepo.getByEmail(email).orElseThrow(ICException::new);
		if(!u.getPassword().equals(password)) {
			throw new ICException();
		}
		return u;
	}
	
	
}
