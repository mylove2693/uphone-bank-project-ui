package ubank.account_query;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AccountInventoryList extends GeneralListActivity {
	private TextView above_txt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.above_list_txt);
		
		Intent intent = this.getIntent();
		
		String start_time = intent.getStringExtra("start_time");
		String end_time = intent.getStringExtra("end_time");
		String accNumValue = intent.getStringExtra("accNumValue"); 
		String accTypeValue = intent.getStringExtra("accTypeValue");
		String title = accTypeValue + accNumValue + "在" + start_time + "到"
						+ end_time + "之间的交易记录如下：";
		
		above_txt = (TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16);
		
		above_txt.setText(title);
	}
}