/**
 * 
 */
package com.ones.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

/**
 * @author sontruong
 *
 */
public class RequestUtils {

	public static String buildUrl(String url, java.util.List<NameValuePair> nvps) throws URISyntaxException {
		URIBuilder builder = new URIBuilder(url);
		builder.addParameters(nvps);
		return builder.build().toString();
	}

	public static BasicHeader buildAuthenHeader(String token) {
		BasicHeader header = new BasicHeader("Authorization", token);
		return header;
	}
	
	public static BasicHeader buildContentType() {
		BasicHeader header = new BasicHeader("content-type", "application/json;charset=UTF-8");
		return header;
	}

	private static void addHeaders(HttpRequestBase httprequest, BasicHeader... headers) {
		if (null == headers || 0 == headers.length) {
			return;
		}
		for (BasicHeader header : headers) {
			httprequest.addHeader(header);
		}
	}

	public static HttpResponse post(String uri, Object object, BasicHeader... headers)
			throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(uri);
		addHeaders(httppost, headers);
		// Request parameters and other properties.
		StringEntity params = new StringEntity(gson.toJson(object), "UTF-8");
		httppost.setEntity(params);
		// Execute and get the response.
		return httpclient.execute(httppost);
	}
	
	public static HttpResponse post(String uri, BasicHeader... headers)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(uri);
		addHeaders(httppost, headers);
		// Execute and get the response.
		return httpclient.execute(httppost);
	}

	public static HttpResponse post(String uri, ArrayList<NameValuePair> params, BasicHeader... headers)
			throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(uri);
		addHeaders(httppost, headers);
		// Request parameters and other properties.
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		httppost.setEntity(entity);

		// Execute and get the response.
		return httpclient.execute(httppost);
	}

	public static HttpResponse put(String uri, Object object, BasicHeader... headers)
			throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		HttpClient httpclient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(uri);
		addHeaders(httpPut, headers);
		// Request parameters and other properties.
		StringEntity params = new StringEntity(gson.toJson(object));
		httpPut.setEntity(params);

		// Execute and get the response.
		return httpclient.execute(httpPut);
	}

	public static HttpResponse get(URI uri, BasicHeader... headers) throws Exception {
		return get(uri.toString(), headers);
	}

	public static HttpResponse get(String uri, List<NameValuePair> params, BasicHeader... headers)
			throws ClientProtocolException, IOException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		addHeaders(httpGet, headers);
		addParams(builder, params);
		HttpResponse response = null;
		// Execute and get the response.
		response = httpclient.execute(httpGet);

		return response;
	}

	private static void addParams(UriComponentsBuilder builder, List<NameValuePair> params) {
		if (null == params || 0 == params.size() || null == builder) {
			return;
		}
		for (NameValuePair param : params) {
			builder.queryParam(param.getName(), param.getValue());
		}
	}

	public static HttpResponse get(String uri, BasicHeader... headers) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		addHeaders(httpGet, headers);
		HttpResponse response = null;
		try {
			// Execute and get the response.
			response = httpclient.execute(httpGet);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return response;
	}

	public static HttpResponse delete(URI uri, BasicHeader... headers) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(uri.toString());
		addHeaders(httpDelete, headers);
		HttpResponse response = null;
		try {
			// Execute and get the response.
			response = httpclient.execute(httpDelete);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return response;
	}
	
	public static HttpResponse delete(String uri, BasicHeader... headers) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(uri);
		addHeaders(httpDelete, headers);
		HttpResponse response = null;
		try {
			// Execute and get the response.
			response = httpclient.execute(httpDelete);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return response;
	}

	public static ResponseMessage getMessage(HttpResponse response) throws IOException {
		ResponseMessage message = new ResponseMessage();
		message.setHttpStatus(HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = null;
			try {
				instream = entity.getContent();
				String result = IOUtils.toString(instream);
				message.setMessage(result);
				if(HttpStatus.OK != message.getHttpStatus()) {
					System.out.println("=== [getMessage]" + result);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				if (null != entity) {
					try {
						instream.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return message;
	}
}
