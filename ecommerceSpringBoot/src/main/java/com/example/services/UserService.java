package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.ICException;
import com.example.exceptions.UAEException;
import com.example.models.Receipt;
import com.example.models.User;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {
	
	private UserRepository userRepo;
	
	public User registerUser(String firstName, String lastName, String email, String password, String address, String phoneNumber) {
		List<Receipt> receipts = new ArrayList<>();
		User u = new User(0, firstName, lastName, email, password, receipts ,address, phoneNumber);
		
		try {
			User newU = userRepo.save(u);
			return newU;
		}catch(Exception e) {
			throw new UAEException();
		}
	}
	
	public User loginUser(String email, String password) {
		User u = userRepo.getByEmail(email).orElseThrow(ICException::new);
		if(!u.getPassword().equals(password)) {
			throw new ICException();
		}
		return u;
	}
	
	public User readUser(User u) {
		return userRepo.findById(u.getUserId()).get();
	}
	
	public User updateUser(User u) {
		User updateUser = userRepo.findById(u.getUserId()).get();
		
		updateUser.setAddress(u.getAddress());
		updateUser.setEmail(u.getEmail());
		updateUser.setFirstName(u.getFirstName());
		updateUser.setLastName(u.getLastName());
		updateUser.setPassword(u.getPassword());
		updateUser.setPhoneNumber(u.getPhoneNumber());
		updateUser.setUserReceipts(u.getUserReceipts());
		
		return userRepo.save(updateUser);
	}
	
	
	public String deleteUser(User u) {
		userRepo.delete(u);
		return "User has been deleted";
	}
	
}
