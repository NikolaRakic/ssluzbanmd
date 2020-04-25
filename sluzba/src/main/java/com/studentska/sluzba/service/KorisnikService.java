package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.request.KreirajKorisnikaRequestDTO;

public interface KorisnikService {

	String kreirajIliIzmeni(KreirajKorisnikaRequestDTO request) throws Exception;

}
