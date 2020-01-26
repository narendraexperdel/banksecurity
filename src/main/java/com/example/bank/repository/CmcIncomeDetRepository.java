package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcIncomedet;

@Component
public interface CmcIncomeDetRepository extends JpaRepository<CmcIncomedet, Integer>, CmcIncomeDetCustomRepository {

}
