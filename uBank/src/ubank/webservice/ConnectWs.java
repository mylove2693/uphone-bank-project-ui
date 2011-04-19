package ubank.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;

public class ConnectWs {

	public static JSONObject connect(Activity activity, EAccType accType, EOperation operation,
			String... params) throws IOException {
		// a,b,c->xx,yy,zz->t:o:xx:yy:zz->...->[is]->{a:"xx",b:"yy"}->{a:"a",b:"b"}
		HttpClient httpclient = new DefaultHttpClient();
		// 0.读取url
		HttpPost httppost = new HttpPost(EHelper.readUrl(activity));
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		// 1.加密，2.格式化参数
		String strParams = EHelper.setParams(accType, operation, EHelper.encode(params));
		lstNameValuePairs.add(new BasicNameValuePair("params", strParams));

		HttpEntity httpentity;
		try {
			httpentity = new UrlEncodedFormEntity(lstNameValuePairs, "utf-8");
			httppost.setEntity(httpentity);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpResponse response;
		try {

			response = httpclient.execute(httppost);
			StatusLine statusLine = response.getStatusLine();
			int code = statusLine.getStatusCode();
//			Log.v("asdf", Integer.toString(code));
			if (code == HttpStatus.SC_OK) {
				// 如果请求成功
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					// 3.is 转换成 string
					String result = EHelper.sToString(instream);
					// 4.包装成json object
					JSONObject jsonObject = new JSONObject(result);
					// 5.解密
					jsonObject = EHelper.decodeJSONObject(jsonObject);
					instream.close();
					return jsonObject;
				}
			} else {
				throw new IOException("status: " + code);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			httppost.abort();
			httpclient.getConnectionManager().shutdown();
		}

		return new JSONObject();
	}
}
