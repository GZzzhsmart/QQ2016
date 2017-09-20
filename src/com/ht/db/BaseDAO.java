package com.ht.db;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.ht.base.Cmd;
import com.ht.base.DBConn;
import com.ht.vo.AccountVo;

@SuppressWarnings("unused")
public class BaseDAO {

	//随机生成QQ号码
	public int getQQcode(){
		boolean bExist=false;
		Connection conn = DBConn.openDB();
		String sql = "select qqcode from account where qqcode=?";
		int qqcode = 0;
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			while(!bExist){
				Random rand = new Random();
				//控制产生8位的QQ号码
				qqcode = rand.nextInt(89999999)+1000000;
				pstmt.setInt(1, qqcode);
				ResultSet rs = pstmt.executeQuery();
				if(!rs.next()){
					bExist=true;
					break;
				}
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return qqcode;
	}
	//随机生成端口(用户每次登录时都重新产生通信端口)
	public int getPort(){
		boolean bExist=false;
		Connection conn = DBConn.openDB();
		String sql = "select port from account where onlinestatus<>? and  port=?";
		int port = 0;
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			while(!bExist){
				Random rand = new Random();
				//控制产生8位的QQ号码
				port = rand.nextInt(64000)+1024;
				pstmt.setString(1, Cmd.STATUS[1]);
				pstmt.setInt(2, port);
				ResultSet rs = pstmt.executeQuery();
				if(!rs.next()){
					bExist=true;
					break;
				}
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return port;
	}
	//保存注册用户的信息
	public AccountVo saveAccount(AccountVo acc){
		//获取QQ号码
		int qqcode = getQQcode();
		acc.setQqCode(qqcode);
		acc.setPort(0);
		acc.setOnlinestatus(Cmd.STATUS[1]);//离线
		
		String sql ="insert into account values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBConn.openDB();
		try{
			int i=1;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(i++, acc.getQqCode());
			pstmt.setString(i++, acc.getNickName());
			pstmt.setString(i++, acc.getHeadImg());
			pstmt.setString(i++, acc.getPassword());
			pstmt.setInt(i++, acc.getAge());
			pstmt.setString(i++, acc.getSex());
			pstmt.setString(i++, acc.getStar());
			pstmt.setString(i++, acc.getBlood());
			pstmt.setString(i++, acc.getNation());
			pstmt.setString(i++, acc.getHobit());
			pstmt.setString(i++, acc.getIpAddr());
			pstmt.setInt(i++, acc.getPort());
			pstmt.setString(i++, acc.getOnlinestatus());
			pstmt.setString(i++, acc.getRemark());
			i=pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return acc;
	}
	//保存注册用户的信息
	public AccountVo updateAccount(AccountVo acc){
		String sql ="update account set nickName=?,headImg=?,age=?,sex=?,star=?,blood=?,nation=?,hobit=?,remark=? where qqCode=?";
		Connection conn = DBConn.openDB();
		try{
			int i=1;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, acc.getNickName());
			pstmt.setString(i++, acc.getHeadImg());
			pstmt.setInt(i++, acc.getAge());
			pstmt.setString(i++, acc.getSex());
			pstmt.setString(i++, acc.getStar());
			pstmt.setString(i++, acc.getBlood());
			pstmt.setString(i++, acc.getNation());
			pstmt.setString(i++, acc.getHobit());
			pstmt.setString(i++, acc.getRemark());
			pstmt.setInt(i++, acc.getQqCode());
			i=pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return acc;
	}
	//登录
	/*1.获取一个合法的端口
	 *2.登录成功后把所有字段的值设置到account对象中，返回给登录界面
	 *3.更新端口和状态
	 * 
	 * */
	public AccountVo login(AccountVo account){
		Connection conn = DBConn.openDB();
		String sql = "select * from account where qqcode=? and password=?";
		int qqcode = 0;
		try{
			int port =getPort();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account.getQqCode());
			pstmt.setString(2, account.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				account.setNickName(rs.getString("nickName").trim());
				account.setHeadImg(rs.getString("headImg").trim());
				account.setAge(rs.getInt("age"));
				account.setSex(rs.getString("sex").trim());
				account.setStar(rs.getString("star").trim());
				account.setBlood(rs.getString("blood").trim());
				account.setNation(rs.getString("nation").trim());
				account.setHobit(rs.getString("hobit").trim());
				InetAddress addr=null;
				try {
					addr = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				String ip = addr.getHostAddress();
				account.setIpAddr(ip);
				account.setPort(port);
				account.setOnlinestatus(Cmd.STATUS[0]);//在线
				account.setRemark(rs.getString("remark"));
				//更改端口和状态
				sql = "update account set ipaddr='"+ip+"' ,port="+port +",onlinestatus='" + Cmd.STATUS[0] + "' where qqcode="+account.getQqCode();
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				pstmt.close();
				stmt.close();
			}else{
				//登录失败
				account=null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	//查找好友信息
	public Vector<AccountVo> getMyFriend(int myQQcode){
		String sql = "select a.*,f.groupName from account a inner join Friends f on a.qqcode=f.friendQQcode where myqqcode=?";
		int qqcode = 0;
		Vector<AccountVo> vmyFriend = new Vector<AccountVo>(); 
		Connection conn = DBConn.openDB();
		try{
			int port =getPort();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myQQcode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				AccountVo account = new AccountVo();
				account.setQqCode(rs.getInt("qqCode"));
				account.setNickName(rs.getString("nickName").trim());
				account.setHeadImg(rs.getString("headImg").trim());
				account.setAge(rs.getInt("age"));
				account.setSex(rs.getString("sex").trim());
				account.setStar(rs.getString("star").trim());
				account.setBlood(rs.getString("blood").trim());
				account.setNation(rs.getString("nation").trim());
				account.setHobit(rs.getString("hobit").trim());
				account.setIpAddr(rs.getString("ipAddr"));
				account.setPort(rs.getInt("port"));
				account.setOnlinestatus(rs.getString("onlinestatus"));//在线
				account.setRemark(rs.getString("remark"));
				account.setGroupName(rs.getString("groupName").trim());
				vmyFriend.add(account);
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return vmyFriend;
	}
	//移动分组
	public void moveGroup(int myQQcode,int friendQQcode,String groupName){
		String sql ="update friends set groupname=? where myQQcode=? and friendQQcode=?";
		try{
			Connection conn = DBConn.openDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, groupName);
			pstmt.setInt(i++, myQQcode);
			pstmt.setInt(i++, friendQQcode);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//查找好友
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector<Vector> findFriend(final String sql){
		Vector<Vector> vData = new Vector<Vector>();
		Connection conn = DBConn.openDB();
		try{
			int port =getPort();
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				Vector rowData = new Vector();
				rowData.addElement(rs.getString("headImg").trim());
				rowData.addElement(rs.getInt("qqcode"));
				rowData.addElement(rs.getString("nickName").trim());
				rowData.addElement(rs.getInt("age"));
				rowData.addElement(rs.getString("sex").trim());
				rowData.addElement(rs.getString("blood").trim());
				rowData.addElement(rs.getString("star").trim());
				rowData.addElement(rs.getString("nation").trim());
				rowData.addElement(rs.getString("hobit").trim());
				rowData.addElement(rs.getString("onlinestatus").trim());
				rowData.addElement(rs.getString("remark"));
				vData.addElement(rowData);
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return vData;
	}
	//获取选中的用户资料
	public AccountVo getSelectedFriend(int myQQcode){
		String sql = "select * from account where qqcode=?";
		int qqcode = 0;
		AccountVo account = new AccountVo(); 
		Connection conn = DBConn.openDB();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myQQcode);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				account.setQqCode(rs.getInt("qqCode"));
				account.setNickName(rs.getString("nickName").trim());
				account.setHeadImg(rs.getString("headImg").trim());
				account.setAge(rs.getInt("age"));
				account.setSex(rs.getString("sex").trim());
				account.setStar(rs.getString("star").trim());
				account.setBlood(rs.getString("blood").trim());
				account.setNation(rs.getString("nation").trim());
				account.setHobit(rs.getString("hobit").trim());
				account.setIpAddr(rs.getString("ipAddr"));
				account.setPort(rs.getInt("port"));
				account.setOnlinestatus(rs.getString("onlinestatus"));//在线
				account.setRemark(rs.getString("remark"));
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	//添加好友
	public void addFriend(int myqqcode,int friendqqcode){
		try{
			Connection conn = DBConn.openDB();
			String sql ="insert into friends values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myqqcode);
			pstmt.setInt(2, friendqqcode);
			pstmt.setString(3, Cmd.GROUPNAME[1]);
			pstmt.executeUpdate();
			pstmt.setInt(1, friendqqcode);
			pstmt.setInt(2, myqqcode);
			pstmt.setString(3, Cmd.GROUPNAME[1]);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//删除好友
	public void delFriend(int myqqcode,int friendqqcode){
		try{
			Connection conn = DBConn.openDB();
			String sql ="delete friends where myqqcode=? and friendqqcode=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myqqcode);
			pstmt.setInt(2, friendqqcode);
			pstmt.executeUpdate();
			pstmt.setInt(1, friendqqcode);
			pstmt.setInt(2, myqqcode);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//判断是否为好友
	public boolean isFriend(int myQQcode,int friendqqcode){
		String sql = "select * from friends where myqqcode=? and friendqqcode=?";
		boolean isok=false;
		AccountVo account = new AccountVo(); 
		Connection conn = DBConn.openDB();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myQQcode);
			pstmt.setInt(2, friendqqcode);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				isok=true;
			}
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return isok;
	}
	
	//更改状态
	public void changeStatus(int qqcode,String status){
		String sql ="update account set onlinestatus=? where qqCode=?";
		Connection conn = DBConn.openDB();
		try{
			int i=1;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, status);
			pstmt.setInt(i++, qqcode);
			i=pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
