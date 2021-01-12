package com.studentska.sluzba.dto.request;

import java.util.Date;

public class IspitIliKolokvijumDTO {
	
	private int idIspit;
	private int idStudent;
	private int idPredmet;
	private int idRok;
	private int idTipIspita;
	private Date datumIspita;
	private Integer ocena;
	private int bodovi;
	private boolean obrisan;
	
	public IspitIliKolokvijumDTO(int idIspit, int idStudent, int idPredmet, int idRok, int idTipIspita,
			Date datumIspita, Integer ocena, int bodovi, boolean obrisan) {
		super();
		this.idIspit = idIspit;
		this.idStudent = idStudent;
		this.idPredmet = idPredmet;
		this.idRok = idRok;
		this.idTipIspita = idTipIspita;
		this.datumIspita = datumIspita;
		this.ocena = ocena;
		this.bodovi = bodovi;
		this.obrisan = obrisan;
	}

	public int getIdIspit() {
		return idIspit;
	}

	public void setIdIspit(int idIspit) {
		this.idIspit = idIspit;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdPredmet() {
		return idPredmet;
	}

	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}

	public int getIdRok() {
		return idRok;
	}

	public void setIdRok(int idRok) {
		this.idRok = idRok;
	}

	public int getIdTipIspita() {
		return idTipIspita;
	}

	public void setIdTipIspita(int idTipIspita) {
		this.idTipIspita = idTipIspita;
	}

	public Date getDatumIspita() {
		return datumIspita;
	}

	public void setDatumIspita(Date datumIspita) {
		this.datumIspita = datumIspita;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	

}
