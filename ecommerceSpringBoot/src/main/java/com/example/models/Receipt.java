package com.example.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="created_By")
	private User user;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="Items_On_Receipt",
			joinColumns = {@JoinColumn(name="receiptNumer")},
			inverseJoinColumns = {@JoinColumn(name="ItemId")}
	)
	private List<Item> items;
	
	private Double total;
	
	@Column(name="created_Date/Time")
	private LocalDateTime dateTime;
	
	//private Integer amountOfItems;
	
	public Receipt(User user, List<Item> items, Double total) {
		this.user = user;
		this.items = items;
		this.total = total;
		this.dateTime = LocalDateTime.now();
	}
}
