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
	
	public void setRealTimeUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("realTimeUrl", url);
		editor.commit();	
	}

	public String getKronUrl() {
		return preferences.getString("kronUrl", "http://192.168.1.101:3000");
	}
	
	public String getRealtimeUrl() {
		return preferences.getString("realTimeUrl", "http://192.168.1.101:3001");
	}

	public InetAddress getUDPAddress() {
		String host = "192.168.1.100";

		InetAddress address = null;
		try {
			address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return address;
	}

	public int getUDPPort() {
		return 3002;
	}
}