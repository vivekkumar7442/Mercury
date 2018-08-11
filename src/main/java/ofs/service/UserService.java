package ofs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ofs.aop.LogExecutionTime;
import ofs.bean.UserVO;
import ofs.bean.dao.UserExpenseDetailsRepository;
import ofs.bean.dao.UserRepository;
import ofs.bean.model.User;
import ofs.bean.model.UserExpenseDetails;
import ofs.bean.request.UserRequest;
import ofs.exception.OfsCommonException;
import ofs.utils.MqUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MqUtils mqUtils;
	
	private UserExpenseDetailsRepository userExpenseDetailsRepository;

	@LogExecutionTime
	public UserVO saveUserVO(UserRequest userRequest) throws OfsCommonException{
		User dupUser=userRepository.findByUserEmail(userRequest.getUserEmail());
		
		if(dupUser!=null) {
			throw new OfsCommonException("user with email already exists");
		}
		else {
			User user = new User();
			user.setUserEmail(userRequest.getUserEmail());
			user.setUserPassword(userRequest.getPassword());
			User userEntity = userRepository.save(user);
			UserVO userVO = new UserVO();
			userVO.setUserId(userEntity.getUserId());
			userVO.setUserEmail(userEntity.getUserEmail());
			mqUtils.send("vivek Test", userVO.toString());
			return userVO;
		}
		
	}


}
