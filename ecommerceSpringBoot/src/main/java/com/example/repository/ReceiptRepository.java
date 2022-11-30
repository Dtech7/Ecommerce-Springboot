package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
	List<Receipt>
}
