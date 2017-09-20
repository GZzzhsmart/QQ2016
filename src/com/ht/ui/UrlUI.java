package com.ht.ui;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlUI {
	public static void main(String[] args) {
		try{
			URL url = new URL("http://www.weather.com.cn/weather/101240701.shtml");
			URLConnection conn = url.openConnection();
			//获取输入流
			InputStream is = conn.getInputStream();
			BufferedInputStream fis = new BufferedInputStream(is);
			FileOutputStream fos = new FileOutputStream("d:\\test5.html");
			byte b[]= new byte[2048];
			int len=0;
			while((len=fis.read(b))>0){
				String str = new String(b,0,len,"utf-8");
				System.out.println(str);
				fos.write(b,0,len);
			}
			fis.close();
			fos.close();
			System.out.println("下载成功!");
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
