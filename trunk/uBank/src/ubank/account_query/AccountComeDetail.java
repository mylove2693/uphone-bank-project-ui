package ubank.account_query;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountComeDetail extends GeneralListActivity {
	private TextView left_txt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.below_list_txt);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		setListener(tvClassSecond, this, AccountQueryType.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("来账查询");
		setListener(tvClassThird, this, AccountCome.class);
		
		String[] name = new String[]{"来账时间","来账类型","来账金额","付款人姓名","付款账号种类","付款账号"};
		String[] value = new String[]{"2011-3-8","转账","100000","李华","中国农业银行","62220220145454"};
		setListAdapter(createText_Text(name, value));
		
		left_txt = (TextView)findViewById(R.id.below_list_left_txt).findViewById(R.id.blue_Text_View);
		left_txt.setText("描述");
	}
}
