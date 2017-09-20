package com.ht.base;

import java.io.Serializable;

import javax.swing.text.StyledDocument;

import com.ht.vo.AccountVo;
/*
 * 发送数据包
 * */
@SuppressWarnings("serial")
public class SendMsg implements Serializable{

	//命令字
	public int cmd;
	//发送人信息
	public AccountVo myInfo;
	//接收人信息
	public AccountVo friendInfo;
	//发送的内容
	public StyledDocument doc;
	//发送文件内容在64K以下
	public byte b[];
	//发送的文件名称
	public String fileName;
}
