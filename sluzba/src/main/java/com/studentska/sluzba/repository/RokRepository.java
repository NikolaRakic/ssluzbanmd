package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Rok;

@Repository
public interface RokRepository extends JpaRepository<Rok, Integer>{
	
	Rok findByIdRok(int id);

}
