package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.WemUser;

public interface WemUserRepository extends JpaRepository<WemUser, Integer>,WemUserCustomRepository{

}
