package ubank.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	/**
	 * 0.读取ip
	 * 
	 * @param activity
	 * @return
	 */
	public static String readUrl(Activity activity) {
		// 读取配置文件
		String URL = "";
		Properties p = new Properties();
		try {
			p.load(activity.getResources().openRawResource(R.raw.url));
			URL = p.getProperty("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, ">Can't read url.properties!");
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
		return params;
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
		stringBuilder.append(":");
		stringBuilder.append(EOperation.getOperNum(operation));
		for (String string : value) {
			stringBuilder.append(":");
			stringBuilder.append(string);
		}
		return stringBuilder.toString();
	}

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
				newJsonObj.put(key, value);
				// System.out.println(jsonObj.getString(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, "============decode error==========");
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

	public static JSONArray toJsonArray(JSONObject jsonObj) {
		JSONArray name = jsonObj.names();
		JSONArray array = new JSONArray();
		try {
			array = jsonObj.toJSONArray(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "============toJsonArray error==========");
		}
		return array;
	}

	public static List<String> toList(JSONObject jsonObj) {
		List<String> lstValue = new ArrayList<String>();
		if (jsonObj == null || !jsonObj.equals("")) {
			try {
				JSONArray nameArray = jsonObj.names();
				JSONArray valArray = jsonObj.toJSONArray(nameArray);
				if (valArray != null) {
					for (int i = 0; i < valArray.length(); i++) {
						lstValue.add(valArray.getString(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, "============toList error==========");
			}
		}
		return lstValue;
	}
/**
 * 
 *@author gsm
 *2011-4-14 
 * @param jsonObj
 * @return
 */
	public static Map<String,String> toMap(JSONObject jsonObj) {
			
		Map<String,String> mapValue = new HashMap<String,String>();
		if (jsonObj == null || !jsonObj.equals("")) {
			try {
				JSONArray nameArray = jsonObj.names();
				JSONArray valArray = jsonObj.toJSONArray(nameArray);
				if (valArray != null) {
					for (int i = 0; i < valArray.length(); i++) {
						mapValue.put(jsonObj.names().getString(i), valArray.getString(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, "============toMap error==========");
			}
		}
		return mapValue;
	}
	public static boolean toBoolean(JSONObject jsonObj) {
		boolean bool = false;
		try {
			bool = jsonObj.getBoolean("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bool;
		}
		return bool;
	}

	/**
	 * 后台返回时有可能是字符串
	 *@author gsm
	 *2011-4-15 
	 * @param jsonObj
	 * @return
	 */
	public static String toStr(JSONObject jsonObj) {
		StringBuilder str = new StringBuilder();
		if (jsonObj == null || !jsonObj.equals("")) {
			try {
				JSONArray nameArray = jsonObj.names();
				JSONArray valArray = jsonObj.toJSONArray(nameArray);
				if (valArray != null) {
					for (int i = 0; i < valArray.length(); i++) {
						str.append(valArray.getString(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, "============toStr error==========");
			}
		}
		return str.toString();
	}

}
