package com.example.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.NSRException;
import com.example.models.Item;
import com.example.models.Receipt;
import com.example.models.User;
import com.example.repository.ReceiptRepository;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ReceiptService {
	
	private ReceiptRepository rRepo;
	private UserRepository uRepo;
	
	//used is email is passed(main one to use)
	public Receipt createReceipt(String email, List<Item> items, Double total) {
		User u = uRepo.getByEmail(email).get();
		Receipt rec = new Receipt(u, items, total);
		return rRepo.save(rec);
	}
	
	//used if userId is passed(Just an extra precaution)
	public Receipt createReceipt(int id, List<Item> items, Double total) {
		User u = uRepo.findById(id).get();
		Receipt rec = new Receipt(u, items, total);
		return rRepo.save(rec);
	}
	
	public List<Receipt> getAllReceipt(String email){
		User u = uRepo.getByEmail(email).get();
		List<Receipt> rList = rRepo.getReceiptsByUser(u);
		return rList;
	}
	
	public Receipt updateReceipt(Receipt r) {
		try {
			return rRepo.save(r);
		}catch(Exception e) {
			throw new NSRException();
		}
		
	}
	
	

}
