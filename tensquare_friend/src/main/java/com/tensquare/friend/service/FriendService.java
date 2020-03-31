package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FriendService {

	@Autowired
	private FriendDao friendDao;

	@Autowired
	private NoFriendDao noFriendDao;

	@Autowired
	private UserClient userClient;

	/**
	 * 添加好友
	 * @param userid
	 * @param friendid
	 * @return
	 */
	public int addFriend(String userid,String friendid){
		if(friendDao.selectCount(userid, friendid)>0){
			return 0;
		}
		Friend friend=new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIslike("0");
		if(friendDao.selectCount(friendid, userid)>0){
			friend.setFriendid("1");
			friendDao.updateState(friendid,userid,"1");
		}
		friendDao.save(friend);

		userClient.addFansCount(friendid, 1);
		userClient.addFollowCount(userid, 1);

		return 1;
	}

	/**
	 * 删除好友
	 * @param friend
	 */
	public void deleteFriend(NoFriend friend){
		friendDao.deleteFriend(friend.getUserid(), friend.getFriendid());
		friendDao.updateState(friend.getFriendid(), friend.getUserid(),"0");
		noFriendDao.save(new NoFriend(friend.getUserid(),friend.getFriendid()));

		userClient.addFansCount(friend.getFriendid(), -1);
		userClient.addFollowCount(friend.getUserid(), -1);
	}
}
