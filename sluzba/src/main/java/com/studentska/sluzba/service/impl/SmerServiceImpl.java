package com.studentska.sluzba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.SmerDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.PredmetNaSmeru;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.repository.PredmetNaSmeruRepository;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.service.SmerService;

@Service
public class SmerServiceImpl implements SmerService{

	
	@Autowired
	SmerRepository smerRepository;
	
	@Autowired
	PredmetNaSmeruRepository predmetNaSmeruRepository;
	
	
	@Transactional // omogucuje rollback u bazi pri pucanju
	@Override
	public String kreirajIliIzmeni(SmerDTO request) throws Exception {
		Smer smer = new Smer();
		
		
		if(request.getId() > 0) {//izmena
			Optional<Smer> smerZaUpdate = smerRepository.findById(request.getId());
			if (!smerZaUpdate.isPresent()) {
				throw new Exception("Smer ne postoji");
			}
			else {
				
			}
				smer = smerZaUpdate.get();
				smer.setNaziv(request.getNaziv());
				smer.setTrajanje(request.getTrajanje());
		}
		else{
			smer.setNaziv(request.getNaziv());
			smer.setTrajanje(request.getTrajanje());
		}
		
		smerRepository.saveAndFlush(smer);
		
		return "USPESNO!";
	}


	@Override
	public String izbrisi(int idSmer) throws Exception {
		Optional<Smer> smerOptional = smerRepository.findById(idSmer);
		
		Smer smer = smerOptional.get();
		
		smer.setObrisan(true);
		
		smerRepository.save(smer);
		
		return "Uspesno";
		
	}


	@Override
	public Smer findOne(int id) {
		return smerRepository.findById(id).get();
	}


	@Override
	public List<Smer> findAll() {
		return smerRepository.findByObrisanIsFalse();
	}


	@Override
	public Smer findOneByNaziv(String naziv) {
		return smerRepository.findByNaziv(naziv);
	}


	@Override
	public List<Smer> findAllByPredmet(int id) {
		List<PredmetNaSmeru> predmetNaSmeru = predmetNaSmeruRepository.findAllByPredmetIdPredmetAndObrisanFalse(id);
		List<Smer> smerovi = new ArrayList<Smer>();
		for (PredmetNaSmeru pns : predmetNaSmeru) {
			Smer smer = smerRepository.findByidSmer(pns.getSmer().getIdSmer());
			smerovi.add(smer);
		}
		return smerovi;
	}

}
