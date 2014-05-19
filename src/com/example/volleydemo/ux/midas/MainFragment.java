package com.example.volleydemo.ux.midas;

import java.util.Random;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleydemo.R;
import com.example.volleydemo.VolleyDemoApplication;
import com.example.volleydemo.R.layout;
import com.example.volleydemo.bl.services.eduwords.EduwordsService;
import com.example.volleydemo.ux.BaseFragment;
import com.example.volleydemo.ux.ServiceFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
	private Listener<String> sessionObtainListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			authToken.setText(response);
			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
			serviceProvider.eduwordsService().login(registerListener,
					MainFragment.this, response,
					"dupa@dupa.pl", "dupa");
		}

//		@Override
//		public void onResponse(String response) {
//			Log.d(TAG, "sessionObtainListener: " + response);
//			authToken.setText(response);
//			Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
//			serviceProvider.eduwordsService().register(registerListener,
//					MainFragment.this, response,
//					"test" + new Random().nextInt() + "@test.pl", "dupa",
//					"dupa"); 
//		}
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
