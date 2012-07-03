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
	private EditText name, kronUrl, udpHost, udpPort;
	private Preferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		pref = new Preferences(this);
		save = (Button) findViewById(R.id.go_button);

		kronUrl = (EditText) findViewById(R.id.kron_server_url);
		kronUrl.setText(pref.getKronUrl());

		name = (EditText) findViewById(R.id.name);
		name.setText(pref.getName());

		udpHost = (EditText) findViewById(R.id.udp_host);
		udpHost.setText(pref.getUdpHost());

		udpPort = (EditText) findViewById(R.id.udp_port);
		udpPort.setText(String.valueOf(pref.getUdpPort()));

		save.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				pref.setKronUrl(kronUrl.getText().toString());
				pref.setUdpHost(udpHost.getText().toString());
				pref.setUdpPort(udpPort.getText().toString());
				pref.setName(name.getText().toString());
				Toast.makeText(getApplicationContext(), "saved",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}