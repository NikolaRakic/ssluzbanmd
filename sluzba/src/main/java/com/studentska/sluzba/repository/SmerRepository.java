package com.studentska.sluzba.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Smer;

@Repository
public interface SmerRepository extends JpaRepository<Smer, Integer>{
	Smer findByNaziv(String naziv);
	Smer findByidSmer(int id);
	ArrayList<Smer> findByObrisanIsFalse();
	 
}
