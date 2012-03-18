package org.kronstadt;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	private SharedPreferences preferences;

	public Preferences(Context context) {
		preferences = context.getSharedPreferences("org.kronstadt.preferences",
				0);
	}

	public void setUrl(String url) {
		Editor editor = preferences.edit();
		editor.putString("url", url);
		editor.commit();
	}

	public String getUrl() {
		return preferences.getString("url", "http://192.168.1.101:3000");
	}
}