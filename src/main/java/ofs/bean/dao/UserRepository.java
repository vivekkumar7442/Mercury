package ofs.bean.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ofs.bean.model.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	User findByUserEmail(String userEmail);
	
	
}