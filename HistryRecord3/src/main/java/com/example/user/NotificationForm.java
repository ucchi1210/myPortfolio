package com.example.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NotificationForm {
	
	private String userId;
	
		
	
	private String userName;
	
	@NotBlank
	private String notificationTime;

}
