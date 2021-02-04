package com.studentska.sluzba.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.dto.request.ProfesorDTO;
import com.studentska.sluzba.dto.request.StudentDTO;
import com.studentska.sluzba.dto.response.ProfesoriDTO;
import com.studentska.sluzba.dto.response.StudentiDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Nastavnik;
import com.studentska.sluzba.model.NastavnikPredaje;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.Uloga;
import com.studentska.sluzba.model.UlogaNastavnik;
import com.studentska.sluzba.repository.KorisnikRepository;
import com.studentska.sluzba.repository.NastavnikPredajeRepository;
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
	NastavnikPredajeRepository nastavnikPredajeRepository;
	
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

		if (request.getIdKorisnik() > 0) { //izmena
			Optional<Korisnik> korisnikZaUpdate = korisnikRepository.findById(request.getIdKorisnik());
			if (!korisnikZaUpdate.isPresent()) {
				throw new Exception("Korisnik ne postojit");
			}
			korisnik = korisnikZaUpdate.get();
		} else { //dodavanje
			Uloga uloga = ulogaRepository.findByNaziv(request.getUloga());
			korisnik.setUloga(uloga);
			korisnik.setPass(configuration.passwordEncoder().encode(request.getPass()));

		}
		Nastavnik nastavnik = new Nastavnik();
		Student student = new Student();
		if (prosledjenaUloga.equals(ULOGA_STUDENT)) {
			System.out.println("student");
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
		//korisnik.setPass(configuration.passwordEncoder().encode(request.getPass()));
		korisnik.setPrezime(request.getPrezime());
		String datumStr = request.getRodjendan();
		Date rodjendan = new SimpleDateFormat(Constants.DATE_FORMAT).parse(datumStr.substring(0,10));
		korisnik.setRodjendan(rodjendan);
		korisnik.setUsername(request.getUsername());

		korisnikRepository.saveAndFlush(korisnik);

		return "Uspesno";
	}

	@Override
	public String izbrisi(int idKorisnika) throws Exception {
		Optional<Korisnik> k = korisnikRepository.findById(idKorisnika);
		if (!k.isPresent()) {
			throw new Exception("Korisnik ne postojit");
		}
		Korisnik kor = k.get();
		Student student = kor.getStudent();
		Nastavnik nastavnik = kor.getNastavnik();
		if(!(student == null)) {
			kor.setObrisan(true);
			student.setObrisan(true);
			korisnikRepository.saveAndFlush(kor);
			studentRepository.saveAndFlush(student);
			return "Uspesno";
		}
		else if(!(nastavnik == null)) {
			kor.setObrisan(true);
			nastavnik.setObrisan(true);
			korisnikRepository.saveAndFlush(kor);
			nastavnikRepository.saveAndFlush(nastavnik);
			return "Uspesno";
		}
		
		
		return "Uspesno";
		
	}

	@Override
	public Korisnik findByUsername(String username) {
		// TODO Auto-generated method stub
		return korisnikRepository.findByUsernameOrEmail(username, username);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public List<StudentiDTO> sviStudenti() throws Exception {
		List<Korisnik> studenti = korisnikRepository.findByStudentNotNullAndObrisanFalse();
		List<StudentiDTO> studentiDTO = new ArrayList<StudentiDTO>();
		for (Korisnik s : studenti) {
			studentiDTO.add(new StudentiDTO(s.getIdKorisnik(), s.getUsername(), s.getEmail(), s.getIme(), s.getPrezime(), s.getStudent().getIdStudent(), s.getStudent().getBrojIndeksa(), s.getStudent().getSmer().getNaziv()));
		}
		
		return studentiDTO;
	}

	@Override
	public List<ProfesoriDTO> sviProfesori() throws Exception {
		List<Korisnik> profesori = korisnikRepository.findByNastavnikNotNullAndObrisanFalse();
		List<ProfesoriDTO> profesoriDTO = new ArrayList<ProfesoriDTO>();
		for (Korisnik p : profesori) {
			profesoriDTO.add(new ProfesoriDTO(p.getIdKorisnik(), p.getNastavnik().getIdNastavnik(), p.getIme(), p.getPrezime(), p.getEmail(), p.getNastavnik().getUlogaNastavnik().getNaziv()));
		}
		
		return profesoriDTO;
	}

	@Override
	public StudentDTO findOneStudent(int id) throws Exception {
		Optional<Korisnik> korisnikOptional = korisnikRepository.findById(id);
		
		if(!korisnikOptional.isPresent()) {
			throw new Exception("Korisnik sa prosledjenim id-om ne postoji");
		}
		Korisnik korisnik = korisnikOptional.get();
		
		Optional<Student> studentOptional = studentRepository.findById(korisnik.getStudent().getIdStudent());
		if(!studentOptional.isPresent()) {
			throw new Exception("Student sa prosledjenim id-om ne postoji");
		}
		Student student = studentOptional.get();
		StudentDTO studentDTO = new StudentDTO(korisnik.getIdKorisnik(), korisnik.getAdresa(), korisnik.getEmail(), korisnik.getIme(), korisnik.getPass(), korisnik.getPrezime(), korisnik.getRodjendan().toString(), korisnik.getUsername(), korisnik.getUloga().getNaziv(), student.getBrojIndeksa(), student.getGodinaStudija(), student.getSmer().getNaziv());
		return studentDTO;
	
	}

	@Override
	public ProfesorDTO findOneNastavnik(int id) throws Exception {
		Optional<Korisnik> korisnikOptional = korisnikRepository.findById(id);
		
		if(!korisnikOptional.isPresent()) {
			throw new Exception("Korisnik sa prosledjenim id-om ne postoji");
		}
		Korisnik korisnik = korisnikOptional.get();
		
		Optional<Nastavnik> nastavnikOptional = nastavnikRepository.findById(korisnik.getNastavnik().getIdNastavnik());
		if(!nastavnikOptional.isPresent()) {
			throw new Exception("Nastavnik sa prosledjenim id-om ne postoji");
		}
		System.out.println("kor ime : " + korisnik.getUsername() );
		Nastavnik nastavnik = nastavnikOptional.get();
		ProfesorDTO profesorDTO = new ProfesorDTO(korisnik.getIdKorisnik(), korisnik.getUloga().getNaziv(), korisnik.getAdresa(), korisnik.getEmail(), korisnik.getIme(), korisnik.getPass(), korisnik.getPrezime(), korisnik.getRodjendan().toString(), korisnik.getUsername(), nastavnik.getIdNastavnik(), nastavnik.getUlogaNastavnik().getNaziv());
		return profesorDTO;
	}

	@Override
	public List<ProfesoriDTO> findAllByPredmetId(int id) throws Exception {
		List<Korisnik> profesori = korisnikRepository.findByNastavnikNotNullAndObrisanFalse();
		List<NastavnikPredaje> nastavnikPredaje = nastavnikPredajeRepository.findByPredmetIdPredmetAndObrisanFalse(id);
		
		List<ProfesoriDTO> profesoriDTO = new ArrayList<ProfesoriDTO>();
		for (Korisnik p : profesori) {
			for (NastavnikPredaje np : nastavnikPredaje) {
				if(np.getNastavnik().getIdNastavnik() == p.getNastavnik().getIdNastavnik()) {
					profesoriDTO.add(new ProfesoriDTO(p.getIdKorisnik(), p.getNastavnik().getIdNastavnik(), p.getIme(), p.getPrezime(), p.getEmail(), p.getNastavnik().getUlogaNastavnik().getNaziv()));				
				}
			}
		
		}
		
		return profesoriDTO;
	}

	@Override
	public List<ProfesoriDTO> findAllByPredmetIdNot(int id) throws Exception {

		List<NastavnikPredaje> nastavnikPredaje = nastavnikPredajeRepository.findByPredmetIdPredmetAndObrisanFalse(id);

		List<Korisnik> sviProfesori = korisnikRepository.findByNastavnikNotNullAndObrisanFalse();

		for(Iterator<Korisnik> prof = sviProfesori.iterator(); prof.hasNext();) {
			Korisnik profesor = prof.next();
			for (NastavnikPredaje np : nastavnikPredaje) {
				if( profesor.getNastavnik().getIdNastavnik() == np.getNastavnik().getIdNastavnik()) {				
						prof.remove();
				}
			}			
		}
		
		List<ProfesoriDTO> profesoriDTO = new ArrayList<ProfesoriDTO>();
		
		for (Korisnik p : sviProfesori) {
			profesoriDTO.add(new ProfesoriDTO(p.getIdKorisnik(), p.getNastavnik().getIdNastavnik(), p.getIme(), p.getPrezime(), p.getEmail(), p.getNastavnik().getUlogaNastavnik().getNaziv()));				
			
		}
		
		return profesoriDTO;
	}

	@Override
	public List<StudentiDTO> sviStudentiZaSmer(int id) {
		List<Korisnik> studenti = korisnikRepository.findByStudentNotNullAndObrisanFalse();
		List<StudentiDTO> studentiDTO = new ArrayList<StudentiDTO>();
		
		for (Korisnik s : studenti) {
			if(s.getStudent().getSmer().getIdSmer() == id) {
				studentiDTO.add(new StudentiDTO(s.getIdKorisnik(), s.getUsername(), s.getEmail(), s.getIme(), s.getPrezime(), s.getStudent().getIdStudent(), s.getStudent().getBrojIndeksa(), s.getStudent().getSmer().getNaziv()));
				
			}
			}
		
		return studentiDTO;
	}

}
