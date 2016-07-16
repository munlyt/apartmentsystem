package com.cubic.apartmentsystem.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.apartmentsystem.domain.User;
import com.cubic.apartmentsystem.domain.UserRole;
import com.cubic.apartmentsystem.enums.RoleType;
import com.cubic.apartmentsystem.enums.StatusType;
import com.cubic.apartmentsystem.repository.IRoleRepository;
import com.cubic.apartmentsystem.repository.IUserRepository;
import com.cubic.apartmentsystem.service.IUserService;
import com.cubic.apartmentsystem.util.Utility;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IRoleRepository roleRepository;

	@Override
	public void saveEmployee(User user) {
		user.setPassword(Utility.encryptPassword(user.getPassword()));
		user.setStatus(StatusType.INACTIVE);
		UserRole userRole = new UserRole();
		userRole.setRole(roleRepository.getRoleFromRoleName(RoleType.ROLE_EMPLOYEE));
		userRole.setUser(user);
		user.setUserRoles(Arrays.asList(userRole));
		userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserFromUsername(username);
	}

	@Override
	@PreAuthorize(value = "hasRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_EMPLOYEE')")
	public boolean changePassword(String oldpassword, String newpassword, String confirmpassword) {
		if (!newpassword.equals(confirmpassword)) {
			return false;
		}

		String username = Utility.getLoggedInUserName();
		User user = userRepository.getUserFromUsername(username);
		String password = user.getPassword();
		if (!Utility.matchPassword(oldpassword, password)) {
			return false;
		}
		String changedpassword = newpassword;
		user.setPassword(Utility.encryptPassword(changedpassword));
		userRepository.save(user);
		return true;
	}

	@Override
	public User getUserProfileById(Long id) {
		User user = userRepository.findOne(id);

		return user;
	}

	@Override
	@PreAuthorize(value = "hasRole('ROLE_ADMIN','ROLE_TMCHECKER','ROLE_STUDENT')")
	public void updateUser(User user) {
		User dbUser = userRepository.findOne(user.getId());
		dbUser.setEmail(user.getEmail());
		dbUser.setName(user.getName());
		userRepository.save(dbUser);
	}

	public User getUserById(Long id) {
		return (User) userRepository.findOne(id);
	}

	@Override
	public void updateStudent(User user) {
		userRepository.save(user);

	}

	@Override
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public void saveManager(User user) {
		user.setPassword(Utility.encryptPassword(user.getPassword()));
		user.setStatus(StatusType.ACTIVE);
		UserRole userRole = new UserRole();
		userRole.setRole(roleRepository.getRoleFromRoleName(RoleType.ROLE_MANAGER));
		userRole.setUser(user);
		user.setUserRoles(Arrays.asList(userRole));
		userRepository.save(user);

	}

	@Override
	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public void changeStatus(Long id, StatusType status) {
		User user = userRepository.findOne(id);
		user.setStatus(status);
		userRepository.save(user);
	}
}