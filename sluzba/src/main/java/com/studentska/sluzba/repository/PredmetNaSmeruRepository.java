package com.studentska.sluzba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.PredmetNaSmeru;
import com.studentska.sluzba.model.Smer;

@Repository
public interface PredmetNaSmeruRepository extends JpaRepository<PredmetNaSmeru, Integer>{
	
	List<PredmetNaSmeru> findAllBySmerAndObrisan(Smer smer, boolean obrisan);
	
	PredmetNaSmeru findBySmerAndPredmetAndGodinaPojavljivanjaAndObrisan(Smer smer, Predmet predmet, int godinaPojavljivanja, boolean obrisan);
	
	Optional<PredmetNaSmeru> findByPredmetIdPredmetAndSmerIdSmer(int idPredmet, int idSmer);

	List<PredmetNaSmeru> findAllByPredmetIdPredmetAndObrisanFalse(int id);
}
