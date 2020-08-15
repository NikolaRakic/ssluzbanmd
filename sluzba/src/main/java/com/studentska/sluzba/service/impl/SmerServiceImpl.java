package com.studentska.sluzba.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.KreirajSmerRequestDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.service.SmerService;

@Service
public class SmerServiceImpl implements SmerService{

	
	@Autowired
	SmerRepository smerRepository;
	
	
	@Transactional // omogucuje rollback u bazi pri pucanju
	@Override
	public String kreirajIliIzmeni(KreirajSmerRequestDTO request) throws Exception {
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

}
