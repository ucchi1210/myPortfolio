package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.MUser;

public class MuserDetails implements UserDetails{
	
	private final MUser mUser;
	private Collection<? extends GrantedAuthority> authorities;
	
	public MuserDetails(MUser mUser) {
		this.mUser = mUser;
	}
	
	public MUser getMUser() {
		return mUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return mUser.getPassword();
	}
	
	@Override
	public String getUsername() {
		return mUser.getUserId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
