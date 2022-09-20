package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.MUser;

public interface UserRepository extends JpaRepository<MUser, String> {
/**ログインユーザー検索*/
	@Query("select user"
			+ " from MUser user"
			+ " where userId = :userId")
	public MUser findLoginUser(@Param("userId")String userId);
	
	public List<MUser> findByNotificationTime(String notificationTime);
	
	/**データ一件更新*/
	@Modifying
	@Query("update MUser"
			+" set"
			+" notificationTime = :notificationTime"
			+" where"
			+" userId = :userId")
	public Integer updateUser(@Param("userId") String userId,
			@Param("notificationTime") String notificationTime);
	
	
	
}
