package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		//ユーザー情報取得
		MUser loginUser = userService.getLoginUser2(username);
		//ユーザーが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		//権限List作成
		//GrantedAuthority authority = new SimpleGrantedAuthority();
		
		
		//UserDetails生成
		UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), null);
		
		return userDetails;
	}

}
