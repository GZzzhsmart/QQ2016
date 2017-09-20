package com.ht.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
  

public class Openweb {  
	  
	//只用来打开本地的html
	public Openweb(String URL){
		String url ="file:///" + new File(URL).getAbsolutePath().replace("\\","/");		
		try {  
			URI uri = new URI(url);  
			Desktop desktop = null;  
			if (Desktop.isDesktopSupported()) {  
				desktop = Desktop.getDesktop();  
			}  
			if (desktop != null)  
				desktop.browse(uri);  
		} catch (IOException ioe) {  
			ioe.printStackTrace();  
		} catch (URISyntaxException e) {  
			e.printStackTrace();  
		}  
	}
	//只用来打开网页
	public Openweb(String net,String URL){
		try {  
			URI uri = new URI(URL);  
			Desktop desktop = null;  
			if (Desktop.isDesktopSupported()) {  
				desktop = Desktop.getDesktop();  
			}  
			if (desktop != null)  
				desktop.browse(uri);  
		} catch (IOException ioe) {  
			ioe.printStackTrace();  
		} catch (URISyntaxException e) {  
			e.printStackTrace();  
		}  
	}
	public static void main(String[] args) {
		new Openweb("test.html");
		new Openweb("test1.html");
		new Openweb("test2.html");
		new Openweb("test3.html");
		new Openweb("test4.html");
		new Openweb("test5.html");
		new Openweb("360魔法摄像头.lnk");
		new Openweb("酷狗音乐.lnk");
		new Openweb("test6.html");
		new Openweb ("QQ聊天项目.pptx");
		
	}
}   

