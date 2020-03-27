package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.ProductClosing;

@Component
public interface ProductClosingRepository extends JpaRepository<ProductClosing, Integer>,ProductClosingCustomRepository {

}
