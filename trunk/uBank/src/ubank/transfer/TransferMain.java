package ubank.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.Speedy;
import ubank.payment.WaitCost;

/**
 * 杨勇
 * 转账的主界面
 * @author Administrator
 * 
 */
public class TransferMain extends GeneralListActivity {
	String[] s = { "手机到手机转账", "手机到签约账户转账" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 设置导航栏的和添加监听
		 */
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("转账汇款");
		this.setListAdapter(createImg_Text_ImgAdapter(s));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// 取得listview中的文本框
		TextView gettv = (TextView) v.findViewById(R.id.data_text);
		if (id == 0) {
			// 手机到手机转账
			Intent next_intent = new Intent();
			// 取得id所在的一行中的文本值作为下一个界面的导航栏
			next_intent.putExtra("title", gettv.getText().toString());
			next_intent.setClass(TransferMain.this, TransferAccSelect.class);
			TransferMain.this.startActivity(next_intent);
		} else if (id == 1) {
			// 手机到签约账户转账
			Intent next_intent = new Intent();
			next_intent.putExtra("title", gettv.getText().toString());
			next_intent.setClass(TransferMain.this, TransferAccSelect.class);
			TransferMain.this.startActivity(next_intent);
		}
	}

}
