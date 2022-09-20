package com.example;

import java.util.List;



public interface UserService {
	/**register User*/
	public void signup(MUser user);

	/**ユーザーリストを取得*/
	public List<MUser> getUsers(MUser user);
	/**通知時間になったユーザーを取得*/
	public List<MUser> getMachUsersWithNow();
	
	/**ユーザー一件取得*/
	public MUser getUserOne(String userId);
	
	/**ユーザーアップデート一件*/	
	public void updateUserOne(String userId,String userName , String notificationtime);
	
	/**ユーザー削除一件*/
	public void deleteUserOne(String userId);
	/**ログインユーザー情報取得*/
	public MUser getLoginUser();
	/**ログインユーザー情報取得*/
	public MUser getLoginUser2(String userId);
	
}
