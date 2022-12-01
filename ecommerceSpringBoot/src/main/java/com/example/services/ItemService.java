package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Item;
import com.example.repository.ItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {

	ItemRepository itemRepo;
	
	public Item createItem(String name, Integer amount, Double price, String description, String imageUrl){
		Item newItem = new Item(0, name, amount, price, description, imageUrl);
		return itemRepo.save(newItem);
	}
	
	public Item readItem(Item i) {
		return itemRepo.findById(i.getItemId()).get();
	}
	
	public Item updateItem(Item i) {
		
		Item updateItem = itemRepo.findById(i.getItemId()).get();
		
		updateItem.setAmount(i.getAmount());
		updateItem.setDescription(i.getDescription());
		updateItem.setImageUrl(i.getImageUrl());
		updateItem.setName(i.getName());
		updateItem.setPrice(i.getPrice());
		
		return itemRepo.save(updateItem);
	}
		
	public String deleteItem(Item i) {
		itemRepo.delete(i);
		
		return "Item has been deleted";
	}
	
}
	

