package com.example.vrs.starwar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import com.example.vrs.starwar.bean.BusinessException;


public class HttpUtil {

	private static final String DEFAULT_ERROR_MSG = "Server not responding";

	public HttpUtil() {
		init();
	}

	private void init() {
	}


	public String doGet(String url) {

		Log.d("URL", url);
		BasicHttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
		HttpConnectionParams.setSoTimeout(httpParameters, 60000);

		// create HttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		InputStream inputStream = null;
		// make GET request to the given URL
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Accept", "application/json"); // or application/jsonrequest
		httpget.setHeader("Content-Type", "application/json");
		try {
			HttpResponse httpResponse = httpClient.execute(httpget);
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				String json = getStringFromInputStream(inputStream);
				String errMsg = "";
				JSONObject errJson;
				try {
					errJson = new JSONObject(json);
					errMsg = errJson.getString("errMsg");
				} catch (JSONException e) {
					e.printStackTrace();
//					errMsg = json;
					errMsg = httpResponse.getStatusLine().getReasonPhrase();
				}

				if(TextUtils.isEmpty(errMsg) || errMsg.trim().equalsIgnoreCase("null"))
					errMsg = DEFAULT_ERROR_MSG;
				BusinessException exception = new BusinessException(
						httpResponse.getStatusLine().getStatusCode() + "",
						errMsg);
				throw exception;
			}
		} catch (IOException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException("201" + "",
					"Unable to connect to server please try again later");
			throw exception;
		}

		// convert inputstream to string
		if (inputStream != null) {
			try {
				String result=null;
				 result = getStringFromInputStream(inputStream);
				return result;
			} catch (IOException e) {
				e.printStackTrace();
				BusinessException exception = new BusinessException("201",
						"Unable to parse response");
				throw exception;

			}
		}
		return null;
	}


	/**
	 * This Method will get the String data from Input Stream
	 * 
	 * @param is
	 * @return String
	 * @throws IOException
	 */
	private String getStringFromInputStream(InputStream is) throws IOException {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}
