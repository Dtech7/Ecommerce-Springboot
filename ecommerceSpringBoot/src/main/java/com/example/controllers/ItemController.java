package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exceptions.IAEException;
import com.example.exceptions.NAIException;
import com.example.models.Item;
import com.example.services.ItemService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemController {
			
	private ItemService iServ;
	
	
/*------------------------Create & Update Item----------------------------*/	
	/*Create and update item should only be able to be used by store
	 * employees/managers to add new items to database or
	 * to edit prices on items*/
	@PostMapping("/")
	public Item createItem(@RequestBody Item i) {
		return iServ.createItem(i);
	}
	
	@PutMapping("/")
	public Item updateItem(@RequestBody Item i) {
		return iServ.updateItem(i);
	}
/*-----------------------------Get--------------------------------------*/
	@GetMapping("/")
	public List<Item> getAllItems(){
		List<Item> all = iServ.getAllItems();
		return all;
	}
	
	@GetMapping("/{id}")
	public Item getItem(@PathVariable("id")int id) {
		return iServ.getItemById(id);
	}
	
/*-------------------------Exception Handlers------------------------------*/
	@ExceptionHandler({IAEException.class})
	public ResponseEntity<String> handleIAE(){
		return new ResponseEntity<>("Item already in system", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({NAIException.class})
	public ResponseEntity<String> handleNAI(){
		return new ResponseEntity<>("No such item in system", HttpStatus.NOT_FOUND);
	}
	
	
}
