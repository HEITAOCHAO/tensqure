package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoFriendService {

	@Autowired
	private NoFriendDao noFriendDao;


	/**
	 * 添加黑名单
	 * @param noFriend
	 * @return
	 */
	public NoFriend addNoFriend(NoFriend noFriend){
		return noFriendDao.save(noFriend);
	}

}
