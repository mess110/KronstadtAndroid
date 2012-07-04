package org.kronstadt.util;

import org.kronstadt.FileSystemActivity;
import org.kronstadt.MouseInputActivity;
import org.kronstadt.SettingsActivity;
import org.kronstadt.TriviaActivity;
import org.kronstadt.WindowManagerActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	private final int ID_MENU_EXIT = 0;
	private final int ID_MENU_REFRESH = 1;
	private final int ID_MENU_SETTINGS = 2;
	private final int ID_MENU_FILE_SYSTEM = 3;
	private final int ID_MENU_MOUSE_INPUT = 4;
	private final int ID_MENU_TRIVIA = 5;
	private final int ID_MENU_WINDOW_MANAGER = 6;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_WINDOW_MANAGER, Menu.NONE, "window manager");
		menu.add(Menu.NONE, ID_MENU_TRIVIA, Menu.NONE, "trivia");
		menu.add(Menu.NONE, ID_MENU_MOUSE_INPUT, Menu.NONE, "mouse");
		menu.add(Menu.NONE, ID_MENU_FILE_SYSTEM, Menu.NONE, "file system");
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
			startActivity(SettingsActivity.class);
			break;
		case ID_MENU_FILE_SYSTEM:
			startActivity(FileSystemActivity.class);
			break;
		case ID_MENU_MOUSE_INPUT:
			startActivity(MouseInputActivity.class);
			break;
		case ID_MENU_TRIVIA:
			startActivity(TriviaActivity.class);
			break;
		case ID_MENU_WINDOW_MANAGER:
			startActivity(WindowManagerActivity.class);
			break;
		default:
			break;
		}
		return false;
	}

	public void refresh() {
		// this is overwritten
	}

	public void startActivity(Class<?> klass) {
		Intent myIntent = new Intent(getBaseContext(), klass);
		startActivity(myIntent);
	}
}