package com.example.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MUser;
import com.example.UserService;
@Service
public class MUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MUser loginMUser = service.getLoginUser2(username);
		Optional<MUser> loginMUserOptional = Optional.ofNullable(loginMUser);
		return loginMUserOptional.map(mUser -> new MuserDetails(mUser))
				.orElseThrow(()->new UsernameNotFoundException("not found"));
	}

}
