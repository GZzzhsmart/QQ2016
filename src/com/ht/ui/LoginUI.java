package com.ht.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.ht.db.BaseDAO;
import com.ht.vo.AccountVo;
import com.ht.vo.FriendVo;
//QQ登录主界面
@SuppressWarnings({ "unused", "serial" })
public class LoginUI extends JFrame implements MouseListener,ActionListener,ItemListener{
	JLabel lblMin,lblClose,lblHead,lblReg,lblshezhi,lblForgetPwd,lblduozhanghao,lblerweima;
	JButton btnLogin;
	JPasswordField txtPassword;
	@SuppressWarnings("rawtypes")
	JComboBox cbQQcode;
	JCheckBox cbpwd,cbAutoLogin;
//	private TrayIcon trayIcon = null;
	HashMap<Integer, AccountVo> user =null;
	@SuppressWarnings("rawtypes")
	public LoginUI() {
		//隐藏标题栏
		setUndecorated(true);
		setDragable();
		//设置图标
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("head/touxiang.png");
		setIconImage(img);
		JLabel bg = new JLabel(new ImageIcon("images/QQ.gif"));
		//绝对布局
		bg.setLayout(null);
		add(bg);
		lblshezhi = new JLabel("▼");
		lblshezhi.setForeground(Color.WHITE);
		lblshezhi.setFont(new Font("黑体",Font.BOLD,12));
		lblshezhi.setToolTipText("设置");
		lblMin = new JLabel("-");
		lblMin.setToolTipText("最小化");
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("黑体",Font.BOLD,20));
		lblClose = new JLabel("×");
		lblClose.setToolTipText("关闭");
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("黑体",Font.BOLD,18));
		
		lblMin.setBounds(380, 7, 20, 20);
		lblClose.setBounds(405, 4, 20, 20);
		lblshezhi.setBounds(349,5,20,20);
		//监听
		lblMin.addMouseListener(this);
		lblClose.addMouseListener(this);
		lblshezhi.addMouseListener(this);
		bg.add(lblMin);
		bg.add(lblClose);
		bg.add(lblshezhi);
		lblHead = new JLabel(new ImageIcon("head/touxiang.png"));
		//ppt
		lblduozhanghao = new JLabel(" ");
		lblduozhanghao.setBounds(14, 305, 22,20);
		//二维码登录
		lblerweima = new JLabel(" ");
		lblerweima.setBounds(405, 302, 21,20);
		lblerweima.setToolTipText("二维码登录");
		//注册账号
		lblReg = new JLabel("     ");
		lblReg.setBounds(330, 185, 70, 50);
		//找回密码
		lblForgetPwd = new JLabel("    ");
		lblForgetPwd.setBounds(334, 225, 80, 30);
		//记住密码
		cbQQcode = new JComboBox();
		//自动登录
		txtPassword = new JPasswordField();
		//登录
		btnLogin = new JButton("");
		cbpwd = new JCheckBox("");
		cbAutoLogin = new JCheckBox("");
		
		//设置下拉框中可以直接输入内容
		cbQQcode.setEditable(true);
		cbQQcode.setToolTipText("账号");
		cbQQcode.setBounds(134, 193, 194, 30);
		
		txtPassword.setBounds(135, 224, 195, 30);
		txtPassword.setToolTipText("密码");
		
		//头像
		lblHead.setBounds(43, 195, 80,80);
		//登录
		btnLogin.setBounds(135, 288, 195, 30);
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setOpaque(false);
		//记住密码
		cbpwd.setBounds(131, 253, 80, 30);
		cbpwd.setOpaque(false);
		//自动登录
		cbAutoLogin.setBounds(258, 253, 80, 30);
		cbAutoLogin.setOpaque(false);
	
		bg.add(cbQQcode);
		bg.add(txtPassword);
		bg.add(lblduozhanghao);
		bg.add(lblerweima);
		bg.add(lblReg);
		bg.add(lblForgetPwd);
		bg.add(lblHead);
		bg.add(btnLogin);
		bg.add(cbpwd);
		bg.add(cbAutoLogin);
		//事件监听
		lblReg.addMouseListener(this);
		lblduozhanghao.addMouseListener(this);
		lblerweima.addMouseListener(this);
		lblForgetPwd.addMouseListener(this);
		
		cbpwd.addActionListener(this);
		cbAutoLogin.addActionListener(this);
		btnLogin.addActionListener(this);
		cbQQcode.addItemListener(this);
		//读取文件内容
		readFile();
		
		
		setSize(428, 330);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new LoginUI();
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lblMin){//最小化
		this.setState(JFrame.HIDE_ON_CLOSE);
			
		}else if(e.getSource()==lblClose){//关闭
			System.exit(0);
		}else if(e.getSource()==lblReg){//打开注册界面
			new RegUI();
		}else if(e.getSource()==lblForgetPwd){//找回密码
			JOptionPane.showMessageDialog(this, "找回密码");
		}else if(e.getSource()==lblshezhi){//打开设置窗口
			new Shezhi();
		}else if(e.getSource()==lblduozhanghao){
			new Openweb("QQ聊天项目.pptx");
		}
		
	}
	//设置界面可以移动的办法
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				LoginUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());

				isDragged = true;
				LoginUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(LoginUI.this.getLocation().x + e.getX() - tmp.x,
							LoginUI.this.getLocation().y + e.getY() - tmp.y);
					LoginUI.this.setLocation(loc);
				}
			}
		});
	}
