package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Uloga;
import com.studentska.sluzba.model.UlogaNastavnik;

@Repository
public interface UlogaNastavnikRepository extends JpaRepository<UlogaNastavnik, Integer>  {
	UlogaNastavnik findByNaziv(String naziv);
}