package com.ht.base;

public interface Cmd {

	//上线通知（登录)
	public static final int CMD_ONLINE	=1000;
	//离线
	public static final int CMD_OFFLINE	=1001;
	//离开
	public static final int CMD_LEVEL	=1002;
	//忙碌
	public static final int CMD_BUYS	=1003;
	//发送信息
	public static final int CMD_SEND	=1004;
	//发送文件
	public static final int CMD_FILE	=1005;
	//抖动
	public static final int CMD_SHAKE	=1006;
	//添加好友
	public static final int CMD_ADDFRI	=1007;
	//同意添加好友
	public static final int CMD_ARGEE	=1008;
	//拒绝添加好友
	public static final int CMD_REFUSE	=1009;
	//更改状态
	public static final int CMD_CHANGESTATE	=1010;
	//删除好友
	public static final int CMD_DELFRIEND=1011;
	//在线状态
	public static final String[] STATUS={"在线","离线","忙碌","隐身"};
	//分组名称
	public static final String[] GROUPNAME={"好友","同学","家人","朋友","黑名单"};
	
}
