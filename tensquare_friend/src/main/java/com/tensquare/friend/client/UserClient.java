package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {


	/**
	 * 更新粉丝
	 * @param userid
	 * @param num
	 */
	@RequestMapping(value = "/addfanscount/{userid}/{num}",method = RequestMethod.PUT)
	void addFansCount(@PathVariable("userid")String userid, @PathVariable("num")int num);

	/**
	 * 更新关注
	 * @param userid
	 * @param num
	 */
	@RequestMapping(value = "/addfollowcount/{userid}/{num}",method = RequestMethod.PUT)
	void addFollowCount(@PathVariable("userid")String userid,@PathVariable("num")int num);
}
