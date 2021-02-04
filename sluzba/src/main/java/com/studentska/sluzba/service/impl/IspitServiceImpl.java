package com.studentska.sluzba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.IspitIliKolokvijumDTO;
import com.studentska.sluzba.dto.response.IspitiDTO;
import com.studentska.sluzba.dto.response.PolozenIspitResponseDTO;
import com.studentska.sluzba.model.Ispit;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.NastavnikPredaje;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Rok;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.model.TipIspita;
import com.studentska.sluzba.repository.IspitRepository;
import com.studentska.sluzba.repository.KorisnikRepository;
import com.studentska.sluzba.repository.NastavnikPredajeRepository;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.repository.RokRepository;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.repository.TipIspitaRepository;
import com.studentska.sluzba.service.IspitService;

@Service
public class IspitServiceImpl implements IspitService {
	
	private final static boolean NIJE_OBRISAN_FLAG = false;
	private final static int TIP_ISPITA_ISPIT = 1;
	private final static int MINIMALNA_OCENA_ZA_PROLAZ = 5;
	
	@Autowired
	IspitRepository ispitRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TipIspitaRepository tipIspitaRepository;
	
	@Autowired
	PredmetRepository predmetRepository;
	
	@Autowired
	RokRepository rokRepository;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	NastavnikPredajeRepository nastavnikPredajeRepository;
	
	
	//Drugi nacin
//	@Override
//	public List<PolozenIspitResponseDTO> polozeniIspitiZaStudenta(int idStudenta) throws Exception {
//		// TODO Auto-generated method stub
//		Optional<Student> studentOptional = studentRepository.findById(idStudenta);
//		if(!studentOptional.isPresent()) {
//			throw new Exception("Student sa prosledjenim id-om ne postoji");
//		}
//		List<Object[]> nativeResponse = ispitRepository.pronadjiPolozenePredmeteNative(idStudenta);
//		List<PolozenIspitResponseDTO> response = new ArrayList<PolozenIspitResponseDTO>();
//		for(Object[] obj:nativeResponse) {
//			PolozenIspitResponseDTO tmpObj = new PolozenIspitResponseDTO(obj[0].toString(),Integer.parseInt(obj[1].toString()), obj[2].toString());
//			response.add(tmpObj);
//		}
//		return response;
//	}

	//prvi nacin
	@Override
	public List<PolozenIspitResponseDTO> polozeniIspitiZaStudentaOptimized(int idStudenta) throws Exception {
		Optional<Korisnik> korisnikOptional = korisnikRepository.findById(idStudenta);
		Korisnik korisnik = korisnikOptional.get();
		Optional<Student> studentOptional = studentRepository.findById(korisnik.getStudent().getIdStudent());
		if(!studentOptional.isPresent()) {
			throw new Exception("Student sa prosledjenim id-om ne postoji");
		}
		Student student = studentOptional.get();
		
		Optional<TipIspita> tipIspitaOptional = tipIspitaRepository.findById(TIP_ISPITA_ISPIT);
		if(!tipIspitaOptional.isPresent()) {
			throw new Exception("Tip ispita ISPIT ne postoji u bazi ili nije dobro podesen parametar u kodu");
		}
		TipIspita tipIspita = tipIspitaOptional.get();
		
		List<Ispit> listaPolozenihIspitaZaStudenta = ispitRepository.findAllByObrisanAndTipIspitaAndStudentAndOcenaGreaterThan(NIJE_OBRISAN_FLAG, tipIspita, student, MINIMALNA_OCENA_ZA_PROLAZ);
		List<PolozenIspitResponseDTO> response = new ArrayList<PolozenIspitResponseDTO>();
		for(Ispit i:listaPolozenihIspitaZaStudenta) {
			PolozenIspitResponseDTO tmpObj = new PolozenIspitResponseDTO(i.getPredmet().getNaziv(), i.getOcena(), i.getRok().getNazivRoka());
			response.add(tmpObj);
		}
		
		return response;
	}

	@Override
	public String unosIspitaIliKolokvijuma(IspitIliKolokvijumDTO request) throws Exception {
		
		Ispit ispit = new Ispit();
		
		Predmet predmet = predmetRepository.findByIdPredmet(request.getIdPredmet());
		
		Rok rok = rokRepository.findByIdRok(request.getIdRok());

		Student student = studentRepository.findByIdStudent(request.getIdStudent());
		
		TipIspita tipIspita = tipIspitaRepository.findByIdTipIspita(request.getIdTipIspita());
		
		//kolokvijum
		if(request.getIdTipIspita() == 2) {
			
			ispit.setBodovi(request.getBodovi());
			ispit.setDatum(request.getDatumIspita());
			ispit.setPredmet(predmet);
			ispit.setRok(rok);
			ispit.setOcena(0);
			ispit.setStudent(student);
			ispit.setTipIspita(tipIspita);
			
			ispitRepository.saveAndFlush(ispit);
			
			
		}
		//ispit
		else if(request.getOcena() != null){
			ispit.setBodovi(request.getBodovi());
			ispit.setDatum(request.getDatumIspita());
			ispit.setPredmet(predmet);
			ispit.setRok(rok);
			ispit.setOcena(request.getOcena());
			ispit.setStudent(student);
			ispit.setTipIspita(tipIspita);
			
			ispitRepository.saveAndFlush(ispit);
		}
		else {
			throw new Exception("Nije dobar Json");
		}
		return null;
	}

	@Override
	public List<IspitiDTO> getAll() {
		List<Ispit> ispiti = ispitRepository.findAllByObrisanFalse();
		
		List<IspitiDTO> ispitiDTO = new ArrayList<IspitiDTO>();
		
		for (Ispit ispit : ispiti) {
			ispitiDTO.add(new IspitiDTO(ispit.getStudent().getBrojIndeksa(), ispit.getPredmet().getNaziv(), ispit.getRok().getNazivRoka(), ispit.getTipIspita().getNazivTipa(), ispit.getDatum().toString(), ispit.getOcena(), ispit.getBodovi()));
		}
		return ispitiDTO;
	}

	@Override
	public List<IspitiDTO> getAllByStudentOrNastavnik(int id) {
		Korisnik ulogovaniKorisnik = korisnikRepository.findByIdKorisnik(id);
		List<Ispit> ispiti = new ArrayList<Ispit>();
		List<IspitiDTO> ispitiDTO = new ArrayList<IspitiDTO>(); 
		
		if(ulogovaniKorisnik.getStudent() != null) {
			ispiti = ispitRepository.findAllByStudentIdStudentAndObrisanFalse(ulogovaniKorisnik.getStudent().getIdStudent());
		}
		else if(ulogovaniKorisnik.getNastavnik() != null) {
			List<NastavnikPredaje> nastavnikPredaje = nastavnikPredajeRepository.findAllByNastavnikIdNastavnikAndObrisanFalse(ulogovaniKorisnik.getNastavnik().getIdNastavnik());
			for (NastavnikPredaje np : nastavnikPredaje) {
				List<Ispit> ispitiNp = ispitRepository.findAllByPredmetIdPredmetAndObrisanFalse(np.getPredmet().getIdPredmet());
				ispiti.addAll(ispitiNp);
			}
			
		}
		for (Ispit ispit : ispiti) {
			ispitiDTO.add(new IspitiDTO(ispit.getStudent().getBrojIndeksa(), ispit.getPredmet().getNaziv(), ispit.getRok().getNazivRoka(), ispit.getTipIspita().getNazivTipa(), ispit.getDatum().toString(), ispit.getOcena(), ispit.getBodovi()));
		}
		return ispitiDTO;
		
	}


}
