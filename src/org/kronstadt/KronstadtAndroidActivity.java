package org.kronstadt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KronstadtAndroidActivity extends Activity {
	private WebView webView;
	private Preferences pref;

	private final int ID_MENU_EXIT = 3;
	private final int ID_MENU_REFRESH = 2;
	private final int ID_MENU_SETTINGS = 1;

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

		loadUrl();
	}

	private void loadUrl() {
		webView.loadUrl(pref.getUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_SETTINGS, Menu.NONE, "settings");
		menu.add(Menu.NONE, ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ID_MENU_EXIT:
			this.finish();
			break;
		case ID_MENU_REFRESH:
			loadUrl();
			break;
		case ID_MENU_SETTINGS:
			Intent myIntent = new Intent(this, SettingsActivity.class);
			startActivity(myIntent);
			break;
		default:
			break;
		}
		return false;
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}