package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.CmcSources;

@Component
public interface CmcsourcesRepository extends JpaRepository<CmcSources, Integer>,CmcsourcesCustomRepository {

}
