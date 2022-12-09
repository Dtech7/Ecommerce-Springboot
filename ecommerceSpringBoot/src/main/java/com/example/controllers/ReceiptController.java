package com.example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.exceptions.NSRException;
import com.example.models.Item;
import com.example.models.Receipt;
import com.example.services.ReceiptService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/receipt")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ReceiptController {
	
	private ReceiptService rServ;
	
/*---------------------------------Create-------------------------------------------*/	

	@PostMapping("/create")
	public Receipt createReceipt(@RequestBody NewReceiptObject body) {
		return rServ.createReceipt(body.email, body.items, body.total);
	}
	
/*------------------------------Update------------------------------------------*/

	@PutMapping("/update")
	public Receipt updateTicket(@RequestBody Receipt r) {
		return rServ.updateReceipt(r);
	}
	
/*-------------------------------Get---------------------------------------------*/
	
	@GetMapping("/")
	public List<Receipt> getAllByEmail(@RequestParam("email")String email){
		return rServ.getAllReceipt(email);
	}

/*---------------------------Exception Handlers--------------------------------*/	
	@ExceptionHandler({NSRException.class})
	public ResponseEntity<String> handleNSR(){
		return new ResponseEntity<>("Receipt not in system", HttpStatus.NOT_FOUND);
	}
}

class NewReceiptObject{
	public String email;
	public List<Item> items;
	public Double total;
}
