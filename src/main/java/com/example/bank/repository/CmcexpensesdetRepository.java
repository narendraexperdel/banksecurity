package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcexpensesdet;

@Component
public interface CmcexpensesdetRepository extends JpaRepository<Cmcexpensesdet, Integer>, CmcexpensesdetCustomRepository {

}
