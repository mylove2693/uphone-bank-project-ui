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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInventoryList extends GeneralListActivity {
	private TextView above_txt = null;
	private String start_time = "";
	private String end_time = "";
	private Intent intent = null;
	private String accTypeValue = "";
	private String accNumValue = "";
	private String userid = "1";
	private String[] paramId = null;
	private String[] name = null;
	private String[] value = null;

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
		tvClassThird.setText("明细查询");
		setListener(tvClassThird, this, AccountInventory.class);

		intent = this.getIntent();
		start_time = intent.getStringExtra("start_time");
		end_time = intent.getStringExtra("end_time");
		accNumValue = intent.getStringExtra("accNumValue");
		accTypeValue = intent.getStringExtra("accTypeValue");
		
		String title = accTypeValue + accNumValue + "在" + start_time + "到"
				+ end_time + "之间的交易记录如下：";

		above_txt = (TextView) findViewById(R.id.above_list_txt).findViewById(
				R.id.Text_View_16_Gray);
		above_txt.setText(title);

		setListData();
		// String[] name = new String[]{"2011-3-8","2011-3-9","2011-3-10"};
		// String[] value = new String[]{"支出","收入","支出"};
		// setListAdapter(createText_Text_Img(name, value));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("id", paramId[position]);
		intent.putExtra("accTypeValue", accTypeValue);
		intent.putExtra("type", value[position]);
		intent.setClass(this, AccountInventoryDetail.class);
		this.startActivity(intent);
	}

	private void setListData() {
		
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_LIST_HISTORY, userid, start_time,
						end_time);
				String result = json.getString("info");
				String[] temp = result.split(",");
				paramId = new String[temp.length];
				name = new String[temp.length];
				value = new String[temp.length];
				for(int i = 0;i < temp.length;i++){
					String[] temp1 = temp[i].split("#");
					paramId[i] = temp1[0];
					name[i] = temp1[1];
					value[i] = temp1[2];
				}
				setListAdapter(createText_Text_Img(name, value));
				
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
