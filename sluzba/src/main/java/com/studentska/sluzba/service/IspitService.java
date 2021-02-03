package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.dto.request.IspitIliKolokvijumDTO;
import com.studentska.sluzba.dto.response.IspitiDTO;
import com.studentska.sluzba.dto.response.PolozenIspitResponseDTO;

public interface IspitService {

	//List<PolozenIspitResponseDTO> polozeniIspitiZaStudenta(int idStudenta) throws Exception;

	List<PolozenIspitResponseDTO> polozeniIspitiZaStudentaOptimized(int idStudenta) throws Exception;
	
	String unosIspitaIliKolokvijuma(IspitIliKolokvijumDTO request) throws Exception;

	List<IspitiDTO> getAll();

	List<IspitiDTO> getAllByStudentOrNastavnik(int id);

}
