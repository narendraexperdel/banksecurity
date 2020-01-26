package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.TelePending;

@Component
public interface TelePendingRepository extends JpaRepository<TelePending, Integer>,TelePendingCustomRepository{

}
