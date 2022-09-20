package com.example.dataTable.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dataTable.DataTable;
import com.example.dataTable.DataTableService;
import com.example.dataTable.form.RegisterForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DataRegistrationController {
	@Autowired
	private DataTableService dataTableService;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@GetMapping("/dataRegistration")
	public String getdataRegistration(Model model, RegisterForm form) {
		
		
		return "/dataRegistration";
	}
	/**データ登録処理*/
	@PostMapping("/register")
	public String registrData(Model model,@ModelAttribute @Validated RegisterForm form ,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			//チェック結果NG
			return getdataRegistration(model, form);
			
		}
		log.info(form.toString());
		DataTable data = mapper.map(form, DataTable.class);
		dataTableService.register(data);
		return "redirect:/datalist";
		
		
	}

}
