package org.kronstadt.util;

import org.kronstadt.MouseInputActivity;
import org.kronstadt.SettingsActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	private final int ID_MENU_EXIT = 0;
	private final int ID_MENU_REFRESH = 1;
	private final int ID_MENU_SETTINGS = 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_REFRESH, Menu.NONE, "refresh");
		menu.add(Menu.NONE, ID_MENU_SETTINGS, Menu.NONE, "settings");
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
			refresh();
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

	public void refresh() {
		// this is overwritten
	}

	public void showMouseInputActivity() {
		Intent myIntent = new Intent(getBaseContext(), MouseInputActivity.class);
		startActivity(myIntent);
	}

}