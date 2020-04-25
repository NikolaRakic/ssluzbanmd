package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Smer;

@Repository
public interface SmerRepository extends JpaRepository<Smer, Integer>{
	Smer findByNaziv(String naziv);
}
