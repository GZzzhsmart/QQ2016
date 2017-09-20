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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import com.ht.db.BaseDAO;
import com.ht.vo.AccountVo;

@SuppressWarnings({ "unused", "serial" })
public class RegUI extends JFrame implements ActionListener ,MouseListener{
	//系统托盘
	private TrayIcon trayIcon = null;
	//声明标题
	private JLabel lblQQcode, lblnickName, lblHeadImg, lblPassword,
			lblcfgpassword, lblAge;
	private JLabel lblSex, lblNation, lblStar, lblBlood, lblIpAddr, lblport;
	private JLabel lblRemark, lblHobit;
	private JLabel lblBg, head, head1;
	//文本框
	private JTextField txtQQcode, txtNickName, txtAge, txtIpAddr, txtPort,
			txtHobit;
	//密码框
	private JPasswordField txtPassword, txtcfgPassword;
	//设置单选框
	private JRadioButton rbMale, rbRemale;
	//设置下拉框
	@SuppressWarnings("rawtypes")
	private JComboBox cbNation, cbStar, cbBlood;
	//设置文本区域框
	private JTextArea taRemark;
	//声明按钮分组
	private ButtonGroup bg;
	//声明按钮
	private JButton btnSave, btnClose, head3;
	String headfase = "head/3.jpg";
	private String sNation[] = { "汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族",
			"壮族","布依族","朝鲜族","满族","侗族","瑶族","白族",
			"土家族","哈尼族","哈萨克族","傣族","黎族","僳僳族","佤族",
			"高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",
			"土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族",
			"仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族","乌孜别克族",
			"俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族",
			"独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族",
 };

	private String sStar[] = { "天蝎座", "天秤座", "水瓶座", "处女座", "摩羯座", "双子座", "白羊座",
			"狮子座", "射手座", "金牛座", "双鱼座", "巨蟹座" };

	private String sBlood[] = { "A", "B", "O", "AB" };

	private String sHeadImg[] = { "head/0.jpg", "head/1.jpg", "head/2.jpg",
			"head/3.jpg", "head/4.jpg", "head/5.jpg", "head/6.jpg",
			"head/7.jpg", "head/8.jpg", "head/9.jpg", "head/10.jpg" };

