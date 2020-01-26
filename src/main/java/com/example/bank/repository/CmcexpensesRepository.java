package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcexpenses;

@Component
public interface CmcexpensesRepository extends JpaRepository<Cmcexpenses, Integer>,CmcexpensesCustomRepository {

}
