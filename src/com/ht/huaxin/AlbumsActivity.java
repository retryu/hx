package com.ht.huaxin;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ht.huaxin.entity.Album;
import com.ht.huaxin.http.HttpUtils;
import com.ht.huaxin.utils.Constants;
import com.ht.huaxin.view.ShaderView;

public class AlbumsActivity extends Activity implements Callback {
	ListView albumsView;
	AlbumsAdapter albumsAdapter;
	Handler albumHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		albumHandler = new Handler(this);

		albumsView = (ListView) findViewById(R.id.albums);
		albumsView.setOnItemClickListener(albumClicked);

		AlbumsLoadThread loadThread = new AlbumsLoadThread();
		new Thread(loadThread).start();
	}

	ListView.OnItemClickListener albumClicked = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			Album album = (Album) parent.getItemAtPosition(position);

			Intent intent = new Intent(AlbumsActivity.this,
					PictureActivity.class);
			intent.putExtra(Constants.EXTRA_Album_id,
					String.valueOf(album.getId()));
			intent.putExtra(Constants.EXTRA_Album_title,
					String.valueOf(album.getTitle()));
			startActivity(intent);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.e("debug", "onTouch");

		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class AlbumsLoadThread implements Runnable {
		public void run() {
			try {
				String url = Constants.ALBUM_URL_PREFIX;
				JSONObject json = HttpUtils.get(url);
				JSONArray jsonArray = json.getJSONArray("entries");
				Type type = new TypeToken<List<Album>>() {
				}.getType();
				Gson gson = new Gson();
				List<Album> albums = gson.fromJson(jsonArray.toString(), type);
				Message msg = Message.obtain();
				msg.obj = albums;
				AlbumsActivity.this.albumHandler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("json", e.toString());
			}

		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		albumsAdapter = new AlbumsAdapter(AlbumsActivity.this,
				(List<Album>) msg.obj);
		albumsView.setAdapter(albumsAdapter);
		return false;
	}
}
