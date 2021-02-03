package com.studentska.sluzba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentska.sluzba.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	Korisnik findByUsernameOrEmail(String username,String email);

	List<Korisnik> findByStudentNotNullAndObrisanFalse();

	List<Korisnik> findByNastavnikNotNullAndObrisanFalse();

	Optional<Korisnik> findByStudentIdStudent(int idStudent);

	List<Korisnik> findByNastavnikIdNastavnikNotAndObrisanFalse(int idNastavnik);

	Korisnik findByIdKorisnik(int id);
}
