package com.ht.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FriendVo implements Serializable{
	private int friendId;//朋友的qq号码
	private int myQQcode;//我的QQ号
	private int friendQQcode;//朋友的qq号
	private String groupName;//分组
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getMyQQcode() {
		return myQQcode;
	}
	public void setMyQQcode(int myQQcode) {
		this.myQQcode = myQQcode;
	}
	public int getFriendQQcode() {
		return friendQQcode;
	}
	public void setFriendQQcode(int friendQQcode) {
		this.friendQQcode = friendQQcode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
