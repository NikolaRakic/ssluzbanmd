package com.studentska.sluzba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.NastavnikPredajeDTO;
import com.studentska.sluzba.model.Nastavnik;
import com.studentska.sluzba.model.NastavnikPredaje;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.repository.NastavnikPredajeRepository;
import com.studentska.sluzba.repository.NastavnikRepository;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.service.NastavnikPredajeService;

@Service
public class NastavnikPredajeServiceImpl implements NastavnikPredajeService{

	@Autowired
	NastavnikPredajeRepository nastavnikPredajeRepository;
	
	@Autowired
	NastavnikRepository nastavnikRepository;
	
	@Autowired
	PredmetRepository predmetRepository;
	
	@Override
	public String addNastavnikPredaje(NastavnikPredajeDTO request) throws Exception {
		NastavnikPredaje noviNastavnikNaPredmetu = new NastavnikPredaje();
		Nastavnik nastavnik = nastavnikRepository.getOne(request.getIdNastavnik()); 
		noviNastavnikNaPredmetu.setNastavnik(nastavnik);
		Predmet predmet = predmetRepository.findByIdPredmet(request.getIdPredmet());
		noviNastavnikNaPredmetu.setPredmet(predmet);
		try {
			nastavnikPredajeRepository.saveAndFlush(noviNastavnikNaPredmetu);
		}catch (Exception ex) {
			System.out.println(ex);
		}
		
		
		return "Uspesno";
	}

	@Override
	public String obrisiNastavnikPredaje(NastavnikPredajeDTO request) throws Exception {
		NastavnikPredaje np = nastavnikPredajeRepository.findByPredmetIdPredmetAndNastavnikIdNastavnikAndObrisan(request.getIdPredmet(), request.getIdNastavnik(), false);
		np.setObrisan(true);
		nastavnikPredajeRepository.saveAndFlush(np);
		return "uspesno";
	}

}
