package com.example.volleydemo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.bl.ServiceProvider;

import android.app.Application;

public class VolleyDemoApplication extends Application {
	private RequestQueue volleyRequestQueue;
	private static VolleyDemoApplication appContext;
	private ServiceProvider serviceProvider;

	public static VolleyDemoApplication obtain() {
		return appContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		appContext = this;
		volleyRequestQueue = Volley.newRequestQueue(this);
		serviceProvider = new ServiceProvider();
	}

	public RequestQueue getVolleyRequestQueue() {
		return volleyRequestQueue;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
}
