package com.example.volleydemo.ux.midas;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleydemo.R;
import com.example.volleydemo.VolleyDemoApplication;
import com.example.volleydemo.bl.data.Word;
import com.example.volleydemo.ux.ServiceFragment;
import com.google.gson.reflect.TypeToken;

public class MainFragment extends ServiceFragment implements ErrorListener {
	private static final String TAG = MainFragment.class.getSimpleName();

	private TextView authToken;


	private Listener<String> loginListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.d(TAG, "registerListener: " + response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
			serviceProvider.eduwordsService().getJson(); 
		}
	};

	private Listener<String> registerListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.d(TAG, "registerListener: " + response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
		}
	};
	private Listener<String> sessionObtainListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.d(TAG, "sessionObtainListener: " + response);
			authToken.setText(response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
			// serviceProvider.eduwordsService().register(registerListener,
			// MainFragment.this, response,
			// "test" + new Random().nextInt() + "@test.pl", "dupa",
			// "dupa");
			serviceProvider.eduwordsService().login(loginListener,
					MainFragment.this, response, "test2@test.pl", "test");
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		authToken = (TextView) rootView.findViewById(R.id.auth_token);
		serviceProvider.eduwordsService().obtainSession(sessionObtainListener,
				this);
		return rootView;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		error.printStackTrace();
		Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG)
				.show();
	}

}