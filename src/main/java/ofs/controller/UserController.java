package ofs.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ofs.bean.UserVO;
import ofs.bean.model.User;
import ofs.bean.request.UserRequest;
import ofs.exception.OfsCommonException;
import ofs.service.MailSenderService;
import ofs.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	MailSenderService mailService;

	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	@ResponseBody
	public UserVO getUserDetails(@RequestBody UserRequest userRequest) throws OfsCommonException {

		return userService.saveUserVO(userRequest);

	}

	@RequestMapping(value = "/user/email", method = RequestMethod.GET)
	@ResponseBody
	public void getMail() throws OfsCommonException {
		try {
			mailService.processMailNotification();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
