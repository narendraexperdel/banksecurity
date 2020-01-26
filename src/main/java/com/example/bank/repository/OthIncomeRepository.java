package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.OthIncome;

@Component
public interface OthIncomeRepository extends JpaRepository<OthIncome, Integer>,OthIncomeCustomRepository {

}
