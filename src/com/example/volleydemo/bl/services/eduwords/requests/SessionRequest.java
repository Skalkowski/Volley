package com.example.volleydemo.bl.services.eduwords.requests;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

public class SessionRequest extends StringRequest {
	private static final String TAG = SessionRequest.class.getSimpleName();

	public SessionRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	// {"url":"http://eduwords.project-midas.com/sessions/1piyVZofC1s6CyCC40z7GNUQ1XX5wtKrzx0oshMwXAE=.json"}
	// <meta content="zNp0WzsLQ22Jk2dQ7QACWMUerREjFmJ0d/zI9PUe+Ck="
	// name="csrf-token" />
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			// parsed =parseSessionJsonResponse(parsed);
			parsed = parseMainSiteResponse(parsed);
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}

	private String parseMainSiteResponse(String parsed) {
		Pattern p = Pattern
				.compile("<meta content=\"\\S*\" name=\"csrf-token\" \\/>");
		Matcher m = p.matcher(parsed);

		if (m.find()) {
			parsed = m.group(0);
		}
		// Because fuck you thats why
		parsed = parsed.substring(
				parsed.indexOf("\"") + 1,
				parsed.indexOf("\"")
						+ "zNp0WzsLQ22Jk2dQ7QACWMUerREjFmJ0d/zI9PUe+Ck="
								.length() + 1);
		Log.d(TAG, "token = " + parsed);
		return parsed;
	}

	private String parseSessionJsonResponse(String parsed) {
		int slashIndex = parsed.lastIndexOf("/");
		int dotIndex = parsed.lastIndexOf(".");
		parsed = parsed.substring(slashIndex, dotIndex);
		return parsed;
	}

	@Override
	protected VolleyError parseNetworkError(VolleyError volleyError) {
		Log.d(TAG, new String(volleyError.networkResponse.data));
		return super.parseNetworkError(volleyError);
	}
}
