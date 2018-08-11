package ofs.bean.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ofs.bean.model.UserExpenseDetails;

public interface UserExpenseDetailsRepository  extends JpaRepository<UserExpenseDetails, Integer>{

}
