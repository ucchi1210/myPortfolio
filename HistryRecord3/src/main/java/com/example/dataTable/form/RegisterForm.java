package com.example.dataTable.form;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
public class RegisterForm {
	@URL
	@NotBlank
	private String siteUrl;
	
	private String title;
	

}
