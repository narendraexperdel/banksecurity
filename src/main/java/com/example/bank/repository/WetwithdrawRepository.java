package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Wetwithdraw;

@Component
public interface WetwithdrawRepository extends JpaRepository<Wetwithdraw, Integer>,WetwithdrawCutomRepository {

}
