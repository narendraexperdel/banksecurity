package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.Removevsadd;

public interface RemovevsaddRepository extends JpaRepository<Removevsadd, Integer>,RemovevsaddCustomRespository {

}
