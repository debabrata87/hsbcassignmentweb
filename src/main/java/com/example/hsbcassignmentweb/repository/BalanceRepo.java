package com.example.hsbcassignmentweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hsbcassignmentweb.model.Balance;


public interface BalanceRepo extends JpaRepository<Balance, Integer>{

	List<Balance> findByCurrency(String currency);
}
