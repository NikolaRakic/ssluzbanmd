package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.request.KreirajPredmetRequestDTO;

public interface PredmetService {

	String kreirajIliIzmeni(KreirajPredmetRequestDTO request) throws Exception;
}
