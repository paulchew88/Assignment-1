package com.pc1crt.groceries.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pc1crt.groceries.model.Customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	private Customer customer;

	public UserPrincipal(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		this.customer.getPermissionList().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authorities.add(authority);
		});
		this.customer.getRoleList().forEach(r -> {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
			authorities.add(authority);
		});
		return authorities;
	}
	public Integer getCustomerId() {
		return this.customer.getCustomerId();
		
	}

	@Override
	public String getPassword() {
		return this.customer.getPassword();
	}

	@Override
	public String getUsername() {

		return this.customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.customer.getActive();
	}

}
