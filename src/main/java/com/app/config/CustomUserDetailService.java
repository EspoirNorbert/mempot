package com.app.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.models.Role;
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
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities(this.getAutorities(user.getRoles()))
				.build();
	}
	
	private List<GrantedAuthority> getAutorities(List<Role> roles){
		return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toList());
	}
	
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth!= null) {
			String email = auth.getName();
			return this.userService.findByEmail(email);
		}
		return null;
	}
}
