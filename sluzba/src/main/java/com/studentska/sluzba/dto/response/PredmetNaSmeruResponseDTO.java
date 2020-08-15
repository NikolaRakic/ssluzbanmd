package com.studentska.sluzba.dto.response;

import javax.persistence.Column;

public class PredmetNaSmeruResponseDTO {
	private int idPredmetNaSmeru;
	private int godinaPojavljivanjaPredmetaNaSmeru;
	private boolean obrisanPredmetNaSmeru;
	//
	private int idPredmet;
	private String nazivPredmeta;
	private boolean obrisanPredmet;
	//
	private int idSmer;
	private String nazivSmera;
	private int trajanjeSmera;
	private boolean obrisanSmer;
	
	public PredmetNaSmeruResponseDTO(int idPredmetNaSmeru, int godinaPojavljivanjaPredmetaNaSmeru,
			boolean obrisanPredmetNaSmeru, int idPredmet, String nazivPredmeta, boolean obrisanPredmet, int idSmer,
			String nazivSmera, int trajanjeSmera, boolean obrisanSmer) {
		super();
		this.idPredmetNaSmeru = idPredmetNaSmeru;
		this.godinaPojavljivanjaPredmetaNaSmeru = godinaPojavljivanjaPredmetaNaSmeru;
		this.obrisanPredmetNaSmeru = obrisanPredmetNaSmeru;
		this.idPredmet = idPredmet;
		this.nazivPredmeta = nazivPredmeta;
		this.obrisanPredmet = obrisanPredmet;
		this.idSmer = idSmer;
		this.nazivSmera = nazivSmera;
		this.trajanjeSmera = trajanjeSmera;
		this.obrisanSmer = obrisanSmer;
	}
	public int getIdPredmetNaSmeru() {
		return idPredmetNaSmeru;
	}
	public void setIdPredmetNaSmeru(int idPredmetNaSmeru) {
		this.idPredmetNaSmeru = idPredmetNaSmeru;
	}
	public int getGodinaPojavljivanjaPredmetaNaSmeru() {
		return godinaPojavljivanjaPredmetaNaSmeru;
	}
	public void setGodinaPojavljivanjaPredmetaNaSmeru(int godinaPojavljivanjaPredmetaNaSmeru) {
		this.godinaPojavljivanjaPredmetaNaSmeru = godinaPojavljivanjaPredmetaNaSmeru;
	}
	public boolean isObrisanPredmetNaSmeru() {
		return obrisanPredmetNaSmeru;
	}
	public void setObrisanPredmetNaSmeru(boolean obrisanPredmetNaSmeru) {
		this.obrisanPredmetNaSmeru = obrisanPredmetNaSmeru;
	}
	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public boolean isObrisanPredmet() {
		return obrisanPredmet;
	}
	public void setObrisanPredmet(boolean obrisanPredmet) {
		this.obrisanPredmet = obrisanPredmet;
	}
	public int getIdSmer() {
		return idSmer;
	}
	public void setIdSmer(int idSmer) {
		this.idSmer = idSmer;
	}
	public String getNazivSmera() {
		return nazivSmera;
	}
	public void setNazivSmera(String nazivSmera) {
		this.nazivSmera = nazivSmera;
	}
	public int getTrajanjeSmera() {
		return trajanjeSmera;
	}
	public void setTrajanjeSmera(int trajanjeSmera) {
		this.trajanjeSmera = trajanjeSmera;
	}
	public boolean isObrisanSmer() {
		return obrisanSmer;
	}
	public void setObrisanSmer(boolean obrisanSmer) {
		this.obrisanSmer = obrisanSmer;
	}
	
	
}
