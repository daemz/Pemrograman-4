package com.example.listview;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private static String link_url = "http://jsonplaceholder.typicode.com/posts";

	private static final String AR_ID = "id";
	private static final String AR_TITLE = "title";
	private static final String AR_BODY = "body";

	JSONArray artikel = null;
	ArrayList<HashMap<String, String>> daftar_artikel = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		JSONParser jParser = new JSONParser();

		JSONObject json = jParser.AmbilJson(link_url);

		try {
			artikel = json.getJSONArray("artikel");

			for(int i = 0; i < artikel.length(); i++){
				JSONObject ar = artikel.getJSONObject(i);

				String id = ar.getString(AR_ID);
				String judul = ar.getString(AR_TITLE);
				String content = ar.getString(AR_BODY).substring(0,100)+"...(baca selengkapnya)";

				HashMap<String, String> map = new HashMap<String, String>();

				map.put(AR_ID, id);
				map.put(AR_TITLE, judul);
				map.put(AR_BODY, content);

				daftar_artikel.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.adapter_listview();
	}

	public void adapter_listview() {

		ListAdapter adapter = new SimpleAdapter(this, daftar_artikel,
				R.layout.list_item,
				new String[] { AR_ID, AR_TITLE, AR_BODY}, new int[] {
						R.id.id, R.id.title, R.id.body});

		
	}
    
}
