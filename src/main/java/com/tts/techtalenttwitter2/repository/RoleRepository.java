package com.tts.techtalenttwitter2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.techtalenttwitter2.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRole(String role);
}
