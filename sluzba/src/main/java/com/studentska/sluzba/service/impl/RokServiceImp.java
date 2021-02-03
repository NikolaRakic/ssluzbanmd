package com.studentska.sluzba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.model.Rok;
import com.studentska.sluzba.repository.RokRepository;
import com.studentska.sluzba.service.RokService;

@Service
public class RokServiceImp implements RokService{

	@Autowired
	RokRepository rokRepository;
	
	
	@Override
	public Rok findOne(int id) {
		return rokRepository.findByIdRok(id);
	}


	@Override
	public List<Rok> getRokovi() {
		return rokRepository.findAll();
	}


	@Override
	public String sacuvajRokove(List<Rok> request) throws Exception {
		rokRepository.saveAll(request);
		return "Uspesno";
	}


	@Override
	public List<Rok> getAktivniRokovi() {
		return rokRepository.findAllByAktivanTrue();
	}

}
