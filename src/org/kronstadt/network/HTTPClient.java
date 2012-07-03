package org.kronstadt.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.kronstadt.model.Answer;
import org.kronstadt.util.Preferences;

import android.content.Context;

public class HTTPClient {

	private Preferences pref;

	public HTTPClient(Context context) {
		pref = new Preferences(context);
	}

	public String getQuestion() {
		String result = "";
		String url = pref.getKronUrl() + "/json/trivia";
		try {
			result = executeHttpGet(url);
		} catch (Exception e) {

		}
		return result;
	}

	public void finishQuestion() {
		String url = pref.getKronUrl() + "/json/trivia/finish";
		try {
			executeHttpGet(url);
		} catch (Exception e) {

		}
	}

	public void answer(Answer answer) {
		String url = pref.getKronUrl() + "/json/trivia/" + pref.getName() + "/"
				+ answer.id;
		try {
			executeHttpGet(url);
		} catch (Exception e) {

		}
	}
	

	public void startJRobotServer() {
		String url = pref.getKronUrl() + "/json/mouse_input/";
		try {
			executeHttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String executeHttpGet(String url) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			return sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
