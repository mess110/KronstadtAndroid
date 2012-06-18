package org.kronstadt;

import org.kronstadt.util.BaseActivity;
import org.kronstadt.util.Preferences;
import org.kronstadt.util.Util;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KronstadtAndroidActivity extends BaseActivity {
	private WebView webView;
	private Preferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		pref = new Preferences(this);
		webView = (WebView) findViewById(R.id.webview);

		WebSettings webSettings = webView.getSettings();
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);

		// workaround so that the default browser doesn't take over
		webView.setWebViewClient(new MyWebViewClient());

		refresh();
	}

	public void refresh() {
		webView.loadUrl(pref.getKronUrl());
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.endsWith("cezara")) {
				showMouseInputActivity();
			}
			Util.log(url);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Util.log("back button pressed");
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}