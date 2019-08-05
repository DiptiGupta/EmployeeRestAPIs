package com.wp.emp.repository;


import com.wp.emp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);
	

	
	
		
}
