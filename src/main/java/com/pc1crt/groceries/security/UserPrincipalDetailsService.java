package com.pc1crt.groceries.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.pc1crt.groceries.model.Customer;
import com.pc1crt.groceries.repository.CustomerRepository;
import com.pc1crt.groceries.service.CustomerService;
@Component("userPrincipalDetailsService")
public class UserPrincipalDetailsService implements UserDetailsService {
	
	private CustomerService repo;

	public UserPrincipalDetailsService(CustomerService repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = repo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
