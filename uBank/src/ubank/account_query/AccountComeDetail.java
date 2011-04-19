package ubank.account_query;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountComeDetail extends GeneralListActivity {
	private TextView left_txt = null;
	private EditText right_edit = null;
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.below_list_txt);
		
		intent = this.getIntent();
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		setListener(tvClassSecond, this, AccountQueryType.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("来账查询");
		setListener(tvClassThird, this, AccountCome.class);
		
//		String[] name = new String[]{"来账时间","来账类型","来账金额","付款人姓名","付款账号种类","付款账号"};
//		String[] value = new String[]{"2011-3-8","转账","100000","李华","中国农业银行","62220220145454"};
//		setListAdapter(createText_Text(name, value));
		
		left_txt = (TextView)findViewById(R.id.below_list_left_txt).findViewById(R.id.blue_Text_View);
		left_txt.setText("描述");
		
		right_edit = (EditText)findViewById(R.id.below_list_right_txt).findViewById(R.id.et_user);
		
		setListValue();
	}
	
	private void setListValue(){
		String[] name = new String[]{"来账时间","来账类型","来账金额","付款人姓名","付款账号种类","付款账号"};
		String[] value = new String[6];
		String type = intent.getStringExtra("type");
		String accType = intent.getStringExtra("accTypeValue");
		String id = intent.getStringExtra("id");
		if (EHelper.hasInternet(this)) {
			JSONObject json = new JSONObject();
			try {
				if ("信用卡".equals(accType)) {
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD,
							EOperation.GET_COMEQUERY_INFO, type, id);
				} else {
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
							EOperation.GET_COMEQUERY_INFO, type, id);
				}
				System.out.println(json);
				for(int i = 0;i < name.length;i++){
					value[i] = json.get(name[i]).toString();
				}
				
				setListAdapter(createText_Text(name, value));
				
				right_edit.setText(json.getString("描述").toString());
				
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
