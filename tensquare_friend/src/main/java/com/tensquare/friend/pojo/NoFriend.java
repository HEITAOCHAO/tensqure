package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tb_nofriend")
@IdClass(Friend.class) //复合主键
public class NoFriend {

	@Id
	private String userid;

	@Id
	private String friendid;

	public NoFriend() {
	}

	public NoFriend(String userid, String friendid) {
		this.userid = userid;
		this.friendid = friendid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}
}
