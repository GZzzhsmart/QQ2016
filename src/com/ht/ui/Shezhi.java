package com.ht.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 登录页面的设置窗口
 * 
 * */
@SuppressWarnings({ "unused", "serial" })
public class Shezhi extends JFrame implements ActionListener,MouseListener{
	JLabel bg;
	//声明标题
	JLabel lblmin,lblclose;
	JLabel lbltxtle,lbltxtle1,lblleixing1,lblleixing2,lbldizhi1,lbldizhi2,lblport1,lblport2,lblname,lblpass,lblyu;
	//文本框
	JTextField txtdizhi1,txtport1,txtport2,txtname,txtpass,txtyu,txtcs;
	//设置下拉框
	@SuppressWarnings("rawtypes")
	JComboBox cbleixing1,cbleixing2,cbdizhi;
	//设置按钮
	JButton btnok,btnclose;
	String sShezhi[ ]={
			"不使用代理",
			"HTTP代理",
			"SOCKSS代理",
			"使用浏览器设置"
	};
	String sShezhi1[ ]={
			"不使用高级选项",
			"UDP类型",
			"TCP类型"
	};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Shezhi(){
		
		super("网络设置");
		//是否隐藏边框
		setDragable();
		setUndecorated(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("images/qt.png");
		setIconImage(image);
		bg = new JLabel(new ImageIcon("images/20.jpg"));
		add(bg);
		bg.setLayout(null);
		
		lbltxtle = new JLabel("网络设置");
		lbltxtle.setFont(new Font("黑体",Font.BOLD,15));
		lbltxtle.setBounds(10, 15, 80, 20);
		bg.add(lbltxtle);
		
		
		
		lblmin = new JLabel("-");
		lblmin.setToolTipText("最小化");
		lblmin.setForeground(Color.WHITE);
		lblmin.setFont(new Font("黑体",Font.BOLD,20));
		lblclose = new JLabel("×");
		lblclose.setToolTipText("关闭");
		lblclose.setForeground(Color.WHITE);
		lblclose.setFont(new Font("黑体",Font.BOLD,18));
		lblmin.setBounds(420, 7, 20, 20);
		lblclose.setBounds(445, 4, 20, 20);
		
		lblmin.addMouseListener(this);
		lblclose.addMouseListener(this);
		bg.add(lblmin);
		bg.add(lblclose);
		
		lblleixing1 = new JLabel("类型:");
		lblleixing1.setForeground(Color.CYAN);
		lblleixing1.setFont(new Font("黑体",Font.BOLD,13));
		lblleixing1.setBounds(30,63,80,20);
		bg.add(lblleixing1);
		
		cbleixing1 = new JComboBox(sShezhi);
		cbleixing1.setBounds(70,60,140,25);
		bg.add(cbleixing1);
	
		lbldizhi1 = new JLabel("地址:");
		lbldizhi1.setForeground(Color.CYAN);
		lbldizhi1.setFont(new Font("黑体",Font.BOLD,13));
		lbldizhi1.setBounds(220,63,80,20);
		bg.add(lbldizhi1);
		
		
		txtdizhi1 = new JTextField();
		txtdizhi1.setBounds(260,60,80,25);
		txtdizhi1.setEditable(false);
		bg.add(txtdizhi1);
		
		lblport1 = new JLabel("端口:");
		lblport1.setForeground(Color.CYAN);
		lblport1.setFont(new Font("黑体",Font.BOLD,13));
		lblport1.setBounds(350,63,80,20);
		bg.add(lblport1);
		
		txtport1 = new JTextField();
		txtport1.setBounds(390,60,50,25);
		txtport1.setEditable(false);
		bg.add(txtport1);
		
		lblname = new JLabel("用户名:");
		lblname.setForeground(Color.CYAN);
		lblname.setFont(new Font("黑体",Font.BOLD,13));
		lblname.setBounds(15,105,80,20);
		bg.add(lblname);
		
		txtname = new JTextField();
		txtname.setBounds(70,103,140,25);
		txtname.setEditable(false);
		bg.add(txtname);
		
		lblpass = new JLabel("密码:");
		lblpass.setForeground(Color.CYAN);
		lblpass.setFont(new Font("黑体",Font.BOLD,13));
		lblpass.setBounds(220,105,80,20);
		bg.add(lblpass);
		
		txtpass = new JTextField();
		txtpass.setBounds(260,103,80,25);
		txtpass.setEditable(false);
		bg.add(txtpass);
		
		lblyu = new JLabel("域:");
		lblyu.setForeground(Color.CYAN);
		lblyu.setFont(new Font("黑体",Font.BOLD,13));
		lblyu.setBounds(365,105,80,20);
		bg.add(lblyu);
		
		 txtyu = new JTextField();
		 txtyu.setBounds(390,103,50,25);
		 txtyu.setEditable(false);
		 bg.add(txtyu);
		 
		 txtcs = new JTextField();
		 txtcs.setForeground(Color.RED);
		 txtcs.setText("    测试   ");
		 txtcs.setBounds(390,143,50,25);
		 txtcs.setEditable(false);
		 bg.add(txtcs);
		 
		 
		 lbltxtle1 = new JLabel("登录服务器");
		 lbltxtle1.setFont(new Font("黑体",Font.BOLD,15));
		 lbltxtle1.setBounds(10, 173, 80, 20);
		bg.add(lbltxtle1);
		
		
		lblleixing1 = new JLabel("类型:");
		lblleixing1.setForeground(Color.CYAN);
		lblleixing1.setFont(new Font("黑体",Font.BOLD,13));
		lblleixing1.setBounds(30,225,80,20);
		bg.add(lblleixing1);
		
		cbleixing1 = new JComboBox(sShezhi1);
		cbleixing1.setBounds(70,223,140,25);
		bg.add(cbleixing1);
	
		lbldizhi2 = new JLabel("地址:");
		lbldizhi2.setForeground(Color.CYAN);
		lbldizhi2.setFont(new Font("黑体",Font.BOLD,13));
		lbldizhi2.setBounds(220,225,80,20);
		bg.add(lbldizhi2);
		
		
		cbdizhi = new JComboBox();
		cbdizhi.setBounds(260,223,80,25);
		cbdizhi.setEditable(false);
		bg.add(cbdizhi);
		
		lblport2 = new JLabel("端口:");
		lblport2.setForeground(Color.CYAN);
		lblport2.setFont(new Font("黑体",Font.BOLD,13));
		lblport2.setBounds(350,225,80,20);
		bg.add(lblport2);
		
		txtport2 = new JTextField();
		txtport2.setBounds(390,223,50,25);
		txtport2.setEditable(false);
		bg.add(txtport2);
		
		btnok = new JButton("确定");
		btnok.setBounds(290,290,80,25);
		bg.add(btnok);
		
		btnclose = new JButton("取消");
		btnclose.setBounds(375,290,80,25 );
		bg.add(btnclose);
		add(bg);
		btnok.addActionListener(this);
		btnclose.addActionListener(this);
		setSize(480,370);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Shezhi();
	}
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==btnok) {
			dispose();
		}if (e.getSource()==btnclose) {
			dispose();
			
		}
	}
	//移动界面
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private void setDragable() {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				Shezhi.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());

				isDragged = true;
				Shezhi.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(Shezhi.this.getLocation().x + e.getX() - tmp.x,
							Shezhi.this.getLocation().y + e.getY() - tmp.y);
					Shezhi.this.setLocation(loc);
				}
			}
		});
	}
	//鼠标事件
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lblmin){
			this.setState(JFrame.HIDE_ON_CLOSE);
		}else if(e.getSource()==lblclose){
			System.exit(0);
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

	
}
