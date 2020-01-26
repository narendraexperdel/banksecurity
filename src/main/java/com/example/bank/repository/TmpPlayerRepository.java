package com.example.bank.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.TmpPlayer;

@Component
public interface TmpPlayerRepository extends JpaRepository<TmpPlayer, Integer>, TmpPlayerCustomRepository{

	
	
}
