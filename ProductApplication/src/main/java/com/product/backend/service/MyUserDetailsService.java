package com.product.backend.service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product.backend.entity.User;
import com.product.backend.repository.UserRepository;
import com.product.backend.security.UserPrinciple;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty()) throw new UsernameNotFoundException("User not found");
		return (UserDetails) new UserPrinciple(user.get());
	}

}
