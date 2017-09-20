package com.ht.vo;

import java.io.Serializable;

//必须对所有的JavaBean进行序列化
@SuppressWarnings("serial")
public class AccountVo implements Serializable{
	private int qqCode;//QQ号码
	private String nickName;//昵称
	private String headImg;//头像
	private String password;//账号
	private int age;//年龄
	private String sex;//性别
	private String star;//星座
	private String blood;//血型
	private String nation;//民族
	private String hobit;//爱好
	private String ipAddr;//IP地址
	private int port;//端口
	private String onlinestatus;//状态
	private String remark;//备注
	private String groupName;//分组
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getQqCode() {
		return qqCode;
	}
	public void setQqCode(int qqCode) {
		this.qqCode = qqCode;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getHobit() {
		return hobit;
	}
	public void setHobit(String hobit) {
		this.hobit = hobit;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getOnlinestatus() {
		return onlinestatus;
	}
	public void setOnlinestatus(String onlinestatus) {
		this.onlinestatus = onlinestatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
}
