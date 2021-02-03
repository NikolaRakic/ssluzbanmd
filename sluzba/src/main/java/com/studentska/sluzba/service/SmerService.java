package com.studentska.sluzba.service;


import java.util.List;

import com.studentska.sluzba.dto.request.SmerDTO;
import com.studentska.sluzba.model.Smer;

public interface SmerService {
	
	String kreirajIliIzmeni(SmerDTO request) throws Exception;

	String izbrisi(int idSmer) throws Exception;

	Smer findOne(int id);

	List<Smer> findAll();

	Smer findOneByNaziv(String naziv);

	List<Smer> findAllByPredmet(int id);
	
}
