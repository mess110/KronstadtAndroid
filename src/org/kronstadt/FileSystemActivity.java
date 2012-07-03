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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileSystemActivity extends ListActivity {

	private HTTPClient http;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		http = new HTTPClient(getApplicationContext());

		loadList("/");
	}

	public void loadList(String path) {
		loadList(path, false);
	}

	public void loadBookmarks() {
		loadList("/", true);
	}

	// this is incredibly ugly
	// fix it
	// need a URL joining class
	private void loadList(String path, boolean bookmarks) {
		ArrayList<String> files = new ArrayList<String>();
		if (!bookmarks) {
			files.add("bookmarks");
			files.add("..");
		} else {

		}

		String jsonString = http.ls(path, bookmarks);
		try {
			JSONObject response = new JSONObject(jsonString);
			JSONArray fileNames = response.getJSONArray("file_names");
			for (int i = 0; i < fileNames.length(); i++) {
				files.add((String) fileNames.get(i));
			}
			files.add(0, response.getString("pwd"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		final String pwd = files.remove(0);
		if (pwd.equals("/")) {
			files.remove(1);
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
				if (position == 0 && s.equals("bookmarks")) {
					loadBookmarks();
					return;
				}

				if (position == 1 && s.equals("..")) {
					String prevDir = FileUtil.prevDir(pwd);
					loadList(prevDir);
					return;
				}
				loadList(FileUtil.join(pwd, s));
			}
		});
	}
}