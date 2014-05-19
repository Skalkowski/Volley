package com.example.volleydemo.bl.services.eduwords;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.volleydemo.bl.services.NetworkedService;
import com.example.volleydemo.bl.services.eduwords.requests.LoginRequest;
import com.example.volleydemo.bl.services.eduwords.requests.RegisterRequest;
import com.example.volleydemo.bl.services.eduwords.requests.SessionRequest;

public class EduwordsService extends NetworkedService {
	private static final String TAG = EduwordsService.class.getSimpleName();
	public static final String MAIN_URL = "http://eduwords.project-midas.com";
	public static final String SESSION_URL = "http://eduwords.project-midas.com/sessions.json";
	public static final String USERS_URL = "http://eduwords.project-midas.com/users";

	public EduwordsService() {
		super();
	}

	public void obtainSession(final Response.Listener<String> responseListener,
			final Response.ErrorListener errorListener) {
		SessionRequest request = new SessionRequest(Request.Method.GET,
				MAIN_URL, responseListener, errorListener);
		volleyRequestQueue.add(request);
	}

	public void register(final Response.Listener<String> responseListener,
			final Response.ErrorListener errorListener, String token,
			String email, String password, String repeatedPassword) {
		RegisterRequest registerRequest = new RegisterRequest(
				Request.Method.POST, USERS_URL, responseListener,
				errorListener, token, email, password, repeatedPassword);
		volleyRequestQueue.add(registerRequest);
	}
	
	public void login(final Response.Listener<String> responseListener,
			final Response.ErrorListener errorListener, String token,
			String email, String password) {
		LoginRequest loginRequest = new LoginRequest(
				Request.Method.POST, USERS_URL, responseListener,
				errorListener, token, email, password);
		volleyRequestQueue.add(loginRequest);
	}
}
