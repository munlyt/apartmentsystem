package com.cubic.apartmentsystem.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cubic.apartmentsystem.domain.User;
import com.cubic.apartmentsystem.domain.UserRole;
import com.cubic.apartmentsystem.enums.StatusType;
import com.cubic.apartmentsystem.service.IUserService;
import com.cubic.apartmentsystem.util.Utility;

public class SecurityUserServiceImpl implements UserDetailsService {
	private static final Logger log = Logger.getLogger(SecurityUserServiceImpl.class);

	@Autowired
	IUserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.getUserByUsername(username);
		} catch (Exception e) {
			Utility.logError(log, e.getMessage(), e);
		}
		if (user == null) {
			throw new UsernameNotFoundException("Username doesn't exists");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
		return buildUserForAuthentication(user, authorities);

	}

	private AuthenticatedUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		Boolean enabled = false;
		if (user.getStatus().equals(StatusType.ACTIVE))
			enabled = true;

		AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.getUsername(), user.getPassword(), enabled,
				true, true, true, authorities);

		authenticatedUser.setUserId(user.getId());
		authenticatedUser.setName(user.getName());
		authenticatedUser.setEmailAddress(user.getEmail());
		return authenticatedUser;

	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		try {
			for (UserRole userRole : userRoles) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleType().toString()));
			}
			return new ArrayList<GrantedAuthority>(setAuths);
		} catch (Exception e) {
			throw e;
		}

	}

}
