package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.UserRepository;
import com.example.security.MuserDetails;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private MUser mUser = new MUser();
	
	@Override
	public void signup(MUser user) {
		boolean exists = userRepository.existsById(user.getUserId());
		if (exists) {
			throw new DataAccessException("登録済みメールアドレスです") {};
		}
		String rowPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rowPassword));
		//insert
		userRepository.save(user);
	}
	
	@Override
	public List<MUser> getUsers(MUser user) {
		
		return userRepository.findAll();
	}
	@Override
	public MUser getUserOne(String userId) {
		Optional<MUser>optional = userRepository.findById(userId);
		MUser user = optional.orElse(null);
		return user;
	}
	@Transactional
	@Override
	public void updateUserOne(String userId, String userName,String notificationtime) {
		// TODO 自動生成されたメソッド・スタブ
		userRepository.updateUser(userId,  notificationtime);
		
	}
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		userRepository.deleteById(userId);
	}
	
	@Override
	public MUser getLoginUser() {
		MuserDetails mDetails = (MuserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mUser = mDetails.getMUser();
		String userId = mUser.getUserId();
		
		return userRepository.findLoginUser(userId);
	}
	@Override
	public List<MUser> getMachUsersWithNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String time = dateFormat.format(new Date());
		
		return userRepository.findByNotificationTime(time);
	}
	@Override
	public MUser getLoginUser2(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return userRepository.findLoginUser(userId);
	}
	
	

}
