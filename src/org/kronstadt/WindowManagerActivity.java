package org.kronstadt;

import org.kronstadt.network.HTTPClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class WindowManagerActivity extends Activity {
	private Button fullscreen, focus, close;
	private Spinner windows;
	private HTTPClient http;

	String[] DayOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window_manager);

		http = new HTTPClient(getBaseContext());

		windows = (Spinner) findViewById(R.id.spinner1);
		// add spinner items

		fullscreen = (Button) findViewById(R.id.full_screen);
		fullscreen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});

		focus = (Button) findViewById(R.id.focus);
		focus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});

		close = (Button) findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
