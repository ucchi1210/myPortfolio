package com.example.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.MUser;
import com.example.UserService;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class notificationtimeController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserService service;
	
	@GetMapping("/notificationtime")
	public String getNotificationtime(NotificationForm form ,Model model){
		MUser mUser = service.getLoginUser();
		//muserをformに変換
		form = mapper.map(mUser,NotificationForm.class);
		model.addAttribute("notificationForm",form);
		
		
		return "notificationtime";
	}
	@PostMapping("/notificationtime")
	public String postNotificationtime(Model model ,@ModelAttribute @Validated NotificationForm form,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return getNotificationtime(form, model);
		}
		log.info("通知時間の変更は"+ form.toString());
		MUser mUser =service.getLoginUser();
		mUser.setNotificationTime(form.getNotificationTime());
		service.updateUserOne(mUser.getUserId(), mUser.getUserName(), mUser.getNotificationTime());
		return "redirect:/datalist";
	}

}
