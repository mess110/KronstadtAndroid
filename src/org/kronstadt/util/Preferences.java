package org.kronstadt.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	private SharedPreferences preferences;

	public Preferences(Context context) {
		preferences = context.getSharedPreferences("org.kronstadt.prefs", 0);
	}

	public void setKronUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("kronUrl", url);
		editor.commit();
	}

	public void setName(String name) {
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.commit();
	}

	public void setUdpHost(String host) {
		Editor editor = preferences.edit();
		editor.putString("udpHost", host);
		editor.commit();
	}

	public void setUdpPort(String port) {
		Editor editor = preferences.edit();
		editor.putInt("udpPort", Integer.valueOf(port));
		editor.commit();
	}

	public String getKronUrl() {
		return preferences.getString("kronUrl", "http://192.168.1.101:3000");
	}

	public int getUdpPort() {
		return preferences.getInt("udpPort", 3002);
	}

	public String getName() {
		return preferences.getString("name", "name");
	}

	public String getUdpHost() {
		return preferences.getString("udpHost", "192.168.1.101");
	}

	public InetAddress getUDPAddress() {
		String host = getUdpHost();

		InetAddress address = null;
		try {
			address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return address;
	}
}