package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 交友数据访问层
 */
public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {

	@Query(value = "select count(*) from tb_friend where userid=:userid and friendid=:friendid",nativeQuery = true)
	int selectCount(@Param("userid")String userid,@Param("friendid")String friendid);

	@Modifying
	@Query(value = "update tb_friend set islike=:type where userid=:userid and friendid=:friendid",nativeQuery = true)
	void updateState(@Param("userid")String userid,@Param("friendid")String friendid,@Param("type")String type);

	@Modifying
	@Query(value = "delete from tb_friend where userid=:userid and friendid=:friendid",nativeQuery = true)
	void deleteFriend(@Param("userid")String userid,@Param("friendid")String friendid);
}
