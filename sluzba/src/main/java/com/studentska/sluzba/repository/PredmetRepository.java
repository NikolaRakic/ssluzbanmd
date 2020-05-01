package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Predmet;

	@Repository
	public interface PredmetRepository extends JpaRepository<Predmet, Integer>{
}