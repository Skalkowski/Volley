package com.example.volleydemo.bl.services.eduwords.requests;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class WordsRequest extends StringRequest {

	public WordsRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener); 
	}

}