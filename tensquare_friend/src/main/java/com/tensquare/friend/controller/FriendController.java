package com.tensquare.friend.controller;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("friend")
public class FriendController {

	@Autowired
	private FriendService friendService;

	@Autowired
	private NoFriendService noFriendService;

	/**
	 * 添加关注好友
	 * @param type 0=喜欢 1=不喜欢
	 * @param friend
	 * @return
	 */
	@RequestMapping(value = "/{type}",method = RequestMethod.POST)
	private Result addFriend(@PathVariable String type,@RequestBody NoFriend friend){
		if(type.equals(0)){
			if(friendService.addFriend(friend.getUserid(), friend.getFriendid())>0){
				return new Result(true, StatusCode.OK,"添加成功！");
			}
			return new Result(false, StatusCode.ERROR,"已经添加过了！");
		}else{
			noFriendService.addNoFriend(friend);
			return new Result(true, StatusCode.OK,"操作成功！");
		}
	}


	/**
	 * 删除好友并加入黑名单
	 * @param friend
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	private Result deleteFriend(@RequestBody NoFriend friend){
		friendService.deleteFriend(friend);
		return new Result(true, StatusCode.OK,"操作成功！");
	}


}
