package com.cubic.apartmentsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cubic.apartmentsystem.domain.Role;
import com.cubic.apartmentsystem.enums.RoleType;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Integer> {

	@Query("SELECT r FROM Role r WHERE r.roleType = :roleType")
	public Role getRoleFromRoleName(@Param("roleType") RoleType roleType);

}
