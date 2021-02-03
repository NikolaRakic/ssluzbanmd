package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.model.Rok;

public interface RokService {
	
	Rok findOne(int id);

	List<Rok> getRokovi();
	
	String sacuvajRokove(List<Rok> request) throws Exception;

	List<Rok> getAktivniRokovi();

}
