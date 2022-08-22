package com.tts.techtalenttwitter2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.techtalenttwitter2.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
	
	User findByUsername(String username);
	
	@Override
	List<User> findAll();
}
