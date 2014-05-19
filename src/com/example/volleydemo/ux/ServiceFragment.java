package com.example.volleydemo.ux;

import android.os.Bundle;

import com.example.volleydemo.VolleyDemoApplication;
import com.example.volleydemo.bl.ServiceProvider;

public class ServiceFragment extends BaseFragment {
	protected ServiceProvider serviceProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serviceProvider = VolleyDemoApplication.obtain().getServiceProvider();
	}
}
