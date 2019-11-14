package com.tanpham.javaee.playground.helloworld;

import java.security.Principal;

import javax.security.auth.login.LoginException;

import org.jboss.security.auth.spi.UsersRolesLoginModule;

public class CustomLoginModule extends UsersRolesLoginModule {
	private CustomPrincipal principal;

	@Override
	public boolean login() throws LoginException {
		boolean login = super.login();
		if (login) {
			principal = new CustomPrincipal(getUsername(), "An user description!");
		}
		return login;
	}

	@Override
	protected Principal getIdentity() {
		return principal != null ? principal : super.getIdentity();
	}
}
