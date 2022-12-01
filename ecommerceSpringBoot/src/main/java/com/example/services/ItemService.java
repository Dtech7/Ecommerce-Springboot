package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.exceptions.NAIException;
import com.example.models.Item;
import com.example.repository.ItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {
	
	private ItemRepository iRepo;
	
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
