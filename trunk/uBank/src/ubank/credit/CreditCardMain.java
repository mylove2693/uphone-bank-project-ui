package ubank.credit;

import ubank.base.GeneralListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CreditCardMain extends GeneralListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SimpleAdapter adapter = createImg_Text_ImgAdapter(new String[] {
				"账户信息", "交易明细查看", "账户来账查看?", "开卡", "销卡", "信用卡还款" });
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		switch (position) {
		case 5:
			Intent intent = new Intent();
			intent.setClass(CreditCardMain.this, CardRepayment.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
