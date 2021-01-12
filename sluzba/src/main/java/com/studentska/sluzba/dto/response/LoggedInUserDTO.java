package com.studentska.sluzba.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoggedInUserDTO {

	private int id;
	private String token;
	private String username;
	private String email;
	private String uloga;
	private Collection<? extends GrantedAuthority> authorities;	
	
	public LoggedInUserDTO(int id, String token, String username, String email, String uloga,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.token = token;
		this.username = username;
		this.uloga = uloga;
		this.email = email;
		this.authorities = authorities;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getUloga() {
		return uloga;
	}



	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Collection<? extends GrantedAuthority> getAuthorities(){ return authorities;}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) { this.authorities = authorities; };
}
