package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Profiledata;

@Component
public interface ProfiledataRepository extends JpaRepository<Profiledata, Integer>,ProfiledataCustomRepository {

}
