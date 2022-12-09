package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.IAEException;
import com.example.exceptions.NAIException;
import com.example.models.Item;
import com.example.repository.ItemRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
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
		Item curItem = iRepo.findById(i.getItemId()).get();
		
		if(i.getName() == null){
			i.setName(curItem.getName());
		}
		if(i.getAmount() == null){
			i.setAmount(curItem.getAmount());
		}
		if(i.getPrice() == null){
			i.setPrice(curItem.getPrice());
		}
		if(i.getDescription() == null){
			i.setDescription(curItem.getDescription());
		}
		if(i.getImageUrl() == null){
			i.setImageUrl(curItem.getImageUrl());
		}
		
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
	
	public String deleteItem(Integer id) {
		Item i = iRepo.findById(id).get();
		iRepo.delete(i);
		
		return "Item has been deleted";
	}

}
