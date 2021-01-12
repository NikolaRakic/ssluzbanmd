package com.studentska.sluzba.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.repository.KorisnikRepository;
import com.studentska.sluzba.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentService studentService;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Override
	public Student findOne(int id) {
		return studentService.findOne(id);
	}
	


}
