package com.studentska.sluzba.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.KreirajPredmetRequestDTO;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.service.PredmetService;

@Service
public class PredmetServiceImpl implements PredmetService{
	
	@Autowired
	PredmetRepository predmetRepository;
	
	@Transactional
	@Override
	public String kreirajIliIzmeni(KreirajPredmetRequestDTO request) throws Exception {
	
		Predmet predmet = new Predmet ();
	
		if(request.getIdPredmet() > 0) {//izmena
			Optional<Predmet> smerZaUpdate = predmetRepository.findById(request.getIdPredmet());
			if (!smerZaUpdate.isPresent()) {
				throw new Exception("Predmet ne postoji");
			}
			else {
				
				predmet = smerZaUpdate.get();
				predmet.setNaziv(request.getNaziv());
			}
		}
		else{
			predmet.setNaziv(request.getNaziv());
		}
		
		predmetRepository.saveAndFlush(predmet);
		
		return "USPESNO!";
	}
	
	
}



