package com.studentska.sluzba.service;

import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.NastavnikPredajeDTO;
import com.studentska.sluzba.model.NastavnikPredaje;

@Service
public interface NastavnikPredajeService {
	String addNastavnikPredaje(NastavnikPredajeDTO request) throws Exception;
	
	String obrisiNastavnikPredaje(NastavnikPredajeDTO request) throws Exception;
}
