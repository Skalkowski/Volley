package com.example.volleydemo.ux.midas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleydemo.R;
import com.example.volleydemo.ux.ServiceFragment;


public class MainFragment extends ServiceFragment implements ErrorListener {
	private static final String TAG = MainFragment.class.getSimpleName();
	

	private TextView authToken;

	private Listener<String> registerListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.d(TAG, "registerListener: " + response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
		}
	};
	private Listener<String> logIn = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			authToken.setText(response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
			serviceProvider.eduwordsService().login(registerListener,
					MainFragment.this,  response,
					"test2@test.pl", "test");
		}


	};
	
	private Listener<String> signUp = new Listener<String>() {
		@Override
		public void onResponse(String response) {
			Log.d(TAG, "sessionObtainListener: " + response);
			authToken.setText(response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
			serviceProvider.eduwordsService().register(registerListener,
					MainFragment.this, response,
					"test@test.pl", "dupa" ,
					"dupa"); 
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		authToken = (TextView) rootView.findViewById(R.id.auth_token);
		serviceProvider.eduwordsService().obtainSession(logIn,
				this);
		
		serviceProvider.eduwordsService().getJson();
		
		return rootView;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		error.printStackTrace();
		Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG)
				.show();
	}

}
