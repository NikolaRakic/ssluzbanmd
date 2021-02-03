package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.TipIspita;

@Repository
public interface TipIspitaRepository extends JpaRepository<TipIspita, Integer>{
	
	TipIspita findByIdTipIspita(int id);

	//TipIspita findOne(int id);

}
