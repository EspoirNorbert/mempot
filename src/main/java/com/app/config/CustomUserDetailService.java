package com.app.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.models.Role;
import com.app.models.User;
import com.app.repositories.RoleRepository;
import com.app.services.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired UserService userService;
	@Autowired RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userService.findByEmail(username);
		
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
			return this.userService.findByEmail(auth.getName());
		}
		return null;
	}

	public boolean isAdmin() {
		List<Role> roles = getCurrentUser().getRoles();
		return roles.stream().anyMatch(role -> "ADMIN".equals(role.getName()));
	}
	
	public String currentUserRole() {
		List<Role> roles = getCurrentUser().getRoles();
		
	    if (roles.stream().anyMatch(role -> "USER".equals(role.getName()))) {
	        return "user";
	    }
	    
	    return "admin";
	}

}
