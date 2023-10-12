package com.app.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.models.User;
import com.app.services.UserService;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userService.findByEmail(username);
		
		System.out.println("Call this method");
		System.out.println(user);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toList());
		
		return new org.springframework.security.core
				.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	

}
