package com.example.ejemplorest.asynctasks;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncRest extends AsyncTask<Integer, Void, Integer> {

	private Context context;
	public AsyncRest(Context context) {
		this.context=context;
	}
	String url="http://10.0.2.2:8080/RestProject/rest/suma/";
	@Override
	protected Integer doInBackground(Integer... params) {
		int a = params[0];
		int b = params[1];
		url+=a+"/"+b+"/";
		Integer res=null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new  HttpGet(url);
		ResponseHandler<String> hander = new BasicResponseHandler();
		try {
			res=Integer.valueOf(httpclient.execute(get, hander));
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();
		return res;
	}
	@Override
	protected void onPostExecute(Integer result) {
		AlertDialog.Builder b = new AlertDialog.Builder(this.context);
		b.setTitle("Resultado");
		b.setMessage("El Resultado es "+result);
		b.show();
	}

}
