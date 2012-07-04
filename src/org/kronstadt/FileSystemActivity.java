package org.kronstadt;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kronstadt.network.HTTPClient;
import org.kronstadt.util.FileUtil;
import org.kronstadt.util.Util;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileSystemActivity extends ListActivity {
	private final int ID_MENU_EXIT = 0;
	private final int ID_MENU_BOOKMARKS = 1;

	private HTTPClient http;
	private String pwd;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_BOOKMARKS, Menu.NONE, "bookmarks");
		menu.add(Menu.NONE, ID_MENU_EXIT, Menu.NONE, "exit");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ID_MENU_EXIT:
			this.finish();
			break;
		case ID_MENU_BOOKMARKS:
			loadBookmarks();
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		http = new HTTPClient(getApplicationContext());
		pwd = "/";

		loadList(pwd);
	}

	public void loadList(String path) {
		loadList(path, false);
	}

	public void loadBookmarks() {
		loadList(pwd, true);
	}

	private void loadList(String path, boolean bookmarks) {
		ArrayList<String> files = new ArrayList<String>();

		String jsonString = http.ls(path, bookmarks);
		try {
			JSONObject response = new JSONObject(jsonString);
			JSONArray fileNames = response.getJSONArray("file_names");
			for (int i = 0; i < fileNames.length(); i++) {
				files.add((String) fileNames.get(i));
			}
			pwd = response.getString("pwd");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Util.log(pwd);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.file_system,
				files));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String s = (String) (((TextView) view).getText());
				loadList(FileUtil.join(pwd, s));
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (!pwd.equals("/")) {
				String prevDir = FileUtil.prevDir(pwd);
				loadList(prevDir);
				return false;
			} else {
				return super.onKeyDown(keyCode, event);
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}