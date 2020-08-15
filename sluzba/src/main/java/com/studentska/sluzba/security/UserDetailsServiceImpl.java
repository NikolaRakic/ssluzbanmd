package com.studentska.sluzba.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik = korisnikRepository.findByUsernameOrEmail(username, username);
		if (korisnik == null) {
			throw new UsernameNotFoundException("Korisnik nije pronadjen");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(korisnik.getUloga().getNaziv()));
		return new org.springframework.security.core.userdetails.User(korisnik.getEmail(), korisnik.getPass(), grantedAuthorities);
	}

}
