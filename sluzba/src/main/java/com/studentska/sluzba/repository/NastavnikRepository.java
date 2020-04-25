package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Nastavnik;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Integer>  {

}
