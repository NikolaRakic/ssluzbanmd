package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.request.KreirajSmerRequestDTO;

public interface SmerService {
	
	String kreirajIliIzmeni(KreirajSmerRequestDTO request) throws Exception;

}
