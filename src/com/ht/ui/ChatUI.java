package com.ht.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.ht.base.Cmd;
import com.ht.base.SendCmd;
import com.ht.base.SendMsg;
import com.ht.vo.AccountVo;
import com.sun.org.apache.bcel.internal.generic.POP;
/**
 * 聊天页面
 * */
@SuppressWarnings({ "unused", "serial" })
public class ChatUI extends JFrame implements ActionListener,MouseListener,KeyListener{
	//声明标题
	JLabel bg, min, exit, send, close, head, name, hobot;
	JLabel jlhead1, jlhead2, qianming, title,shexiangtou,yinyue;
	JTextPane txtread, txtwrite;
	//文本框
	JTextField tbendi, jduifangarea, tduifang;
	Font fontstyle;
	JLabel cbsize, cbFont,cbShake,cbFile;
	JLabel color;
	JMenuItem sonti, heiti, kaiti, lishu, size1, size2, size3, size4, size5,
	size6, size7, size8;
	JPopupMenu font, size;
	AccountVo myInfo,friendInfo;
	public ChatUI(AccountVo myInfo,AccountVo friendInfo){
		this.myInfo=myInfo;
		this.friendInfo=friendInfo;
		setUndecorated(true);
		bg = new JLabel(new ImageIcon("photo/chat.jpg"));
		// 绝对布局
		bg.setLayout(null);
		add(bg);
		//退出
		exit = new JLabel(" ");
		exit.setToolTipText("退出");
		exit.setBounds(565, 3, 20, 20);
		bg.add(exit);
		//最小化
		min = new JLabel("  ");
		min.setToolTipText("最小化");
		min.setBounds(505, 3, 20, 20);
		bg.add(min);
		//头像
		head = new JLabel(new ImageIcon(friendInfo.getHeadImg()));
		head.setBounds(6, 2, 48, 48);
		bg.add(head);
		//昵称
		name = new JLabel(friendInfo.getNickName());
		name.setBounds(60, 5, 60, 20);
		bg.add(name);
		//聊天栏左上角
		title = new JLabel(myInfo.getNickName() + "和" + friendInfo.getNickName()
				+ "正在聊天");
		title.setBounds(150, 5, 300, 30);
		title.setFont(new Font("楷体", Font.BOLD, 16));
		title.setForeground(Color.RED);
		bg.add(title);

		//签名
		qianming = new JLabel(friendInfo.getHobit());
		qianming.setBounds(60, 25, 200, 30);
		bg.add(qianming);
		//发送
		send = new JLabel("发送(S)");
		send.setToolTipText("发送");
		send.setBounds(298, 478, 60, 20);
		bg.add(send);
		//关闭
		close = new JLabel("关闭(X)");
		close.setToolTipText("关闭");
		close.setBounds(368, 478, 60, 20);
		bg.add(close);
		//聊天栏图片
		jlhead1 = new JLabel(new ImageIcon("photo/04.jpg"));
		jlhead1.setBounds(447, 35, 135, 235);
		bg.add(jlhead1);
		jlhead2 = new JLabel(new ImageIcon("photo/02.jpg"));
		jlhead2.setBounds(447, 270, 135, 235);
		bg.add(jlhead2);

		txtread = new JTextPane();
		JScrollPane js = new JScrollPane(txtread);
		js.setBounds(0, 83, 447, 290);
		js.setBorder(null);
		txtread.setEnabled(false);
		bg.add(js);

		txtwrite = new JTextPane();
		JScrollPane jss = new JScrollPane(txtwrite);
		jss.setBounds(0, 393, 447, 80);
		jss.setBorder(null);
		bg.add(jss);
		//字体大小
		cbsize = new JLabel("  ");
		cbsize.setToolTipText("设置字体");
		cbsize.setBounds(10, 370, 20, 20);
		bg.add(cbsize);
		//字体类型
		cbFont = new JLabel("  ");
		cbFont.setToolTipText("字体类型");
		cbFont.setBounds(35, 370, 30, 30);
		bg.add(cbFont);
		//字体颜色
		color = new JLabel("  ");
		color.setToolTipText("字体颜色");
		color.setBounds(68, 370, 20, 30);
		bg.add(color);
		//振动
		cbShake = new JLabel("  ");
		cbShake.setToolTipText("抖动");
		cbShake.setBounds(90,370,30,30);
		bg.add(cbShake);
		//发送文件
		cbFile = new JLabel("  ");
		cbFile.setToolTipText("发送文件");
		cbFile.setBounds(180,370,30,30);
		bg.add(cbFile);
		
		//视频聊天
		shexiangtou = new JLabel("  ");
		shexiangtou.setToolTipText("视频聊天");
		shexiangtou.setBounds(45,48,30,30);
		bg.add(shexiangtou);
		
		//音乐
		yinyue = new JLabel("  ");
		yinyue.setToolTipText("播放音乐");
		yinyue.setBounds(220,370,30,30);
		bg.add(yinyue);
		//字体类型
		size = new JPopupMenu();
		sonti = new JMenuItem("宋体");
		heiti = new JMenuItem("黑体");
		kaiti = new JMenuItem("楷体");
		lishu = new JMenuItem("隶书");
		size.add(sonti);
		size.add(heiti);
		size.add(kaiti);
		size.add(lishu);
		//监听事件
		sonti.addActionListener(this);
		heiti.addActionListener(this);
		kaiti.addActionListener(this);
		lishu.addActionListener(this);

		font = new JPopupMenu();
		size1 = new JMenuItem("8");
		size2 = new JMenuItem("14");
		size3 = new JMenuItem("18");
		size4 = new JMenuItem("20");
		size5 = new JMenuItem("24");
		size6 = new JMenuItem("28");
		size7 = new JMenuItem("32");
		size8 = new JMenuItem("40");
		font.add(size1);
		font.add(size2);
		font.add(size3);
		font.add(size4);
		font.add(size5);
		font.add(size6);
		font.add(size7);
		font.add(size8);

		size1.addActionListener(this);
		size2.addActionListener(this);
		size3.addActionListener(this);
		size4.addActionListener(this);
		size5.addActionListener(this);
		size6.addActionListener(this);
		size7.addActionListener(this);
		size8.addActionListener(this);

		exit.addMouseListener(this);
		send.addMouseListener(this);
		close.addMouseListener(this);
		min.addMouseListener(this);
		cbsize.addMouseListener(this);
		cbFont.addMouseListener(this);
		color.addMouseListener(this);
		cbShake.addMouseListener(this);
		cbFile.addMouseListener(this);
		shexiangtou.addMouseListener(this);
		yinyue.addMouseListener(this);
		txtwrite.addKeyListener(this);
		setDragable();
		setSize(582, 507);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	//把发送框的内容提交到接收框，同时清除发送框的内容
	public  void appendView(String name, StyledDocument xx)throws BadLocationException {
		//获取接收框的文档（内容）
		StyledDocument vdoc = txtread.getStyledDocument();//获取接收框的内容
		
		// 格式化时间
		Date date = new Date();//获取系统当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //设置显示时分秒的格式
		String time = sdf.format(date);//获取时分秒
		//创建一个属性集合
		SimpleAttributeSet as = new SimpleAttributeSet();
		// 显示谁说
		//vdoc.getLength()获取接收框中的原来内容的长度
		String s =name + "    " + time + "\n";
//		saveRecord(name,s);//保存聊天记录
		vdoc.insertString(vdoc.getLength(), s, as);
		int end = 0;
		//处理显示的内容
		while (end < xx.getLength()) {
			// 获得一个元素
			Element e0 = xx.getCharacterElement(end);
			//获取对应的风格，
			SimpleAttributeSet as1 = new SimpleAttributeSet();
			StyleConstants.setForeground(as1, StyleConstants.getForeground(e0.getAttributes()));
			StyleConstants.setFontSize(as1, StyleConstants.getFontSize(e0.getAttributes()));
			StyleConstants.setFontFamily(as1, StyleConstants.getFontFamily(e0.getAttributes()));
			//获取该元素的内容
			s = e0.getDocument().getText(end, e0.getEndOffset() - end);
			// 将元素加到浏览窗中
			if("icon".equals(e0.getName())){
				vdoc.insertString(vdoc.getLength(), s, e0.getAttributes());
			}
			else{
				vdoc.insertString(vdoc.getLength(), s, as1);
//				saveRecord(name,s+"\n");//保存聊天记录
			}
			//getEndOffset（）函数就是获取当前元素的长度
			end = e0.getEndOffset(); 
		}
		// 输入一个换行
		vdoc.insertString(vdoc.getLength(), "\n", as);
		
		// 设置显示视图加字符的位置与文档结尾，以便视图滚动
		txtread.setCaretPosition(vdoc.getLength());
	}
	//设置界面可以拖动的方法
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				ChatUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());

