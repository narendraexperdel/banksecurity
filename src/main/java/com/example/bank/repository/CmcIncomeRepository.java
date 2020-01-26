package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcIncome;

@Component
public interface CmcIncomeRepository extends JpaRepository<CmcIncome, Integer>, CmcIncomeCustomRepository{

}
