package com.example.volleydemo;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.bl.ServiceProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VolleyDemoApplication extends Application {
	private RequestQueue volleyRequestQueue;
	private static VolleyDemoApplication appContext;
	private ServiceProvider serviceProvider;
	private Gson gson;

	public static VolleyDemoApplication obtain() {
		return appContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		appContext = this;

		DefaultHttpClient httpclient = new DefaultHttpClient();

		CookieStore cookieStore = new BasicCookieStore();
		httpclient.setCookieStore(cookieStore);

		HttpStack httpStack = new HttpClientStack(httpclient);
		volleyRequestQueue = Volley.newRequestQueue(this, httpStack);
		serviceProvider = new ServiceProvider();
		gson = new GsonBuilder().create();
	}

	public RequestQueue getVolleyRequestQueue() {
		return volleyRequestQueue;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public Gson getGson() {
		return gson;
	}
}