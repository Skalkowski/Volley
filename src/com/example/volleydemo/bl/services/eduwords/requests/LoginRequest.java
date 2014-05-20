package com.example.volleydemo.bl.services.eduwords.requests;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

public class LoginRequest extends StringRequest {
	private static final String TAG = LoginRequest.class.getSimpleName();

	private static final String LOGGED_IN = "Logged in!";

	private static final String PARAM_TOKEN = "authenticity_token";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PASSWORD = "password";

	private String token;
	private String email;
	private String password;

	public LoginRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener, String token, String email,
			String password) {
		super(method, url, listener, errorListener);
		this.token = token;
		this.email = email;
		this.password = password;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return Collections.emptyMap();
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		Map<String, String> params = new HashMap<String, String>();
		params.put(PARAM_TOKEN, token);
		params.put(PARAM_EMAIL, email);
		params.put(PARAM_PASSWORD, password);
		return params;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			if (parsed.contains(LOGGED_IN)) {
				parsed = LOGGED_IN;
			} else {
				Log.i(TAG, parsed);
				parsed = "IMPLEMENT RESPONSE in "
						+ LoginRequest.class.getSimpleName();
			}
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}
}