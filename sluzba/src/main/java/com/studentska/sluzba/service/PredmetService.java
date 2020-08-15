package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.dto.request.DodeliPredmetSmeruRequestDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.response.PredmetNaSmeruResponseDTO;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Smer;

public interface PredmetService {

	String kreirajIliIzmeni(PredmetDTO request) throws Exception;

	Predmet findOne(int id);

	List<Predmet> findAll();

	List<PredmetNaSmeruResponseDTO> getPredmetiPoSmeru(int idSmera) throws Exception;

	String dodeliPredmetSmeru(DodeliPredmetSmeruRequestDTO request) throws Exception;
}
