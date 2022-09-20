package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_user")
public class MUser {
	@Id
	private String userId;
	private String password;
	private String userName;
	private String notificationTime;
}
