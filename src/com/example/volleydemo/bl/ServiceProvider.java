package com.example.volleydemo.bl;

import com.example.volleydemo.bl.services.eduwords.EduwordsService;

public class ServiceProvider {
	private EduwordsService eduwordsService;

	public ServiceProvider() {
	}

	public EduwordsService eduwordsService() {
		if (eduwordsService == null) {
			eduwordsService = new EduwordsService();
		}
		return eduwordsService;
	}

}
