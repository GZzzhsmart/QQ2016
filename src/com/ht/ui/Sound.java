package com.ht.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.ht.base.Cmd;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

@SuppressWarnings("unused")
public class Sound {

	@SuppressWarnings("resource")
	public Sound(int cmd) {
		try {
			String filename = "sounds/sx.wav";
			switch(cmd){
			case Cmd.CMD_ADDFRI://添加好友
				filename="sounds/system.wav";
				break;
			case Cmd.CMD_SHAKE://抖动
				filename = "sounds/tweet.wav";
				break;
			case Cmd.CMD_SEND://发送消息
				filename = "sounds/QQ信息声.wav";
				break;
			}
			File file = new File(filename);
			InputStream is = new FileInputStream(file);
			int size = is.available();
			byte b[] = new byte[size];
			is.read(b, 0, size);
			AudioData ad = new AudioData(b);
			AudioDataStream ds = new AudioDataStream(ad);
			AudioPlayer.player.start(ds);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sound(Cmd.CMD_ARGEE);
	}
}
