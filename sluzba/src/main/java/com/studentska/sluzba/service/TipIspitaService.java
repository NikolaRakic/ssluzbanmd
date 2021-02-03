package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.model.TipIspita;

public interface TipIspitaService {
	
	TipIspita findOne(int id);

	List<TipIspita> findAll();

}