				isDragged = true;
				ChatUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(ChatUI.this.getLocation().x + e.getX() - tmp.x,
							ChatUI.this.getLocation().y + e.getY() - tmp.y);
					ChatUI.this.setLocation(loc);
				}
			}
		});
	}
	public static void main(String[] args) {
		
	}
	//组件事件
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sonti) {//字体
			fontstyle = new Font("宋体", Font.BOLD, 20);
			txtwrite.setFont(fontstyle);
		} else if (e.getSource() == heiti) {
			fontstyle = new Font("黑体", Font.BOLD, 14);
			txtwrite.setFont(fontstyle);
		} else if (e.getSource() == kaiti) {
			fontstyle = new Font("楷体", Font.BOLD, 14);
			txtwrite.setFont(fontstyle);
		} else if (e.getSource() == lishu) {
			fontstyle = new Font("隶书", Font.BOLD, 14);
			txtwrite.setFont(fontstyle);
		} else if (e.getSource() == size1) {

		}
		
	}
	//鼠标事件
	@SuppressWarnings({ "static-access", "deprecation", "resource" })
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==exit) {//退出
			dispose();
		}else if (e.getSource()==send) {//发送
			if(txtwrite.getText().equals("")){
				JOptionPane.showMessageDialog(this, "请输入你要发送的内容。");
				return;
			}
			try {
				appendView(myInfo.getNickName(), txtwrite.getStyledDocument());
				SendMsg msg= new SendMsg();
				msg.cmd = Cmd.CMD_SEND;
				msg.myInfo = myInfo;
				msg.friendInfo = friendInfo;
				msg.doc = txtwrite.getStyledDocument();
				SendCmd.send(msg);
				txtwrite.setText("");
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		
		}else if (e.getSource()==close) {//关闭
			System.exit(0);
		}else if(e.getSource()==min){//最小化
			setState(JFrame.HIDE_ON_CLOSE);
		}else if (e.getSource()==cbsize) {
			if (e.getButton()==1) {//左键
				size.show(this, 35, 370);
			}else if (e.getButton()==3) {//右键
				font.show(this, 35, 370);
			}
		}else if (e.getSource()==color) {//颜色
				JColorChooser colordlg = new JColorChooser();
				// 打开颜色对话框，并且获取选中的颜色
				Color color=colordlg.showDialog(this, "请选择字体颜色", Color.BLACK);
				// 设置发送文本框的字体颜色
				txtwrite.setForeground(color);
			
		}else if (e.getSource()==cbFont) {//发送表情
			//打开表情窗口，选择表情图标
			int x,y;
			x=this.getLocation().x+220;
			y=this.getLocation().y-28;
			new BqUI(this,x,y);
		}else if(e.getSource()==cbShake){//振动
			//发送消息
			SendMsg msg = new SendMsg();
			msg.cmd = Cmd.CMD_SHAKE;
			msg.myInfo = myInfo;
			msg.friendInfo = friendInfo;
			SendCmd.send(msg);
			shake();
		}else if(e.getSource()==cbFile){//发送文件
			FileDialog dlg = new FileDialog(this,"请选择要发送的文件(64K以下)",FileDialog.LOAD);
			dlg.show();
			String filename = dlg.getDirectory() + dlg.getFile();
			try {
				FileInputStream fis = new FileInputStream(filename);
				int size = fis.available();
				byte b[] = new byte[size];
				fis.read(b);
				SendMsg msg = new SendMsg();
				msg.cmd = Cmd.CMD_FILE;
				msg.myInfo=myInfo;
				msg.friendInfo = friendInfo;
				msg.b = b;
				msg.fileName = dlg.getFile();
				SendCmd.send(msg);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(filename);
		}else if(e.getSource()==shexiangtou){
			//摄像头
			new Openweb("360魔法摄像头.lnk");
		}else if(e.getSource()==yinyue){
			//音乐
			new Openweb("酷狗音乐.lnk");
		}
	}
	//抖动窗口
	public void shake(){
		//抖动窗口
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		for(int i=0;i<20;i++){
			if(i%2==0){
				this.setLocation(x+2, y+2);
			}else{
				this.setLocation(x-2, y-2);
			}
			try {
				Thread.sleep(50);//休眠事件
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
	//定义线程
	class thread extends Thread {

		DatagramSocket ds;

		public thread() {
			// 得到端口值
			int port = Integer.parseInt(tbendi.getText().trim());
			try {
				ds = new DatagramSocket(port);
			} catch (SocketException e) {

				e.printStackTrace();
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			while (true) {
				try {
					byte b[] = new byte[1024];
					DatagramPacket dp = new DatagramPacket(b, b.length);
					ds.receive(dp);
					// 读取字符，从0开始读取，读到数据报的结束
					String str = new String(b, 0, dp.getLength());
					String s = txtread.getText();
					s += new Date().toLocaleString() + "\n";
					s += str + "\n";
					// 显示在显示框中
					txtread.setText(s);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//按回车键发送消息
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10){
			if(txtwrite.getText().equals("")){
				JOptionPane.showMessageDialog(this, "请输入你要发送的内容。");
				return;
			}
			try {
				appendView(myInfo.getNickName(), txtwrite.getStyledDocument());
				SendMsg msg= new SendMsg();
				msg.cmd = Cmd.CMD_SEND;
				msg.myInfo = myInfo;
				msg.friendInfo = friendInfo;
				msg.doc = txtwrite.getStyledDocument();
				SendCmd.send(msg);
				txtwrite.setText("");
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}

}
