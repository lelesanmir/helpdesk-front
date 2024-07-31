package com.leonardo.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.leonardo.helpdesk.enums.Profile;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public UserSS(Integer integer, String email, String password, Set<Profile> profiles) {
		super();
		this.id = integer;
		this.email = email;
		this.password = password;
		this.authorities = profiles.stream()
				.map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}// change to true

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}// change to true

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}// change to true

	@Override
	public boolean isEnabled() {
		return true;
	}// change to true

}