//	//系统托盘
//	public void jbInit() {
//
//		if (SystemTray.isSupported()) { // 检查当前系统是否支持系统托盘
//
//			SystemTray tray = SystemTray.getSystemTray();// 获取表示桌面托盘区的
//															// SystemTray 实例。
//
//			Image image = Toolkit.getDefaultToolkit().getImage("image/111.png");
//
//			PopupMenu popupMenu = new PopupMenu();
//
//			MenuItem exitItem = new MenuItem("关闭");
//
//			MenuItem menuItema = new MenuItem("在线状态");
//
//			MenuItem menuItemb = new MenuItem("离线");
//
//			final MenuItem menuItemc = new MenuItem("注册用户");
//
//			MenuItem menuItemd = new MenuItem("主界面");
//
//			exitItem.addActionListener(new ActionListener() {
//
//				public void actionPerformed(ActionEvent e) {
//
//					try {
//
//						System.exit(0);
//
//					} catch (Exception ex) {
//
//						ex.printStackTrace();
//
//					}
//
//				}
//
//			});
//			menuItemc.addActionListener(new ActionListener() {
//
//				public void actionPerformed(ActionEvent e) {
//
//					try {
//
//						new RegUI();
//
//					} catch (Exception ex) {
//
//						ex.printStackTrace();
//
//					}
//
//				}
//
//			});
//
//			popupMenu.add(menuItema);
//
//			popupMenu.add(menuItemb);
//
//			popupMenu.add(menuItemc);
//
//			popupMenu.add(menuItemd);
//
//			popupMenu.add(exitItem);
//
//			trayIcon = new TrayIcon(image, "系统托盘{kissjava}", popupMenu);
//
//			trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
//			});
//			try {
//				tray.add(trayIcon); // 将 TrayIcon 添加到 SystemTray。
//
//			} catch (AWTException e) {
//
//				System.err.println(e);
//
//			}
//
//		} else {
//
//			System.out.println("你的系统不支持系统托盘");
//
//		}
//
//		ImageIcon icon = new ImageIcon();
//
//		try {
//
//		} catch (Exception e) {
//
//		}
//		JLabel label = new JLabel();
//		label.setIcon(icon);
//		this.add(label);
//	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
		
		
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin){
			String qqcode="";
			String pwd = txtPassword.getText().trim();
			if(cbQQcode.getSelectedItem() == null){
				JOptionPane.showMessageDialog(this, "请输入QQ号码");
				return;
			}
			if(pwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入登录密码");
				return;
			}
			qqcode = cbQQcode.getSelectedItem().toString();
			
			AccountVo account = new AccountVo();
			account.setQqCode(Integer.parseInt(qqcode));
			account.setPassword(pwd);
			account = new BaseDAO().login(account);
			
			AccountVo myInfo = new AccountVo();
			
			if(account==null){
				JOptionPane.showMessageDialog(this, "登录失败，用户名或密码错误!");
				return;
			}else{
				//保存登录成功的QQ号码到文件中
				save(account);
				//登录成功后，关闭当前窗口，
				dispose();
				//显示主窗口,同时把登录成功的个人信息account对象，传递到主窗口
				new MainUI(account);
			}
			
			
		}else if(e.getSource()==cbpwd){
			JOptionPane.showMessageDialog(this, "记住密码");
		}
		
	}
	
	//保存登录成功的QQ号码到文件中
	@SuppressWarnings("unchecked")
	public void save(AccountVo account){
		HashMap<Integer, AccountVo> user =null;
		File file = new File("account.dat");
		try {
			if(!file.exists()){//第一次使用QQ
					file.createNewFile();
					user = new HashMap<Integer, AccountVo>();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				//读取文件内容
				user = (HashMap<Integer, AccountVo>)ois.readObject();
				fis.close();
				ois.close();
			}
			//新登录的用户信息保存到hashMap中
			user.put(account.getQqCode(), account);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.dat"));
			oos.writeObject(user);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//读取文件内容
	@SuppressWarnings({ "resource", "unchecked" })
	public void readFile(){
		try{
			File file = new File("account.dat");
			//文件不存在就结束
			if(!file.exists()){
				return;
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//成员变量，不是局部变量
			user = (HashMap<Integer, AccountVo>)ois.readObject();
			Set<Integer> set = user.keySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				cbQQcode.addItem(it.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cbQQcode){
			if(!cbQQcode.getSelectedItem().toString().equals("") && user!=null){
				int qqcode = Integer.parseInt(cbQQcode.getSelectedItem().toString());
				AccountVo account = user.get(qqcode);
				//如果等于空就不进入循环，如果不等于空则执行下列语句
				if(account!=null){
					txtPassword.setText(account.getPassword());
					lblHead.setIcon(new ImageIcon(account.getHeadImg()));
				}
			}
		}
	}
}
