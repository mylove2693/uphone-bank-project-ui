package ubank.credit;

import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
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

		initializeData();

	}

	private void initializeData() {
		// TODO Auto-generated method stub
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		SimpleAdapter adapter = createImg_Text_ImgAdapter(new String[] {
				"账户信息", "交易明细查看", "账户来账查看?", "开卡", "销卡", "信用卡还款" });
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = null;
		switch (position) {
		case 0:
			// 账户信息
			intent = new Intent();
			startActivity(intent);
			break;
		case 1:
			// 交易明细查看
			intent = new Intent();
			startActivity(intent);
			break;
		case 2:
			// 账户来账查看
			intent = new Intent();
			startActivity(intent);
			break;
		case 3:
			// 销卡
			intent = new Intent(CreditCardMain.this, OpenCard.class);
			startActivity(intent);
			break;
		case 4:
			// 销卡
			intent = new Intent(CreditCardMain.this, DestroyCard.class);
			startActivity(intent);
			break;
		case 5:
			// 信用卡还款
			intent = new Intent(CreditCardMain.this, CardRepayment.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
