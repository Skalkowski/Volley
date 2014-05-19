package com.example.volleydemo.bl.services;

import com.android.volley.RequestQueue;

public class NetworkedService extends BaseService {

	protected RequestQueue volleyRequestQueue;

	public NetworkedService() {
		super();
		volleyRequestQueue = appContext.getVolleyRequestQueue();
	}
}
