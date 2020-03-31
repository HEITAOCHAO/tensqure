package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

	User findByMobile(String mobile);

	/**
	 * 更新关注
	 * @param userid
	 * @param num
	 */
	@Modifying
	@Query(value = "update tb_user set followcount=followcount+:num where id=:userid",nativeQuery = true)
	void addFollowCount(@Param("userid")String userid,@Param("num") int num);

	/**
	 * 更新粉丝
	 * @param userid
	 * @param num
	 */
	@Modifying
	@Query(value = "update tb_user set fenscount=fenscount+:num where id=:userid",nativeQuery = true)
	void addFansCount(@Param("userid")String userid,@Param("num") int num);

}
