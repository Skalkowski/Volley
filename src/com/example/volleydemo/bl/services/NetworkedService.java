package com.example.volleydemo.bl.services;

import com.android.volley.RequestQueue;

public class NetworkedService extends BaseService {

	
	public static final String SESSION_COOKIE = "_eduwords_session";
	protected RequestQueue volleyRequestQueue;

	public NetworkedService() {
		super();
		volleyRequestQueue = appContext.getVolleyRequestQueue();
	}
}
