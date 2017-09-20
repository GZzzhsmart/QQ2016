package com.ht.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.text.StyledDocument;

import com.ht.vo.AccountVo;
@SuppressWarnings("unused")
public class SendCmd {
	//发送消息
	public static void send(SendMsg msg){
		try {
			//定义Socket
			DatagramSocket socket = new DatagramSocket();
			//创建一个字节数组输出流
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//把对象输出到字节数组中
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(msg);
			oos.flush();
			//把要发送的数据转换为字节数组
			byte b[]=bos.toByteArray();
			//获取好友的IP地址
			InetAddress addr = InetAddress.getByName(msg.friendInfo.getIpAddr());
			//获取好友的接收端口
			int port = msg.friendInfo.getPort();
			//生成发送报(数据报)
			DatagramPacket pack = new DatagramPacket(b,0,b.length,addr,port);
			socket.send(pack);//发送
			System.out.println(msg.cmd +"==发送消息,qqcode="+msg.friendInfo.getQqCode());
			socket.close();
			oos.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//广播发送信息
	public  static void sendAll(Vector<AccountVo> allInfo,AccountVo myInfo,int cmd){
		for (AccountVo acc : allInfo) {
			if(!acc.getOnlinestatus().equals(Cmd.STATUS[1]) && acc.getQqCode()!=myInfo.getQqCode()){
				SendMsg msg = new SendMsg();
				msg.cmd = cmd;
				msg.myInfo = myInfo;
				msg.friendInfo = acc;
				send(msg);
			}
		}
		
	}
	
}
