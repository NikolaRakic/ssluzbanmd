package com.studentska.sluzba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.model.TipIspita;
import com.studentska.sluzba.service.TipIspitaService;

@Service
public class TipIspitaServiceImpl implements TipIspitaService{

	@Autowired
	TipIspitaService tipIspitaService;
	
	@Override
	public TipIspita findOne(int id) {
		return tipIspitaService.findOne(id);
	}

}
