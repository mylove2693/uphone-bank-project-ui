package ubank.account_query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class AccountComeList extends GeneralListActivity {
	private TextView above_txt = null;
	private String start_time = "";
	private String end_time = null;
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.above_list_txt);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		setListener(tvClassSecond, this, AccountQueryType.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("来账查询");
		setListener(tvClassThird, this, AccountCome.class);
		
		Intent intent = this.getIntent();
		
		String start_time = intent.getStringExtra("start_time");
		String end_time = intent.getStringExtra("end_time");
		String accNumValue = intent.getStringExtra("accNumValue"); 
		String accTypeValue = intent.getStringExtra("accTypeValue");
		String title = accTypeValue + accNumValue + "在" + start_time + "到"
						+ end_time + "之间的交易记录如下：";
		
		above_txt = (TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
		above_txt.setText(title);
		
		String[] name = new String[]{"2011-3-8","2011-3-9","2011-3-10"};
		String[] value = new String[]{"转账","汇款","转账"};
		setListAdapter(createText_Text_Img(name, value));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, AccountComeDetail.class);
		this.startActivity(intent);
	}
	private void setListData(){
		String userid = "1";
		String accTypeValue = intent.getStringExtra("accTypeValue");
		String[] name = null;
		String[] value = null;
		JSONObject json = new JSONObject();
		JSONArray names = null;
		if("信用卡".equals(accTypeValue)){
			json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.GET_COME_HISTORY,
					userid,start_time,end_time);
		}else if("活期储蓄卡".equals(accTypeValue)){
			json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.GET_COME_HISTORY,
					userid,start_time,end_time);
		}
		
		names = json.names();
		name = new String[names.length()];
		value = new String[names.length()];
		for(int i = 0;i < names.length();i++){
			try {
				name[i] = names.getString(i);
				value[i] = json.getString(names.getString(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setListAdapter(createText_Text_Img(name, value));
	}
}
