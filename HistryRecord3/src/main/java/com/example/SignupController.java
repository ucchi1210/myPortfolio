package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/signup")
	public String getSignup(Model model ,SignupForm form) {
		
		return"signup";
	}
	
	/**ユーザー登録処理*/
	@PostMapping("/signup")
	public String postSignup(Model model,@ModelAttribute @Validated SignupForm form, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return getSignup(model, form);
		}
		log.info(form.toString());
		
		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		
		//ユーザー登録
		userService.signup(user);
		
		return "redirect:/login";
	}

}
