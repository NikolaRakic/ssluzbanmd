package com.studentska.sluzba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>  {
	
	Student findByIdStudent(int id);


}
