package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Expensestype;

@Component
public interface ExpensestypeRepository extends JpaRepository<Expensestype, Integer>, ExpensestypeCustomRepository{

}
