package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcProduct;

@Component
public interface CmcProductRepository extends JpaRepository<CmcProduct, Integer>,CmcProductCustomRepository {

}
