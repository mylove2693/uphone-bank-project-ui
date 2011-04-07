package ubank.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.R;
import android.app.Activity;
import android.util.Log;

public enum EHelper {
	HELPER;

	private final static String TAG = "Enum Helper";
	private static String URL = "";

	/**
	 * 0.读取ip
	 * 
	 * @param activity
	 * @return
	 */
	public static String readUrl(Activity activity) {
		// 读取配置文件
		Properties p = new Properties();
		try {
			p.load(activity.getResources().openRawResource(R.raw.url));
			URL = p.getProperty("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, ">Can't read url.properties!");
			e.printStackTrace();
		}
		return URL;
	}

	public static JSONArray connect(int accType, int operation,
			String... params) {

		return null;
	}

	/**
	 * 1.加密
	 * 
	 * @return
	 */
	public static String[] encode(String[] params) {
		String[] arrParams = new String[params.length];
		for (String string : params) {

		}
		return arrParams;
	}

	/**
	 * 2.设置需要传输的参数
	 * 
	 * @author hosolo
	 * @param funNo
	 * @param value
	 * @return 格式话过的参数，like 0101:yes:haha
	 */
	public static String setParams(EAccType accType, EOperation operation,
			String[] value) {
		StringBuilder stringBuilder = new StringBuilder(
				EAccType.getAccTypeId(accType));
		stringBuilder.append(EOperation.getOperNum(operation));
		for (String string : value) {
			stringBuilder.append(":");
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

	// /**
	// * 3.包装json
	// *
	// * @return
	// */
	// public static JSONObject wrapupJSON(String accTyep, String operation,
	// String params) {
	// JSONObject jsonObj = new JSONObject();
	// jsonObj.put("accType", accTyep);
	// jsonObj.put("operation", operation);
	// jsonObj.put("params", params);
	//
	// return jsonObj;
	// }

	/**
	 * 3.将流转换成String
	 * 
	 * @author hosolo
	 * @param is
	 * @return String
	 */
	public static String sToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "===========convert Stream to String error=============");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	// /**
	// * 4.回复json格式
	// *
	// * @param value
	// * @return
	// */
	// public static JSONObject toJSONObject(String value) {
	// if (!value.equals("") || value == null) {
	// try {
	// // Parsing
	// JSONObject json = new JSONObject(value);
	// return json;
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// Log.e(TAG, "============parse JSON error==========");
	// }
	// }
	// return HELPER;
	//
	// }

	/**
	 * 4.解密json object对象里面的字符串
	 * 
	 * @param jsonObj
	 * @return
	 */
	public static JSONObject decodeJSONObject(JSONObject jsonObj) {
		JSONObject newJsonObj = new JSONObject();
		for (Iterator iter = jsonObj.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			try {
				String value = jsonObj.getString(key);
				// 执行解密
				//
				//
				newJsonObj.put(key, "c");
				System.out.println(jsonObj.getString(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newJsonObj;
	}

	/**
	 * 4.解密String
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeString(String str) {
		return str;
	}
}
