package ubank.account_query;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ubank.base.GeneralListActivity;
import ubank.main.Login;
import ubank.main.R;

public class AccountComeList extends GeneralListActivity {
	private TextView above_txt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.above_list_txt);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, Login.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("来账查询");
		
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
}
