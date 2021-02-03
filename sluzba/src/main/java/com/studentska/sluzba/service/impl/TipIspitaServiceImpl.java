package com.studentska.sluzba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.model.TipIspita;
import com.studentska.sluzba.repository.TipIspitaRepository;
import com.studentska.sluzba.service.TipIspitaService;

@Service
public class TipIspitaServiceImpl implements TipIspitaService{

	@Autowired
	TipIspitaRepository tipIspitaRepository;
	
	@Override
	public TipIspita findOne(int id) {
		return tipIspitaRepository.findByIdTipIspita(id);
	}

	@Override
	public List<TipIspita> findAll() {
		System.out.println("udje");
		return tipIspitaRepository.findAll();
	}

}
