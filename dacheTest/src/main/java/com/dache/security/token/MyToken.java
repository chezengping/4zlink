package com.dache.security.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class MyToken extends UsernamePasswordAuthenticationToken {
	
	private static final long serialVersionUID = -8562355590099687777L;
	
	public MyToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
	
	
}
