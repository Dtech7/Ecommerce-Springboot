package com.example.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String now = LocalDateTime.now().format(format);
		
		Receipt rec = new Receipt(u, items, total, now);
		return rRepo.save(rec);
	}
	
	//used if userId is passed(Just an extra precaution)
	public Receipt createReceipt(int id, List<Item> items, Double total) {
		User u = uRepo.findById(id).get();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String now = LocalDateTime.now().format(format);
		
		Receipt rec = new Receipt(u, items, total, now);
		return rRepo.save(rec);
	}
	
	public Receipt readReceipt(Receipt r) {
		return rRepo.findById(r.getReceiptNumer()).get();
	}
	
	public Receipt updateReceipt(Receipt r) {
		Receipt updateReceipt = rRepo.findById(r.getReceiptNumer()).get();
		
		updateReceipt.setAmountOfItems(r.getAmountOfItems());
		updateReceipt.setDateTime(r.getDateTime());
		updateReceipt.setItems(r.getItems());
		updateReceipt.setTotal(r.getTotal());
		updateReceipt.setUser(r.getUser());
		
		return rRepo.save(updateReceipt);
	}
	
	public String deleteReceipt(Receipt r) {
		rRepo.delete(r);
		return "Receipt has been deleted";
	}

}
