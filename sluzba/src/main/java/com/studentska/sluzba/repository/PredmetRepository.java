package com.studentska.sluzba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Predmet;

	@Repository
	public interface PredmetRepository extends JpaRepository<Predmet, Integer>{
		Predmet findByIdPredmet(int id);
		List<Predmet> findByObrisanIsFalse();
		
}
