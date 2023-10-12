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

import com.app.models.Role;
import com.app.models.User;
import com.app.services.UserService;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Call method for authenticated user");
		
		User foundUser = this.userService.findByEmail(username);
		
		if (foundUser != null) {
			return new org.springframework.security.core.userdetails.User(
					foundUser.getEmail(),foundUser.getPassword(),
					this.getGrantedAuthorities(foundUser.getRoles()));
		}
		
		throw new UsernameNotFoundException("User not found !");
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
		return roles.stream()
	            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
	            .collect(Collectors.toList());
	}

}
