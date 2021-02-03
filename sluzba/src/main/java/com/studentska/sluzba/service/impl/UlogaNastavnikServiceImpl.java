package com.studentska.sluzba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.response.UlogaNastavnikDTO;
import com.studentska.sluzba.model.UlogaNastavnik;
import com.studentska.sluzba.repository.UlogaNastavnikRepository;
import com.studentska.sluzba.service.UlogaNastavnikService;

@Service
public class UlogaNastavnikServiceImpl implements UlogaNastavnikService{
	
	@Autowired
	UlogaNastavnikRepository ulogaNastavnikRepository;

	@Override
	public List<UlogaNastavnikDTO> findAll() {
		List<UlogaNastavnik> uloge = ulogaNastavnikRepository.findAll();
	
		List<UlogaNastavnikDTO> ulogeDTO = new ArrayList<UlogaNastavnikDTO>();
		
		for (UlogaNastavnik uloga : uloge) {
			ulogeDTO.add(new UlogaNastavnikDTO(uloga.getIdUlogaNastavnik(), uloga.getNaziv()));
		}
		return ulogeDTO;
	}

}
