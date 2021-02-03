package com.studentska.sluzba.service;

import java.util.List;

import com.studentska.sluzba.dto.response.UlogaNastavnikDTO;
import com.studentska.sluzba.model.UlogaNastavnik;

public interface UlogaNastavnikService {
	
	List<UlogaNastavnikDTO> findAll();

}