	private ImageIcon[] headIcon = { new ImageIcon(sHeadImg[0]),
			new ImageIcon(sHeadImg[1]), new ImageIcon(sHeadImg[2]),
			new ImageIcon(sHeadImg[3]), new ImageIcon(sHeadImg[4]),
			new ImageIcon(sHeadImg[5]), new ImageIcon(sHeadImg[6]),
			new ImageIcon(sHeadImg[7]), new ImageIcon(sHeadImg[8]),
			new ImageIcon(sHeadImg[9]), new ImageIcon(sHeadImg[10]), 
			
		};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegUI() {
		super("QQ用户注册");
//		jbInit();
		//是否隐藏边框
		//setUndecorated(true);
		Sound sound = new Sound();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("images/qt.png");
		setIconImage(image);
		lblBg = new JLabel(new ImageIcon("images/17.jpg"));
		add(lblBg);
		lblBg.setLayout(null);
		JLabel title = new JLabel("用户注册", JLabel.CENTER);
		title.setFont(new Font("华文行楷", Font.BOLD, 45));
		title.setForeground(Color.RED);
		title.setBounds(250, 30, 200, 40);
		lblBg.add(title);

		//head1 = new JLabel(new ImageIcon("images/8.gif"));
		//head1.setBounds(600, 30, 100, 120);
		//lblBg.add(head1);
//		head = new JLabel(new ImageIcon("images/01.gif"));
//		head.setBounds(240, 230, 120, 140);
//		lblBg.add(head);
		
		//QQ号码
		lblQQcode = new JLabel("QQ号码:", new ImageIcon("images/2.gif"), 0);
		lblQQcode.setFont(new Font("黑体", Font.BOLD, 20));
		lblQQcode.setForeground(Color.CYAN);
		//昵称
		lblnickName = new JLabel("昵称:", new ImageIcon("images/dog.gif"), 0);
		lblnickName.setFont(new Font("黑体", Font.BOLD, 20));
		lblnickName.setForeground(Color.ORANGE);
		//头像
		lblHeadImg = new JLabel("头像:", new ImageIcon("images/dog.gif"), 0);
		lblHeadImg.setFont(new Font("黑体", Font.BOLD, 20));
		lblHeadImg.setForeground(Color.GREEN);
		//登录密码
		lblPassword = new JLabel("登录密码:", new ImageIcon("images/dog.gif"), 0);
		lblPassword.setFont(new Font("黑体", Font.BOLD, 20));
		lblPassword.setForeground(Color.BLUE);
		//确认密码
		lblcfgpassword = new JLabel("确认密码:", new ImageIcon("images/dog.gif"), 0);
		lblcfgpassword.setFont(new Font("黑体", Font.BOLD, 20));
		lblcfgpassword.setForeground(Color.CYAN);
		//年龄
		lblAge = new JLabel("年龄:", new ImageIcon("images/dog.gif"), 0);
		lblAge.setFont(new Font("黑体", Font.BOLD, 20));
		lblAge.setForeground(Color.YELLOW);
		//性别
		lblSex = new JLabel("性别:", new ImageIcon("images/dog.gif"), 0);
		lblSex.setFont(new Font("黑体", Font.BOLD, 20));
		lblSex.setForeground(Color.ORANGE);
		//民族
		lblNation = new JLabel("民族:", new ImageIcon("images/2.gif"), 0);
		lblNation.setFont(new Font("黑体", Font.BOLD, 20));
		lblNation.setForeground(Color.MAGENTA);
		//星座
		lblStar = new JLabel("星座:", new ImageIcon("images/dog.gif"), 0);
		lblStar.setFont(new Font("黑体", Font.BOLD, 20));
		lblStar.setForeground(Color.ORANGE);
		//血型
		lblBlood = new JLabel("血型:", new ImageIcon("images/2.gif"), 0);
		lblBlood.setFont(new Font("黑体", Font.BOLD, 20));
		lblBlood.setForeground(Color.GREEN);
		//兴趣爱好
		lblHobit = new JLabel("兴趣爱好:", new ImageIcon("images/dog.gif"), 0);
		lblHobit.setFont(new Font("黑体", Font.BOLD, 20));
		lblHobit.setForeground(Color.CYAN);
		//IP地址
		lblIpAddr = new JLabel("IP地址:", new ImageIcon("images/2.gif"), 0);
		lblIpAddr.setFont(new Font("黑体", Font.BOLD, 20));
		lblIpAddr.setForeground(Color.BLUE);
		//端口
		lblport = new JLabel("端口:", new ImageIcon("images/dog.gif"), 0);
		lblport.setFont(new Font("黑体", Font.BOLD, 20));
		lblport.setForeground(Color.CYAN);
		//备注
		lblRemark = new JLabel("备注:", new ImageIcon("images/2.gif"), 0);
		lblRemark.setFont(new Font("黑体", Font.BOLD, 20));
		lblRemark.setForeground(Color.GREEN);

		txtQQcode = new JTextField(10);
		txtQQcode.setText("亲，一会儿告诉你哦！");
		txtQQcode.setEditable(false);
		txtNickName = new JTextField(10);

		head3 = new JButton(new ImageIcon(headfase));

		txtPassword = new JPasswordField(10);
		txtPassword.setEchoChar('*');
		txtcfgPassword = new JPasswordField(10);
		txtcfgPassword.setEchoChar('*');
		txtAge = new JTextField(5);

		rbMale = new JRadioButton("男神", true);
		rbMale.setFont(new Font("黑体", Font.BOLD, 20));
		rbMale.setForeground(Color.RED);

		rbRemale = new JRadioButton("女神");
		rbRemale.setFont(new Font("黑体", Font.BOLD, 20));
		rbRemale.setForeground(Color.GREEN);

		bg = new ButtonGroup();
		bg.add(rbMale);
		bg.add(rbRemale);
		cbNation = new JComboBox(sNation);
		cbStar = new JComboBox(sStar);
		cbBlood = new JComboBox(sBlood);
		txtHobit = new JTextField(20);
		InetAddress addr = null;

		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		txtIpAddr = new JTextField(addr.getHostAddress());
		txtPort = new JTextField(5);
		txtPort.setText("亲，我会告诉你的，别急嘛");
		txtPort.setEditable(false);
		taRemark = new JTextArea(3, 80);
		Icon image1 = new ImageIcon("images/dog.gif");

		lblQQcode.setBounds(20, 100, 120, 30);
		txtQQcode.setBounds(150, 100, 180, 30);
		lblnickName.setBounds(20, 140, 120, 30);
		txtNickName.setBounds(150, 140, 180, 30);

		lblHeadImg.setBounds(350, 75, 120, 100);
		head3.setBounds(480, 80, 80,80);
		lblPassword.setBounds(20, 180, 120, 30);
		txtPassword.setBounds(150, 180, 180, 30);
		lblcfgpassword.setBounds(360, 180, 120, 30);
		txtcfgPassword.setBounds(480, 180, 160, 30);

		lblAge.setBounds(20, 220, 120, 30);
		txtAge.setBounds(150, 220, 90, 30);
		lblSex.setBounds(400, 210, 80, 60);
		rbMale.setBounds(480, 210, 80, 60);
		rbMale.setOpaque(false);
		rbRemale.setBounds(540, 210, 80, 60);
		rbRemale.setOpaque(false);

		lblNation.setBounds(20, 260, 120, 30);
		cbNation.setBounds(150, 260, 90, 30);
		lblStar.setBounds(20, 300, 120, 30);
		cbStar.setBounds(150, 300, 90, 30);
		lblBlood.setBounds(20, 340, 120, 30);
		cbBlood.setBounds(150, 340, 90, 30);
		lblHobit.setBounds(20, 380, 120, 30);
		txtHobit.setBounds(150, 380, 490, 30);
		lblIpAddr.setBounds(360, 280, 120, 30);
		txtIpAddr.setBounds(480, 280, 160, 30);
		lblport.setBounds(360, 320, 120, 30);
		txtPort.setBounds(480, 320, 160, 30);
		lblRemark.setBounds(20, 420, 120, 30);
		taRemark.setBounds(150, 420, 490, 60);

		lblBg.add(lblQQcode);
		lblBg.add(txtQQcode);
		lblBg.add(lblnickName);
		lblBg.add(txtNickName);
		lblBg.add(lblHeadImg);
		lblBg.add(head3);
		lblBg.add(lblPassword);
		lblBg.add(txtPassword);
		lblBg.add(lblcfgpassword);
		lblBg.add(txtcfgPassword);
		lblBg.add(lblAge);
		lblBg.add(txtAge);
		lblBg.add(lblSex);
		lblBg.add(rbMale);
		lblBg.add(rbRemale);
		lblBg.add(lblNation);
		lblBg.add(cbNation);
		lblBg.add(lblStar);
		lblBg.add(cbStar);
		lblBg.add(lblBlood);
		lblBg.add(cbBlood);
		lblBg.add(lblHobit);
		lblBg.add(txtHobit);
		lblBg.add(lblIpAddr);
		lblBg.add(txtIpAddr);
		lblBg.add(lblport);
		lblBg.add(txtPort);
		lblBg.add(lblRemark);
		lblBg.add(taRemark);

		btnSave = new JButton("注册(S)", image1);
		btnSave.setMnemonic('S');
		btnClose = new JButton("退出(C)", image1);
		btnClose.setMnemonic('C');

		btnSave.setBounds(220, 520, 100, 50);
		btnClose.setBounds(360, 520, 100, 50);
		lblBg.add(btnSave);
		lblBg.add(btnClose);
		btnSave.addActionListener(this);
		btnClose.addActionListener(this);
		head3.addActionListener(this);

		setResizable(false);
		setSize(700, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
//	//系统托盘
//	public void jbInit() {
//		if (SystemTray.isSupported()) { // 检查当前系统是否支持系统托盘
//			SystemTray tray = SystemTray.getSystemTray();// 获取表示桌面托盘区的
//															// SystemTray 实例。
//			Image image = Toolkit.getDefaultToolkit().getImage("images/111.png");
//			PopupMenu popupMenu = new PopupMenu();
//			MenuItem exitItem = new MenuItem("关闭");
////			MenuItem menuItema = new MenuItem("最小化");
////			MenuItem menuItemb = new MenuItem("最大化");
//			MenuItem menuItemc = new MenuItem("登录");
//			MenuItem menuItemd = new MenuItem("游戏");
//			exitItem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					try {
//						System.exit(0);
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//			});
//			menuItemc.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					try {
//						new LoginUI();
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//			});
//			menuItemd.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					try {
//						new ShaoRei();
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//			});
////			popupMenu.add(menuItema);
////			popupMenu.add(menuItemb);
//			popupMenu.add(menuItemc);
//			popupMenu.add(menuItemd);
//			popupMenu.add(exitItem);
//			trayIcon = new TrayIcon(image, "系统托盘{kissjava}", popupMenu);
//			trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
//			});
//			try {
//				tray.add(trayIcon); // 将 TrayIcon 添加到 SystemTray。
//			} catch (AWTException e) {
//				System.err.println(e);
//			}
//		} else {
//			System.out.println("你的系统不支持系统托盘");
//		}
//		ImageIcon icon = new ImageIcon();
//		try {
//		} catch (Exception e) {
//		}
//		JLabel label = new JLabel();
//		label.setIcon(icon);
//		this.add(label);
//	}

	public static void main(String[] args) {

		new RegUI();
	}
	//进行合法性的验证
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			String nickname = txtNickName.getText().trim();
			if (nickname.equals("")) {
				JOptionPane.showMessageDialog(this, "亲，请输入昵称");
				return;
			}
			String pass = txtPassword.getText().trim();
			String cfgpass = txtcfgPassword.getText().trim();
			if (pass.equals("")) {
				JOptionPane.showMessageDialog(this, "亲，请输入登录密码");
				return;
			}
			if (!pass.equals(cfgpass)) {
				JOptionPane.showMessageDialog(this, "亲，登录密码与确认密码不一致");
				return;
			}
			int age = 0;
			String sage = txtAge.getText().trim();
			if (sage.equals("")) {
				JOptionPane.showMessageDialog(this, "亲，请输入年龄");
				txtAge.setText("0");
				return;
			}
			try {
				age = Integer.parseInt(sage);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "亲，请输入0~100之间的年龄");
				return;
			}
			if (age < 0 || age > 100) {
				JOptionPane.showMessageDialog(this, "亲，请输入0~100之间的年龄");
				return;

			}
			AccountVo account = new AccountVo();
			account.setNickName(nickname);

			account.setHeadImg(headfase);
			account.setPassword(pass);
			account.setAge(age);
			if (rbMale.isSelected()) {
				account.setSex("男神");
			} else {
				account.setSex("女神");
			}
			account.setNation(sNation[cbNation.getSelectedIndex()]);
			account.setStar(sStar[cbStar.getSelectedIndex()]);
			account.setBlood(sBlood[cbBlood.getSelectedIndex()]);
			account.setHobit(txtHobit.getText().trim());
			account.setIpAddr(txtIpAddr.getText().trim());
			account.setRemark(taRemark.getText().trim());
			BaseDAO baseDAO = new BaseDAO();
			account = baseDAO.saveAccount(account);
			txtQQcode.setText(account.getQqCode() + "");
			txtPort.setText("0");

			JOptionPane.showConfirmDialog(this, "恭喜您,亲，您已成为QQ会员！你的qq号码为"
					+ account.getQqCode());

		} else if (e.getSource() == head3) {
			String str = "head/" + new Random().nextInt(10) + ".jpg";
			head3.setIcon(new ImageIcon(str));
			headfase = str;

		} else if (e.getSource() == btnClose) {
			System.exit(0);
		}

	}

	// 播放背景音乐
	class Sound {

		String path = new String("musics\\");

		// 默认背景音乐
		String file = new String("gqq.mid");

		Sequence seq;

		Sequencer midi;

		// 判断是否在播放音乐
		boolean sign;

		public Sound() {
			loadSound();
		}

		// 播放背景音乐的函数
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
	

	public void mouseClicked(MouseEvent e) {
		
		
	}


	public void mouseEntered(MouseEvent e) {
		
		
	}


	public void mouseExited(MouseEvent e) {
		
		
	}


	public void mousePressed(MouseEvent e) {
		
		
	}


	public void mouseReleased(MouseEvent e) {
		
		
	}
}
