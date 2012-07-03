package org.kronstadt.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.kronstadt.util.Preferences;

import android.content.Context;

public class UDPClient {

	private Preferences pref;
	private DatagramSocket dsocket;

	public UDPClient(Context context) {
		pref = new Preferences(context);
	}

	public void connect() {
		try {
			dsocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void move(float x, float y) {
		send("0" + ":" + x + ":" + y + "!");
	}

	public void click() {
		send("1" + ":0:0!");
	}

	public void middleClick() {
		send("2" + ":0:0!");
	}

	public void rightClick() {
		send("3" + ":0:0!");
	}

	public void doubleClick() {
		send("4" + ":0:0!");
	}

	private void send(String s) {
		byte[] msg = s.getBytes();

		DatagramPacket packet = new DatagramPacket(msg, msg.length,
				pref.getUDPAddress(), pref.getUdpPort());

		try {
			dsocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
