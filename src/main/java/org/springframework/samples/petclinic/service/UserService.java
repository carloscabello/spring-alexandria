/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/* Devuelve el usuario logueado */
	public User getPrincipal() {
		User res = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			Optional<User> currentUser = findUser(userDetail.getUsername());
			
			if(currentUser.isPresent()) {
				res = currentUser.get();
			}
		}
		
		return res;
	}
	
	/* Devuelve la Authority del usuario loguado
	 * o null en otro caso*/
	private Authorities getPrincipalAuthority() {
		Authorities res = null;
		User currentUser = getPrincipal();
		if(currentUser != null) {
			res = currentUser.getAuthorities().iterator().next();
		}
		return res;
	}
	
	/* Devuelve el nombre de la autoridad del usuario logueado
	 * y anonymous en otro caso */
	public String getPrincipalRole() {
		String res = "anonymous";
		Authorities principalAuthority = getPrincipalAuthority();
		if(principalAuthority != null) {
			res = principalAuthority.getAuthority();
		}
		return res;
	}
	
	/* Devuelve cierto cuando el usuario está logueado*/
	public Boolean isAuthenticated() {
		Boolean res = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		res = !(auth instanceof AnonymousAuthenticationToken);
		
		return res;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}
}
