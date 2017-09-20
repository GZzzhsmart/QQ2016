package com.ht.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Vector;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import com.ht.base.Cmd;
import com.ht.base.SendCmd;
import com.ht.base.SendMsg;
import com.ht.db.BaseDAO;
import com.ht.vo.AccountVo;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements MouseListener, ActionListener,ItemListener,WindowListener{

	//声明标题
	JLabel bg, close, min, music, youxi;
	JLabel head,isonline;
	JLabel name, qianming, huanfu, look,test,test1,test2,test3,test4,test5,test6;
	JTabbedPane jp;
	@SuppressWarnings("rawtypes")
	JList list1, list2, list3, list4, list5;
	JMenuItem chat, jvfriend, jvhaoyou, jvmate, jvhmd, jvjiaren,lookfriendinfo, delete;
	//弹出菜单
	JPopupMenu pop,online;
	//在线，隐身，忙碌
	JMenuItem zaixian,yinshen,manglu;
	
	Point loc = null;
	Point tmp = null;
	
	JMenuItem open, clo;
	JPopupMenu pop1;
	Sound4 sound4;
	boolean isDragged = false;

	//保存登录成功后的个人信息
	AccountVo myInfo, friendInfo,fretable;
	//保存所有的好友信息（好友，家人，同学，朋友，黑名单）
	Vector<AccountVo> allimfmation, vfriend, vmate, vhaoyou, vhmd, vjiaren;
	BaseDAO baseDAO = new BaseDAO();
	Hashtable<Integer, ChatUI> chatWin = new Hashtable<Integer, ChatUI>();
	// 设置界面移动的方法
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				MainUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());

				isDragged = true;
				MainUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(MainUI.this.getLocation().x + e.getX()
							- tmp.x, MainUI.this.getLocation().y + e.getY()
							- tmp.y);
					MainUI.this.setLocation(loc);
				}
			}
		});
	}

	public MainUI() {
		
	};

	@SuppressWarnings("rawtypes")
	public MainUI(AccountVo myInfo) {
		super("好友界面");
		this.myInfo=myInfo;
		setIconImage(new ImageIcon(myInfo.getHeadImg()).getImage());
		setDragable();
		setUndecorated(true);
		bg=new JLabel(new ImageIcon("images/qqmain3.png"));
		bg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(bg);
		//关闭
		close = new JLabel("  ");
		close.setToolTipText("关闭");
		close.setBounds(250, 5, 20, 20);
		bg.add(close);
		//最小化
		min = new JLabel();
		min.setToolTipText("最小化");
		min.setBounds(230, 5, 20, 20);
		bg.add(min);
		//头像
		head = new JLabel(new ImageIcon(myInfo.getHeadImg()));
		head.setBounds(10, 43, 60, 62);
		bg.add(head);
		//昵称
		name = new JLabel(myInfo.getNickName());
		name.setBounds(80, 35, 60, 30);
		bg.add(name);
		//换肤
		huanfu = new JLabel("  ");
		huanfu.setToolTipText("换肤");
		huanfu.setBounds(232, 90, 15, 15);
		bg.add(huanfu);
		//超链接(腾讯网迷你版，QQ空间，QQ浏览器，应用宝，QQ邮箱,QQ天气,腾讯应用中心)
		//腾讯新闻首页
		test = new JLabel("  ");
		test.setToolTipText("腾讯网迷你版");
		test.setBounds(136,85,20,20);
		bg.add(test);
		//QQ空间
		test1 = new JLabel("  ");
		test1.setToolTipText("QQ空间");
		test1.setBounds(76,85,25,25);
		bg.add(test1);
		//QQ浏览器
		test2 = new JLabel("  ");
		test2.setToolTipText("QQ浏览器");
		test2.setBounds(8, 615, 30, 30);
		bg.add(test2);
		//应用宝
		test3 = new JLabel("  ");
		test3.setToolTipText("应用宝");
		test3.setBounds(230, 643, 60, 30);
		bg.add(test3);
		//QQ邮箱
		test4 = new JLabel("  ");
		test4.setToolTipText("QQ邮箱");
		test4.setBounds(117,85,25,25);
		bg.add(test4);
		//QQ天气
		test5 = new JLabel("  ");
		test5.setToolTipText("QQ天气");
		test5.setBounds(238,55,20,20);
		bg.add(test5);
		
		//腾讯应用中心
		test6 = new JLabel("  ");
		test6.setToolTipText("腾讯应用中心");
		test6.setBounds(175,87,20,20);
		bg.add(test6);
		//签名
		qianming =  new JLabel(myInfo.getHobit());
		qianming.setBounds(80, 58, 100, 30);
		bg.add(qianming);
		//查找
		look = new JLabel("   ");
		look.setToolTipText("查找");
		look.setBounds(180, 643, 30, 30);
		bg.add(look);
		//游戏
		youxi=new JLabel(" ");
		youxi.setToolTipText("游戏");
		youxi.setBounds(38, 615, 30, 30);
		bg.add(youxi);
		//状态
		online = new JPopupMenu("状态");
		zaixian = new JMenuItem("在线");
		yinshen = new JMenuItem("隐身");
		manglu = new JMenuItem("忙碌");
		
		online.add(zaixian);
		online.add(yinshen);
		online.add(manglu);
		chat = new JMenuItem("聊天");
		jvhaoyou = new JMenuItem("移动到好友分组");
		jvmate = new JMenuItem("移动到同学分组");
		jvjiaren = new JMenuItem("移动到家人分组");
		jvfriend = new JMenuItem("移动到朋友分组");
		jvhmd = new JMenuItem("移入黑名单");
		delete = new JMenuItem("删除好友");
		lookfriendinfo = new JMenuItem("查看好友资料");
		
		pop = new JPopupMenu();
		
		pop.add(chat);
		pop.add(jvhaoyou);
		pop.add(jvmate);
		pop.add(jvjiaren);
		pop.add(jvfriend);
		pop.add(jvhmd);
		pop.add(lookfriendinfo);
		pop.add(delete);
		//添加事件
		chat.addActionListener(this);
		jvhaoyou.addActionListener(this);
		jvmate.addActionListener(this);
		jvjiaren.addActionListener(this);
		jvfriend.addActionListener(this);
		jvhmd.addActionListener(this);
		lookfriendinfo.addActionListener(this);
		delete.addActionListener(this);
		youxi.addMouseListener(this);
		
		//实例化向量，分组向量
		allimfmation = new Vector<AccountVo>();
		vhaoyou = new Vector<AccountVo>();//好友
		vmate = new Vector<AccountVo>();//同学
		vjiaren  = new Vector<AccountVo>();//家人 
		vfriend = new Vector<AccountVo>();//朋友
		vhmd  = new Vector<AccountVo>();//黑名单
		
		list1 = new JList();
		list2 = new JList();
		list3 = new JList();
		list4 = new JList();
		list5 = new JList();
		
		//更新界面
		reflush();
		//设置透明的背景
		UIManager.put("TabbedPane.contentOpaque", false);
//		"好友","同学","家人","朋友","黑名单"
		jp = new JTabbedPane();
		jp.addTab("好友",list1);
		jp.addTab("同学",list2);
		jp.addTab("家人",list3);
		jp.addTab("朋友",list4);
		jp.addTab("黑名单",list5);
		jp.setBounds(0, 140, 281, 474);
		jp.setOpaque(false);
		bg.add(jp);
		
		//监听事件
		close.addMouseListener(this);
		min.addMouseListener(this);
		huanfu.addMouseListener(this);
		look.addMouseListener(this);
		head.addMouseListener(this);
		test.addMouseListener(this);
		test1.addMouseListener(this);
		test2.addMouseListener(this);
		test3.addMouseListener(this);
		test4.addMouseListener(this);
		test5.addMouseListener(this);
		test6.addMouseListener(this);
		
		list1.addMouseListener(this);
		list2.addMouseListener(this);
		list3.addMouseListener(this);
		list4.addMouseListener(this);
		list5.addMouseListener(this);
		
		//音乐
		music = new JLabel(" ");
		music.setToolTipText("音乐");
		music.setBounds(64, 615, 30, 30);
		bg.add(music);

		pop1 = new JPopupMenu();
		
		open = new JMenuItem("音乐开");
		clo = new JMenuItem("音乐关");
		
		pop1.add(open);
		pop1.add(clo);
		
		open.addActionListener(this);
		clo.addActionListener(this);
		music.addMouseListener(this);
		zaixian.addActionListener(this);
		yinshen.addActionListener(this);
		manglu.addActionListener(this);
		
		
		//启动线程
		new ReceiveThread().start();
		//发送登录广播
		SendCmd.sendAll(allimfmation, myInfo, Cmd.CMD_ONLINE);
		setSize(281, 671);
		setVisible(true);
		setLocation(700, 50);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {

		new MainUI(new AccountVo());
	}

	//打开聊天窗口
	@SuppressWarnings("deprecation")
	public ChatUI openChat(){
		//从Hashtable中获取根该朋友聊天的窗口信息
		ChatUI chat = chatWin.get(friendInfo.getQqCode());
		if(chat==null){
			chat = new ChatUI(myInfo,friendInfo);
			chatWin.put(friendInfo.getQqCode(), chat);
		}
		
		//显示
		chat.show();
		return chat;
	}
	//鼠标事件
	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==close) {//关闭
			//窗口马上要关闭时触发该事件，如果需要在任务栏绘图标就必须用该事件
			JOptionPane.showMessageDialog(this, "窗口马上要关闭");
			//更改为离线状态
			baseDAO.changeStatus(myInfo.getQqCode(), Cmd.STATUS[1]);
			myInfo.setOnlinestatus(Cmd.STATUS[1]);
			SendCmd.sendAll(allimfmation, myInfo, Cmd.CMD_OFFLINE);
			reflush();
			System.exit(0);
		}else if (e.getSource()==min) {//最小化
			setState(JFrame.HIDE_ON_CLOSE);
		}else if (e.getSource()==huanfu) {//换肤
			//换肤函数，运用文件选择框
			JFileChooser dlg = new JFileChooser();
			//打开文件对话框
			dlg.setDialogType(JFileChooser.OPEN_DIALOG);
			dlg.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //设置对话框标题
			dlg.setDialogTitle("更换皮肤");
			if (dlg.showOpenDialog(this) == dlg.APPROVE_OPTION) {
            	File file = dlg.getSelectedFile();
            	String path = file.getPath();
            	bg.setIcon(new ImageIcon(path));
            }
		}else if (e.getSource()==look) {//查看好友资料
			new FindUI(myInfo);
		}else if (e.getSource()==head) {//双击头像修改资料
			if (e.getClickCount()==2) {
				new UpdateMyInfoUI(myInfo,this);
				
			}else if (e.getButton()==3) {
				online.show(this,e.getX(),e.getY());
			}
			//好友
		}else if (e.getSource()==list1) {
			if(list1.getSelectedIndex()>=0){
				friendInfo = vhaoyou.get(list1.getSelectedIndex());
			}
			if (e.getButton()==3) {
					if(list1.getSelectedIndex()>=0){
						pop.show(list1, e.getX(), e.getY());
					}
			}else if (e.getClickCount()==2) {//双击
				if(list1.getSelectedIndex()>=0){
					
					openChat();
				}
				
			}
		//同学
		}else if (e.getSource()==list2) {
			if(list2.getSelectedIndex()>=0){
				friendInfo = vmate.get(list2.getSelectedIndex());
			}
			if (e.getButton()==3) {
				if(list2.getSelectedIndex()>=0){
					pop.show(list2, e.getX(), e.getY());
				}
			}else if (e.getClickCount()==2) {
				if(list2.getSelectedIndex()>=0){
					
					openChat();
				}
				
			}
			//家人
		}else if(e.getSource()==list3){
			if(list3.getSelectedIndex()>=0){
				friendInfo = vjiaren.get(list3.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(list3.getSelectedIndex()>=0){
					pop.show(list3, e.getX(), e.getY());
				}
			}else if(e.getClickCount()==2){//双击
				if(list3.getSelectedIndex()>=0){
					openChat();
				}
			}
			//朋友
		}else if (e.getSource()==list4) {
			if(list4.getSelectedIndex()>=0){
				friendInfo = vfriend.get(list4.getSelectedIndex());
			}
			if (e.getButton()==3) {
				
				if(list4.getSelectedIndex()>=0){
					pop.show(list4, e.getX(), e.getY());
				}
			}else if (e.getClickCount()==2) {
				if(list4.getSelectedIndex()>=0){
					
					openChat();
				}
				
			}
			//黑名单
		}else if (e.getSource()==list5) {
			
			if (e.getButton()==3) {
				if(list5.getSelectedIndex()>=0){
					pop.show(list5, e.getX(), e.getY());
					friendInfo = vhmd.get(list5.getSelectedIndex());
				}
			}
		}else if (e.getSource() == close) {//关闭
			System.exit(0);
		}else if (e.getSource()==music) {//音乐
			if (e.getButton()==3) {//右键
				pop1.show(this, 64, 615);
			}
			
		}else if (e.getSource()==youxi) {//游戏
			new ShaoRei();
		}else if(e.getSource()==test){//腾讯网迷你版
			new Openweb("test.html");
			
		}else if(e.getSource()==test1){//QQ空间
			new Openweb("test1.html");
		}else if(e.getSource()==test4){//QQ邮箱
			new Openweb("test4.html");
		}else if(e.getSource()==test2){//QQ浏览器
			new Openweb("test2.html");
		}else if(e.getSource()==test3){//应用宝
			new Openweb("test3.html");
		}else if(e.getSource()==test5){//QQ天气
			new Openweb("test5.html");
		}else if(e.getSource()==test6){//腾讯应用中心
			new Openweb("test6.html");
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==lookfriendinfo) {
			//查看好友资料
			new LookInfoUI(friendInfo);
		}else if (e.getSource()==chat) {
//			只设置了好友栏的右键打开聊天界面
//			friendInfo = vhaoyou.get(list5.getSelectedIndex());
//			new ChatUI(myInfo,friendInfo);
			//右键点击打开聊天界面
			openChat();
		}else if (e.getSource()==open) {
			sound4 = new Sound4();
		}else if(e.getSource()==clo){
			if(sound4.isplay()){
				sound4.mystop();
			}
		}else if(e.getSource()==jvhaoyou){//移动分组(好友，同学，家人，朋友，黑名单)
			baseDAO.moveGroup(myInfo.getQqCode(), friendInfo.getQqCode(), Cmd.GROUPNAME[0]);
			reflush();
		}else if(e.getSource()==jvmate){
			baseDAO.moveGroup(myInfo.getQqCode(), friendInfo.getQqCode(), Cmd.GROUPNAME[1]);
			reflush();
		}else if(e.getSource()==jvjiaren){
			baseDAO.moveGroup(myInfo.getQqCode(), friendInfo.getQqCode(), Cmd.GROUPNAME[2]);
			reflush();
		}else if(e.getSource()==jvfriend){
			baseDAO.moveGroup(myInfo.getQqCode(), friendInfo.getQqCode(), Cmd.GROUPNAME[3]);
			reflush();
		}else if(e.getSource()==jvhmd){
			baseDAO.moveGroup(myInfo.getQqCode(), friendInfo.getQqCode(), Cmd.GROUPNAME[4]);
			reflush();
		}else if(e.getSource()==zaixian){//在线状态
			@SuppressWarnings("unused")
			String status = myInfo.getOnlinestatus();
			String headImg = myInfo.getHeadImg();
			String filename = "";
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + fix;
			head.setIcon(new ImageIcon(filename));
			baseDAO.changeStatus(myInfo.getQqCode(), Cmd.STATUS[0]);
			SendCmd.sendAll(allimfmation, myInfo, Cmd.CMD_CHANGESTATE);
		}else if(e.getSource()==yinshen){//隐身状态
			JOptionPane.showMessageDialog(this, "隐身");
			@SuppressWarnings("unused")
			String status = myInfo.getOnlinestatus();
			String headImg = myInfo.getHeadImg();
			String filename = "";
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre +"_w"+fix;
			head.setIcon(new ImageIcon(filename));
			baseDAO.changeStatus(myInfo.getQqCode(), Cmd.STATUS[3]);
			SendCmd.sendAll(allimfmation, myInfo, Cmd.CMD_CHANGESTATE);
		}else if(e.getSource()==manglu){//忙碌状态
			JOptionPane.showMessageDialog(this, "忙碌");
			@SuppressWarnings("unused")
			String status = myInfo.getOnlinestatus();
			String headImg = myInfo.getHeadImg();
			String filename = "";
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + "_l"+fix;
			head.setIcon(new ImageIcon(filename));
			baseDAO.changeStatus(myInfo.getQqCode(), Cmd.STATUS[2]);
			SendCmd.sendAll(allimfmation, myInfo, Cmd.CMD_CHANGESTATE);
		}else if(e.getSource()==delete){//删除好友
			baseDAO.delFriend(myInfo.getQqCode(), friendInfo.getQqCode());
			reflush();
			SendMsg msg = new SendMsg();
			msg.cmd = Cmd.CMD_DELFRIEND;
			msg.myInfo=myInfo;
			msg.friendInfo = friendInfo;
			SendCmd.send(msg);
		}
	}

	@SuppressWarnings("unchecked")
	public void reflush() {
		//获取所有好友的信息
		allimfmation = baseDAO.getMyFriend(myInfo.getQqCode());
		//清空个个分组的数据，重新赋值
		//好友，同学，家人，朋友，黑名单
		vhaoyou.clear();
		vmate.clear();
		vjiaren.clear();
		vfriend.clear();
		vhmd.clear();
		//循环保存数据
		for (AccountVo table : allimfmation) {
//			"好友","同学","家人","朋友","黑名单"
			String groupname = table.getGroupName();
			//区别各个好友的类型，区分各个分组
			if(groupname.equals(Cmd.GROUPNAME[0])){
				vhaoyou.add(table);
			}else if(groupname.equals(Cmd.GROUPNAME[1])){
				vmate.add(table);
			}else if(groupname.equals(Cmd.GROUPNAME[2])){
				vjiaren.add(table);
			}else if(groupname.equals(Cmd.GROUPNAME[3])){
				vfriend.add(table);
			}else if(groupname.equals(Cmd.GROUPNAME[4])){
				vhmd.add(table);
			}
			//初始化List界面的显示
			list1.setModel(new DataModel(vhaoyou));
			list2.setModel(new DataModel(vmate));
			list3.setModel(new DataModel(vjiaren));
			list4.setModel(new DataModel(vfriend));
			list5.setModel(new DataModel(vhmd));	
			
			list1.setCellRenderer(new MyfriendHead(vhaoyou));
			list2.setCellRenderer(new MyfriendHead(vmate));
			list3.setCellRenderer(new MyfriendHead(vjiaren));
			list4.setCellRenderer(new MyfriendHead(vfriend));
			list5.setCellRenderer(new MyfriendHead(vhmd));
		}
	}

	//显示文本信息
	@SuppressWarnings("rawtypes")
	class DataModel extends AbstractListModel {
		Vector<AccountVo> data;
		public DataModel() {
				
		}
		public DataModel(Vector<AccountVo> data) {
			this.data = data;
		}
	    //系统自动运行，下标从0开始计算
	    public Object getElementAt(int index) {
	    	AccountVo info = data.get(index);
	    	//返回当前的信息，QQ号码，签名，QQ昵称
	    	return info.getNickName()+"("+info.getQqCode()+")<"+info.getRemark()+">";
	    }

	    public int getSize() {

	        return data.size();
	    }
	}
	// 获取好友头像
	class MyfriendHead extends DefaultListCellRenderer {
		Vector<AccountVo> datas;
		public MyfriendHead(Vector<AccountVo> datas) {
			
			this.datas = datas;
		}
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value,
					index, isSelected, cellHasFocus);
			if (index>=0 && index < datas.size()) {
				AccountVo user =  datas.get(index);
				String status = user.getOnlinestatus();
				String headImg = user.getHeadImg();
				String filename = "";
				if(status.equals(Cmd.STATUS[0])){//在线
					filename = headImg;
				}else if(status.equals(Cmd.STATUS[1])){//离线
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_h"+fix;
				}else if(status.equals(Cmd.STATUS[2])){//忙碌
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_l"+fix;
				}else if(status.equals(Cmd.STATUS[3])){//隐身
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_w"+fix;
				}
				// 给列表中好友状态设置头像
				setIcon(new ImageIcon(filename));
				System.out.println(filename);
				//设置文本内容
				setText(user.getNickName()+"("+user.getQqCode()+")<"+user.getRemark()+">");
				System.out.println(user.getHeadImg());
			}
			// 设置字体颜色
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			//设置有效
			setEnabled(list.isEnabled());
			//设置默认字体
			setFont(list.getFont());
			//设置透明背景
			setOpaque(true);
			return this;
		}
	}
	
	//定义内部线程类
	class ReceiveThread extends Thread{
		public ReceiveThread() {
			
		}
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			try {
				@SuppressWarnings("resource")
				DatagramSocket socket = new DatagramSocket(myInfo.getPort());
				while(true){
//					最多接收512k的文件
					byte b[] = new byte[1024*512];
					DatagramPacket pack = new DatagramPacket(b,0,b.length);
					//接收信息
					socket.receive(pack);
					ByteArrayInputStream bais = new ByteArrayInputStream(pack.getData());
					ObjectInputStream ois = new ObjectInputStream(bais);
					SendMsg msg = (SendMsg)ois.readObject();
					myInfo = msg.friendInfo;
					friendInfo = msg.myInfo;
					switch(msg.cmd){
					case Cmd.CMD_ONLINE: //登录(上线)
						System.out.println("上线通知！！！");
						new TipUi(myInfo);
						reflush();
						new Sound(msg.cmd);
						break;
					case Cmd.CMD_LEVEL://离开
					case Cmd.CMD_BUYS://忙碌
						reflush();
						break;
					case Cmd.CMD_OFFLINE://离线
						
						reflush();
						break;
					case Cmd.CMD_CHANGESTATE://更改状态
						reflush();
						break;
					case Cmd.CMD_SEND: //发送聊天消息
						new Sound(msg.cmd);
						System.out.println("接收聊天消息....");
						ChatUI chat = openChat();
						try {
								chat.appendView(msg.myInfo.getNickName(), msg.doc);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						break;
					case Cmd.CMD_SHAKE://抖动
						new Sound(msg.cmd);
						chat = openChat();
						chat.shake();
						System.out.println("接收抖动消息");
						break;
					case Cmd.CMD_ADDFRI: //添加好友
						new Sound(msg.cmd);
						String str = "【"+friendInfo.getNickName()+"】请求添加你为好友，是否同意？";
						SendMsg msg2 = new SendMsg();
						if(JOptionPane.showConfirmDialog(null, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							msg2.cmd = Cmd.CMD_ARGEE;
							baseDAO.addFriend(myInfo.getQqCode(), friendInfo.getQqCode());
							reflush();
						}else{
							msg2.cmd = Cmd.CMD_REFUSE;
						}
						msg2.myInfo = myInfo;
						msg2.friendInfo = friendInfo;
						SendCmd.send(msg2);
						break;
					case Cmd.CMD_ARGEE://同意添加好友
						reflush();
						break;
					case Cmd.CMD_REFUSE://拒绝添加好友
						str = "【"+friendInfo.getNickName()+"】拒绝了你的好友请求。";
						JOptionPane.showMessageDialog(null, str);
						break;
					case Cmd.CMD_DELFRIEND://删除好友
						reflush();
						break;
					case Cmd.CMD_FILE://接收文件
						str = friendInfo.getNickName()+"发送了一个【"+msg.fileName+"文件】，是否接收？";
						if(JOptionPane.showConfirmDialog(null, "是否接收文件","接收文件",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							JFileChooser chooser = new JFileChooser(" ");
				            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				            chooser.setDialogTitle("保存文件");
				            if (chooser.showOpenDialog(null) == chooser.APPROVE_OPTION) {
				            	String ext = msg.fileName.substring(msg.fileName.indexOf('.'),msg.fileName.length());
				            	String filename = chooser.getSelectedFile().getAbsolutePath()+ext;
				            	FileOutputStream fos = new FileOutputStream(filename);
				            	fos.write(msg.b);
				            	fos.flush();
				            	fos.close();
				            }
						}
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==online){
			 String status = online.getSelectionModel().toString();
			 changeState();
			 baseDAO.changeStatus(myInfo.getQqCode(), status);
			 SendCmd.sendAll(allimfmation , myInfo, Cmd.CMD_CHANGESTATE);
		}
	}
	//更改状态
	public void changeState(){
		String status = online.getSelectionModel().toString();
		String filename=myInfo.getHeadImg();
		String headImg= myInfo.getHeadImg();
		if(status.equals(Cmd.STATUS[0])){
			filename = headImg;
		}else if(status.equals(Cmd.STATUS[2])){
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + "_l"+fix;
		}else if(status.equals(Cmd.STATUS[3])){
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + "_w"+fix;
		}
		bg.setIcon(new ImageIcon(filename));
	}
	//更改状态
	public void changeState(String status){
		String filename=myInfo.getHeadImg();
		String headImg= myInfo.getHeadImg();
		if(status.equals(Cmd.STATUS[0])){
			filename = headImg;
		}else if(status.equals(Cmd.STATUS[2])){
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + "_l"+fix;
		}else if(status.equals(Cmd.STATUS[3])){
			int pos = headImg.indexOf('.');
			String pre = headImg.substring(0,pos);
			String fix = headImg.substring(pos,headImg.length());
			filename = pre + "_w"+fix;
		}
		bg.setIcon(new ImageIcon(filename));
	}

	public void windowActivated(WindowEvent e) {
		
		
	}

	public void windowClosed(WindowEvent e) {
		
		
	}

	
	public void windowClosing(WindowEvent e) {

	}

	public void windowDeactivated(WindowEvent e) {
		
		
	}

	public void windowDeiconified(WindowEvent e) {
		
		
	}
	
	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		
		
	}
	
}
//播放背景音乐
class Sound4 {
	String path = new String("musics\\");
	// 默认背景音乐
	String file = new String("569.mid");
	Sequence seq;
	Sequencer midi;
	// 判断是否在播放音乐
	boolean sign;

	public Sound4() {
		loadSound();
	}

//播放背景音乐的函数
void loadSound() {
	try {
		// 读取音频文件
		seq = MidiSystem.getSequence(new File(path + file));
		// 获取一个音频设备
		midi = MidiSystem.getSequencer();
		// 打开音频设备
		midi.open();
		// 把音频文件设置到播放设备中
		midi.setSequence(seq);
		// 开始播放音乐
		midi.start();
		// 循环播放
		midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	// 代表正在播放背景音乐
	sign = true;
}

// 停止播放背景音乐
void mystop() {
	midi.stop();
	midi.close();
	sign = false;
}

// 判断是否在播放背景音乐
boolean isplay() {
	return sign;
}

// 设置在下拉框中选择的背景音乐文件名称
void setMusic(String e) {
	file = e;
}
}

