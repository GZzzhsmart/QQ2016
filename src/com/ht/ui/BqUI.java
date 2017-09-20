package com.ht.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BqUI extends JFrame implements MouseListener{
	
	JLabel [] bqicon;
	String iconlist[];
//	String imageName;
	ChatUI chat;
	public BqUI(ChatUI chat,int x,int y) {
		this.chat = chat;
		setUndecorated(true);
		setResizable(false);
		//设置窗口在最前面
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("head/touxiang.png");
		setIconImage(img);
		setAlwaysOnTop(true);
		Container con = getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.setBackground(Color.WHITE);
		File file = new File("bq");
		//获取bq文件夹的所有文件的名称
		iconlist = file.list();
		bqicon = new JLabel[iconlist.length];
		for(int i=0;i<iconlist.length;i++){
			//每个JLabel控件显示一个标签图标
			bqicon[i]= new JLabel(new ImageIcon("bq/"+iconlist[i]));
			//设置白色的边框颜色，边框为2个像素的宽度
			bqicon[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
			//为每一个JLable控件添加鼠标事件
			bqicon[i].addMouseListener(this);
			add(bqicon[i]);
		}
		
		setSize(300, 320);
		setVisible(true);
//		setLocationRelativeTo(null);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
//		new BqUI(null);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			for(int i=0;i<iconlist.length;i++){
				if(e.getSource()==bqicon[i]){
					//把选中的表情显示到聊天窗口的发送文本框中
					//insertIcon函数的作用：把图标插入到文本框中
					chat.txtwrite.insertIcon(bqicon[i].getIcon());
					dispose();
					break;
				}
			}

		}

		
	}
	//鼠标移动到控件区域的上方时会触发该事件
	public void mouseEntered(MouseEvent e) {
		
		for(int i=0;i<iconlist.length;i++){
			if(e.getSource()==bqicon[i]){
				//如果鼠标在该JLabel的上面，就改变该JLabel的边框颜色为蓝色
				bqicon[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
				break;
			}
		}
	}
	//鼠标离开该控件区域的上方时会触发该事件
	public void mouseExited(MouseEvent e) {
		for(int i=0;i<iconlist.length;i++){
			if(e.getSource()==bqicon[i]){
				//如果鼠标离开该JLabel时，就改变该JLabel的边框颜色为背景颜色(白色)
				bqicon[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
				break;
			}
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
}
