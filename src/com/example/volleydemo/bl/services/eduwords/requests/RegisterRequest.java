package com.example.volleydemo.bl.services.eduwords.requests;

import java.io.UnsupportedEncodingException;
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

public class RegisterRequest extends StringRequest {
	private static final String TAG = RegisterRequest.class.getSimpleName();

	private static final String SIGNED_UP = "Signed up!";
	private static final String EMAIL_TAKEN = "Email has already been taken";

	private static final String PARAM_TOKEN = "authenticity_token";
	private static final String PARAM_EMAIL = "user[email]";
	private static final String PARAM_PASSWORD = "user[password]";
	private static final String PARAM_PASSWORD_CONFIRM = "user[password_confirmation]";

	private String token;
	private String email;
	private String password;
	private String repeatedPassword;

	public RegisterRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener, String token, String email,
			String password, String repeatedPassword) {
		super(method, url, listener, errorListener);
		this.token = token;
		this.email = email;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		Map<String, String> params = new HashMap<String, String>();
		params.put(PARAM_TOKEN, token);
		params.put(PARAM_EMAIL, email);
		params.put(PARAM_PASSWORD, password);
		params.put(PARAM_PASSWORD_CONFIRM, repeatedPassword);
		return params;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			Log.i("dupas", parsed);
			if (parsed.contains(SIGNED_UP)) {
				parsed = SIGNED_UP;
			} else if (parsed.contains(EMAIL_TAKEN)) {
				parsed = EMAIL_TAKEN;
			} else {
				Log.d(TAG, parsed);
				parsed = "IMPLEMENT RESPONSE in "
						+ RegisterRequest.class.getSimpleName();

			}
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}
}
