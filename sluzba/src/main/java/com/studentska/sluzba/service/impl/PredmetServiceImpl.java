package com.studentska.sluzba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.dto.request.DodeliPredmetSmeruRequestDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.response.PredmetNaSmeruResponseDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Nastavnik;
import com.studentska.sluzba.model.NastavnikPredaje;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.PredmetNaSmeru;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.repository.KorisnikRepository;
import com.studentska.sluzba.repository.NastavnikPredajeRepository;
import com.studentska.sluzba.repository.NastavnikRepository;
import com.studentska.sluzba.repository.PredmetNaSmeruRepository;
import com.studentska.sluzba.repository.PredmetRepository;
import com.studentska.sluzba.repository.SmerRepository;
import com.studentska.sluzba.service.PredmetService;

@Service
public class PredmetServiceImpl implements PredmetService{
	
	@Autowired
	PredmetRepository predmetRepository;
	
	@Autowired
	PredmetNaSmeruRepository predmetNaSmeruRepository;
	
	@Autowired
	SmerRepository smerRepository;
	
	@Autowired
	NastavnikPredajeRepository nastavnikPredajeRepository;
	
	@Autowired
	NastavnikRepository nastavnikRepository;
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Transactional
	@Override
	public String kreirajIliIzmeni(PredmetDTO request) throws Exception {
	
		Predmet predmet = new Predmet ();
	
		if(request.getIdPredmet() > 0) {//izmena
			Optional<Predmet> predmetZaUpdate = predmetRepository.findById(request.getIdPredmet());
			if (!predmetZaUpdate.isPresent()) {
				throw new Exception("Predmet ne postoji");
			}
			else {
				
				predmet = predmetZaUpdate.get();
				predmet.setNaziv(request.getNaziv());
			}
		}
		else{
			predmet.setNaziv(request.getNaziv());
		}
		
		predmetRepository.saveAndFlush(predmet);
		
		return "USPESNO!";
	}
	
	

	@Override
	public Predmet findOne(int id) {
		return predmetRepository.findByIdPredmet(id);
	}

	@Override
	public List<Predmet> findAll() {
		return predmetRepository.findByObrisanIsFalse();
	}

	@Override
	public List<PredmetNaSmeruResponseDTO> getPredmetiPoSmeru(int idSmera) throws Exception{
		// TODO Auto-generated method stub
		Optional<Smer> smerOptional = smerRepository.findById(idSmera);
		if(!smerOptional.isPresent()) {
			throw new Exception("Prosledjen je pogresan id smera");
		}
		Smer smer = smerOptional.get();
		List<PredmetNaSmeru> predmetiNaSmerovima = predmetNaSmeruRepository.findAllBySmerAndObrisan(smer, false);
		List<PredmetNaSmeruResponseDTO> response = new ArrayList<PredmetNaSmeruResponseDTO>();
		for(PredmetNaSmeru pns:predmetiNaSmerovima) {
			PredmetNaSmeruResponseDTO tmpObj = new PredmetNaSmeruResponseDTO(pns.getIdPredmetNaSmeru(), pns.getGodinaPojavljivanja(), pns.isObrisan(), pns.getPredmet().getIdPredmet(), pns.getPredmet().getNaziv(), pns.getPredmet().isObrisan(), pns.getSmer().getIdSmer(), pns.getSmer().getNaziv(), pns.getSmer().getTrajanje(), pns.getSmer().isObrisan());
			response.add(tmpObj);
		}
		return response;
	}

