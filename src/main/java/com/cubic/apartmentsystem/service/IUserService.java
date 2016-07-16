package com.cubic.apartmentsystem.service;

import java.util.List;

import com.cubic.apartmentsystem.domain.User;
import com.cubic.apartmentsystem.enums.StatusType;

public interface IUserService {
	public void saveEmployee(User user);

	public User getUserByUsername(String username);

	public boolean changePassword(String oldpassword, String newpassword, String confirmpassword);

	public User getUserProfileById(Long id);

	public void updateUser(User user);

	public User getUserById(Long id);

	public void updateStudent(User user);

	public void saveManager(User user);

	public List<User> getAllUser();

	public void changeStatus(Long id, StatusType status);
}
