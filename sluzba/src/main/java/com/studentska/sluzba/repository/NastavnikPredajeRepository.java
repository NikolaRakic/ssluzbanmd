package com.studentska.sluzba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.NastavnikPredaje;

@Repository
public interface NastavnikPredajeRepository extends JpaRepository<NastavnikPredaje, Integer> {
	
	List<NastavnikPredaje> findByPredmetIdPredmetAndObrisanFalse(int id);

	NastavnikPredaje findByPredmetIdPredmetAndNastavnikIdNastavnikAndObrisan(int idPredmet, int idNastavnik, boolean obrisan);

	List<NastavnikPredaje> findByPredmetIdPredmetNotAndObrisanFalse(int id);

	List<NastavnikPredaje> findAllByNastavnikIdNastavnikAndObrisanFalse(int id);
		
	
}
