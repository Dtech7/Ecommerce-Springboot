package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.IAEException;
import com.example.exceptions.NAIException;
import com.example.models.Item;
import com.example.repository.ItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {
	
	private ItemRepository iRepo;
	
	public Item createItem(Item i) {
		try {
			return iRepo.save(i);
		}catch(Exception e) {
			throw new IAEException();
		}
		
	}
	
	public Item updateItem(Item i) {
		return iRepo.save(i);
	}
	
	public List<Item> getAllItems(){
		List<Item> allItems = iRepo.findAll();
		return allItems;
	}
	
	public Item getItemById(Integer id) {
		Item item;
		
		try {
			 item = iRepo.getReferenceById(id);
		}
		catch(Exception e) {
			throw new NAIException();
		}
		return item;
	}

}