	@Override
	public String dodeliPredmetSmeru(DodeliPredmetSmeruRequestDTO request) throws Exception {
		Optional<Smer> smerOptional = smerRepository.findById(request.getIdSmer());
		if(!smerOptional.isPresent()) {
			throw new Exception("Prosledjen je pogresan id smera");
		}
		Smer smer = smerOptional.get();
		
		Optional<Predmet> predmetOptional = predmetRepository.findById(request.getIdPredmet());
		if(!predmetOptional.isPresent()) {
			throw new Exception("Prosledjen je pogresan id predmeta");
		}
		Predmet predmet = predmetOptional.get();
		int godinaPojavljivanja = request.getGodinaPojavljivanja();
		if(godinaPojavljivanja<1 || godinaPojavljivanja>4) {
			throw new Exception("Godina pojavljivanja mora biti od 1 do 4");
		}
		
		PredmetNaSmeru proveraPostoanja = predmetNaSmeruRepository.findBySmerAndPredmetAndGodinaPojavljivanjaAndObrisan(smer, predmet, godinaPojavljivanja, false);
		if(proveraPostoanja!=null) {
			throw new Exception("Izabrani predmet je vec dodeljen izabranom smeru za izabranu godinu pojavljivanja");
		}
		PredmetNaSmeru novPredmet = new PredmetNaSmeru();
		novPredmet.setGodinaPojavljivanja(godinaPojavljivanja);
		novPredmet.setObrisan(false);
		novPredmet.setPredmet(predmet);
		novPredmet.setSmer(smer);
		
		predmetNaSmeruRepository.save(novPredmet);
		
		return "Predmet je uspesno dodeljen izabranom smeru";
	}

	@Override
	public String izbrisi(int id) {
		Optional<Predmet> predmetOptional = predmetRepository.findById(id);
		
		Predmet predmet = predmetOptional.get();
		
		predmet.setObrisan(true);
		
		predmetRepository.saveAndFlush(predmet);
		
		return "uspesno";
	}



	@Override
	public String izmeniPredmetNaSmeru(List<PredmetNaSmeruResponseDTO> request) throws Exception {
		PredmetNaSmeru predmetNaSmeru = new PredmetNaSmeru();
		for (PredmetNaSmeruResponseDTO predm : request) {
			Optional<PredmetNaSmeru> predmetZaUpdate = predmetNaSmeruRepository.findById(predm.getIdPredmetNaSmeru());
			if (!predmetZaUpdate.isPresent()) {
				throw new Exception("Predmet ne postoji");
			}
			else {
				
				predmetNaSmeru = predmetZaUpdate.get();
				predmetNaSmeru.setGodinaPojavljivanja(predm.getGodinaPojavljivanjaPredmetaNaSmeru());
				
				predmetNaSmeruRepository.saveAndFlush(predmetNaSmeru);
				
			}
		}
		return "uspesno";
		
		
		
	}



	@Override
	public String izbrisiPredmetNaSmeru(int idPredmet, int idSmer) throws Exception {
		PredmetNaSmeru predmetNaSmeru = new PredmetNaSmeru();
			Optional<PredmetNaSmeru> predmetZaUpdate = predmetNaSmeruRepository.findByPredmetIdPredmetAndSmerIdSmerAndObrisan(idPredmet, idSmer, false);
			if (!predmetZaUpdate.isPresent()) {
				throw new Exception("Predmet ne postoji");
			}
			else {
				
				predmetNaSmeru = predmetZaUpdate.get();
				predmetNaSmeru.setObrisan(true);
				
				predmetNaSmeruRepository.saveAndFlush(predmetNaSmeru);
				
			}
		
		return "uspesno";
	}



	@Override
	public List<Predmet> findAllForNastavnik(int id) {
		
		Optional<Korisnik> korisnikOptional = korisnikRepository.findById(id);
		Korisnik korisnik = new Korisnik();
		if(korisnikOptional.isPresent()) {
			korisnik = korisnikOptional.get();

		}
		List<NastavnikPredaje> nastavnikPredaje = nastavnikPredajeRepository.findAllByNastavnikIdNastavnikAndObrisanFalse(korisnik.getNastavnik().getIdNastavnik());
		List<Predmet> predmeti = new ArrayList<Predmet>();
		for (NastavnikPredaje nastavnikP : nastavnikPredaje) {
			Predmet predmet = predmetRepository.findByIdPredmet(nastavnikP.getPredmet().getIdPredmet());
			predmeti.add(predmet);
		}
		return predmeti;
		
	}
	
	
}



