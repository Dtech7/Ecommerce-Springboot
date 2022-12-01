package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Receipt;
import com.example.models.User;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
	List<Receipt> getReceiptsByUser(User u);
}
