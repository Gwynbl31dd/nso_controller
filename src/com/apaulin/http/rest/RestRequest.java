/**
 * 
 */
package com.apaulin.http.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.apaulin.xplorer.exception.NSOException;
import com.apaulin.xplorer.json.JSONDisplay;
import com.github.cliftonlabs.json_simple.JsonException;


/**
 * Process the classical HTTP request
 * @author Anthony Paulin
 * @since 23/07/2018
 * @version 0.2
 *
 */
public class RestRequest {
	
	private String getResult(String path,String url,String user,String password,String header,HttpRequestBase request) throws HTTPException, NSOException, IOException {
		StringBuffer result = new StringBuffer();
		CloseableHttpClient client = null;
		try {
			client = buildCustomHttpClient(user,password);
			String urlFinal = url+"/api"+path;
			request.addHeader(HttpHeaders.ACCEPT,header);
			request.addHeader(HttpHeaders.CONTENT_TYPE,header);
			HttpResponse response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			checkHeaderCode(statusCode,urlFinal);
			if(statusCode != 204) {//The code 204 is the no-content, therefore, not output is expected
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			}
		}
		catch(IOException | KeyManagementException | KeyStoreException e) {
			throw new NSOException(e);
		}
		finally {
			client.close();
		}
		return result.toString();
	}
	
	public String get(String path,String url,String user,String password,String header) throws HTTPException, NSOException {
		try {
			return getResult(path,url,user,password,header,new HttpGet(url+"/api"+path));
		} catch (IOException e) {
			throw new NSOException(e);
		}
	}
	
	public String get(String path,String url,String user,String password) throws HTTPException, NSOException {
		return this.get(path,url,user,password,"application/vnd.yang.collection+json");
	}
	
	/**
	 * Return the Header
	 * @param path
	 * @param url
	 * @param user
	 * @param password
	 * @param header
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HTTPException
	 * @throws NSOException 
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	public String head(String path,String url,String user,String password,String header) throws HTTPException, NSOException, IOException {
		StringBuffer result = null;
		CloseableHttpClient client = null;
		HttpResponse response = null;
		try {
			client = buildCustomHttpClient(user,password);
			String urlFinal = url+"/api"+path;
			HttpGet request = new HttpGet(url+"/api"+path);
			request.addHeader(HttpHeaders.ACCEPT,header);
			response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			checkHeaderCode(statusCode,urlFinal);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		}
		catch(IOException | KeyManagementException | KeyStoreException e) {
			throw new NSOException(e);
		}
		finally {
			client.close();
		}
		return response.getEntity().getContentType().toString();//Return the content Type
	}
	
	public String delete(String path,String url,String user,String password) throws HTTPException, NSOException {		
		try {
			return getResult(path,url,user,password,"application/vnd.yang.data+json",new HttpDelete(url+"/api"+path));
		} catch (IOException e) {
			throw new NSOException(e);
		}
	}
	
	public String post(String path,String url,String user,String password,String data) throws  NSOException, HTTPException {
		try {
			HttpPost httpPost = new HttpPost(url+"/api"+path);
			StringEntity params = new StringEntity(data);
			httpPost.setEntity(params);
			String result = getResult(path,url,user,password,"application/vnd.yang.data+json",httpPost);
			try {
				return new JSONDisplay(result).getJsonString();
			}
			catch(NullPointerException e) {
				return "{}";//return empty JSON if cannot parse it
			}
		} catch (IOException | JsonException e) {
			throw new NSOException(e);
		}
	}
	
	public String patch(String path,String url,String user,String password,String data) throws HTTPException, NSOException  {		
		try {
			HttpPatch httpPatch = new HttpPatch(url+"/api"+path);
			StringEntity params = new StringEntity(data);
			httpPatch.setEntity(params);
			return new JSONDisplay(getResult(path,url,user,password,"application/vnd.yang.data+json",httpPatch)).getJsonString();
		} catch (IOException | JsonException e) {
			throw new NSOException(e);
		}
	}
	
	public String put(String path,String url,String user,String password,String data) throws HTTPException, NSOException  {
		try {
			HttpPut httpPut = new HttpPut(url+"/api"+path);
			StringEntity params = new StringEntity(data);
			httpPut.setEntity(params);
			return new JSONDisplay(getResult(path,url,user,password,"application/vnd.yang.data+json",httpPut)).getJsonString();
		} catch (IOException | JsonException e) {
			throw new NSOException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	private CloseableHttpClient buildCustomHttpClient(String user,String password) throws KeyStoreException, KeyManagementException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		SSLConnectionSocketFactory sslsf = null;
		UsernamePasswordCredentials credentials
		 = new UsernamePasswordCredentials(user,password);
		provider.setCredentials(AuthScope.ANY, credentials);
		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws java.security.cert.CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(),
			        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (java.security.NoSuchAlgorithmException e) {
			try {
				throw new NoSuchAlgorithmException(e.toString());
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
		}
		return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultCredentialsProvider(provider).build();
	}
	//TODO Check no contain code 201
	private void checkHeaderCode(int code,String url) throws HTTPException {
		if(code == 501) {
			throw new HTTPException("Not Implemented",code,url);
		}
		else if(code == 500) {
			throw new HTTPException("Internal server error",code,url);
		}
		else if(code == 401) {
			throw new HTTPException("Unauthorized ",code,url);
		}
		else if(code == 404) {
			throw new HTTPException("Not Found",code,url);
		}
	}
}
