package com.example.volleydemo.bl.services;

import com.example.volleydemo.VolleyDemoApplication;

public class BaseService {
	protected VolleyDemoApplication appContext;

	public BaseService() {
		appContext = VolleyDemoApplication.obtain();
	}
}
