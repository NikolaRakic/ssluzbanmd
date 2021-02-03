package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.dto.request.DodeliPredmetSmeruRequestDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.response.PredmetNaSmeruResponseDTO;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Smer;

public interface PredmetService {

	String kreirajIliIzmeni(PredmetDTO request) throws Exception;
	
	String izbrisi(int id);

	Predmet findOne(int id);

	List<Predmet> findAll();

	List<PredmetNaSmeruResponseDTO> getPredmetiPoSmeru(int idSmera) throws Exception;

	String dodeliPredmetSmeru(DodeliPredmetSmeruRequestDTO request) throws Exception;

	String izmeniPredmetNaSmeru(List<PredmetNaSmeruResponseDTO> request) throws Exception;
	
	String izbrisiPredmetNaSmeru(int idPredmet, int idSmer) throws Exception;

	List<Predmet> findAllForNastavnik(int id);
}
