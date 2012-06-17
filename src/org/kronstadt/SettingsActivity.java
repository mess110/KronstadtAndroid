package org.kronstadt;

import org.kronstadt.util.Preferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private Button save;
	private EditText kronUrl, realTimeUrl;
	private Preferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		pref = new Preferences(this);
		save = (Button) findViewById(R.id.go_button);

		kronUrl = (EditText) findViewById(R.id.editText1);
		kronUrl.setText(pref.getKronUrl());

		realTimeUrl = (EditText) findViewById(R.id.editText2);
		realTimeUrl.setText(pref.getRealtimeUrl());

		save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				pref.setKronUrl(kronUrl.getText().toString());
				pref.setRealTimeUrl(realTimeUrl.getText().toString());
				Toast.makeText(getApplicationContext(), "saved",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}