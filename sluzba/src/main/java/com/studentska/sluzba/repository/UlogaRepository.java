package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.studentska.sluzba.model.Uloga;

@Repository
public interface UlogaRepository extends JpaRepository<Uloga, Integer>  {
	Uloga findByNaziv(String naziv);
}
