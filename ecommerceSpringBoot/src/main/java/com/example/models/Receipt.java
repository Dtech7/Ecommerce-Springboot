package com.example.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="receipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="receipt_number")
	private Integer receiptNumer;
	
	@Column(name="created_By")
	private User user;
	
	private List<Item> items;
	
	private Double total;
}
