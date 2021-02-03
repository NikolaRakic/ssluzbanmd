package com.studentska.sluzba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Ispit;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.TipIspita;


@Repository
public interface IspitRepository extends JpaRepository<Ispit, Integer>{
	//prvi nacin
	List<Ispit> findAllByObrisanAndTipIspitaAndStudentAndOcenaGreaterThan(boolean obrisan, TipIspita tipIspita, Student student, int ocena);

	List<Ispit> findAllByObrisanFalse();

	List<Ispit> findAllByStudentIdStudentAndObrisanFalse(int idStudent);

	List<Ispit> findAllByPredmetIdPredmetAndObrisanFalse(int idPredmet);
	
//	//drugi nacin
//	@Query(value = "select p.naziv, i.ocena, r.naziv_roka from ispit as i \r\n" + 
//			"join predmet as p on i.predmet_id_predmet = p.id_predmet\r\n" + 
//			"join rok as r on i.rok_id_rok = r.id_rok\r\n" + 
//			"where i.ocena > 5 and i.obrisan = 0 and i.tip_ispita_id_tip_ispita = 1 and i.student_id_student = :idStudent", nativeQuery = true)
//	List<Object[]> pronadjiPolozenePredmeteNative(@Param("idStudent") int idStudent);
}
