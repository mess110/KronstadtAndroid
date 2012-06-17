package org.kronstadt.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Util {

	public static void log(float x, float y) {
		log(String.valueOf(x) + " - " + String.valueOf(y));
	}

	public static void log(String s) {
		Log.d("Kronstadt", s);
	}

	public static JSONObject json(float x, float y) {
		JSONObject result = null;
		try {
			result = new JSONObject().put("x", x).put("y", y);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
