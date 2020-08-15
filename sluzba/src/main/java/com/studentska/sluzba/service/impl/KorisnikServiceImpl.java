package com.studentska.sluzba.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Nastavnik;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.Uloga;
import com.studentska.sluzba.model.UlogaNastavnik;
import com.studentska.sluzba.repository.KorisnikRepository;
import com.studentska.sluzba.repository.NastavnikRepository;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.repository.UlogaNastavnikRepository;
import com.studentska.sluzba.repository.UlogaRepository;
import com.studentska.sluzba.security.SecurityConfiguration;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.KorisnikService;
import com.studentska.sluzba.util.Constants;

@Service
public class KorisnikServiceImpl implements KorisnikService {

	private static final String ULOGA_NASTAVNIK = "nastavnik";
	private static final String ULOGA_STUDENT = "student";
	private static final String ULOGA_ADMIN = "admin";

	private static final String ULOGA_NASTAVNIK_PROFESOR = "profesor";
	private static final String ULOGA_NASTAVNIK_ASISTENT = "asistent";
	private static final String ULOGA_NASTAVNIK_DEMONSTRATOR = "demonstrator";

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	NastavnikRepository nastavnikRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UlogaRepository ulogaRepository;

	@Autowired
	UlogaNastavnikRepository ulogaNastavnikRepository;

	@Autowired
	SmerRepository smerRepository;
	
	@Autowired
	SecurityConfiguration configuration;
	
	@Autowired
	TokenUtils tokenUtils;

	@Transactional // omogucuje rollback u bazi pri pucanju
	@Override
	public String kreirajIliIzmeni(KorisnikDTO request) throws Exception {
		// TODO Auto-generated method stub
		Korisnik korisnik = new Korisnik();
		String prosledjenaUloga = request.getUloga();
		if (prosledjenaUloga.equals(ULOGA_ADMIN)) {
			throw new Exception("Bad request");
		}

		if (request.getIdKorisnik() > 0) {
			Optional<Korisnik> korisnikZaUpdate = korisnikRepository.findById(request.getIdKorisnik());
			if (!korisnikZaUpdate.isPresent()) {
				throw new Exception("Korisnik ne postojit");
			}
			korisnik = korisnikZaUpdate.get();
		} else {
			Uloga uloga = ulogaRepository.findByNaziv(request.getUloga());
			korisnik.setUloga(uloga);

		}
		Nastavnik nastavnik = new Nastavnik();
		Student student = new Student();
		if (prosledjenaUloga.equals(ULOGA_STUDENT)) {

			if (korisnik.getStudent() != null) {
				student = korisnik.getStudent();
			}
			Smer smer = smerRepository.findByNaziv(request.getSmerStudenta());
			if (smer == null) {
				throw new Exception("Nepostojeci smer");
			}
			student.setSmer(smer);
			student.setBrojIndeksa(request.getBrojIndeksa());
			student.setGodinaStudija(request.getGodinaStudija());
			studentRepository.saveAndFlush(student);
			korisnik.setStudent(student);
		} else if (prosledjenaUloga.equals(ULOGA_NASTAVNIK)) {
			if (korisnik.getNastavnik() != null) {
				nastavnik = korisnik.getNastavnik();
			}
			UlogaNastavnik ulogaNastavnik = ulogaNastavnikRepository.findByNaziv(request.getUlogaNastavnik());
			if (ulogaNastavnik == null) {
				throw new Exception("Nepostojeca uloga nastavnik");
			}
			nastavnik.setUlogaNastavnik(ulogaNastavnik);

			nastavnikRepository.saveAndFlush(nastavnik);
			korisnik.setNastavnik(nastavnik);
		} else {
			throw new Exception("Nepoznata uloga");
		}

		korisnik.setAdresa(request.getAdresa());
		korisnik.setEmail(request.getEmail());
		korisnik.setIme(request.getIme());
		korisnik.setPass(configuration.passwordEncoder().encode(request.getPass()));
		korisnik.setPrezime(request.getPrezime());
		String datumStr = request.getRodjendan();
		// "31/12/1998"; <- koristite ovaj format u celoj aplikaciji
		Date rodjendan = new SimpleDateFormat(Constants.DATE_FORMAT).parse(datumStr);
		korisnik.setRodjendan(rodjendan);
		korisnik.setUsername(request.getUsername());

		korisnikRepository.saveAndFlush(korisnik);

		return "Uspesno";
	}

	@Override
	public void izbrisi(int idKorisnika) throws Exception {
		Optional<Korisnik> k = korisnikRepository.findById(idKorisnika);
		if (!k.isPresent()) {
			throw new Exception("Korisnik ne postojit");
		}
		Korisnik kor = k.get();
		korisnikRepository.delete(kor);
		
	}

	@Override
	public Korisnik findByUsername(String username) {
		// TODO Auto-generated method stub
		return korisnikRepository.findByUsernameOrEmail(username, username);
	}

}
