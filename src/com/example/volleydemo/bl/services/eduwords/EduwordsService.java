package com.example.volleydemo.bl.services.eduwords;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleydemo.bl.services.NetworkedService;
import com.example.volleydemo.bl.services.eduwords.requests.LoginRequest;
import com.example.volleydemo.bl.services.eduwords.requests.RegisterRequest;
import com.example.volleydemo.bl.services.eduwords.requests.SessionRequest;


public class EduwordsService extends NetworkedService {
	private static final String TAG = EduwordsService.class.getSimpleName();
	public static final String MAIN_URL = "http://eduwords.project-midas.com";
	public static final String SESSION_URL = "http://eduwords.project-midas.com/sessions.json";
	public static final String USERS_URL = "http://eduwords.project-midas.com/users";
	public static final String WORDS_URL = "http://eduwords.project-midas.com/words.json";

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
				Request.Method.POST, SESSION_URL, responseListener,
				errorListener, token, email, password);
		volleyRequestQueue.add(loginRequest);
	}
	
	public void getJson(){
		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, WORDS_URL,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.i("textasd", response.toString());

						parseJson(response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				});
		volleyRequestQueue.add(jr);
	}
	
	private void parseJson(JSONObject json) {
		try {
			JSONArray words = json.getJSONArray("words");

			for (int i = 0; i < words.length(); i++) {
				JSONObject wordJson = words.getJSONObject(i);

				Log.i("word",
						"id: " + wordJson.optString("id") + " slowo: "
								+ wordJson.optString("namelanguage1") + " tlumaczeniee: "
								+ wordJson.optString("namelanguage2"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
