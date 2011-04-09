package ubank.account_query;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.os.Bundle;
import android.widget.TextView;

public class AccountInventoryDetail extends GeneralListActivity {
	private TextView left_txt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.below_list_txt);
		
		String[] name = new String[]{"交易日期","来账账户","支出","余额"};
		String[] value = new String[]{"2011-3-8","62220220145454","100000","2500000"};
		setListAdapter(createText_Text(name, value));
		
		left_txt = (TextView)findViewById(R.id.below_list_left_txt).findViewById(R.id.blue_Text_View);
		left_txt.setText("描述");
	}
}
