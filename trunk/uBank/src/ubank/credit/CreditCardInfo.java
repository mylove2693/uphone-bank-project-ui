package ubank.credit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class CreditCardInfo extends GeneralListActivity {

	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 接收数据
		Intent intent = getIntent();
		String creditcard = intent.getStringExtra("creditcard");

		// 模拟数据
		String[] value = new String[] { "44", "李四", "400", "100", "3/10" };
		SimpleAdapter adapter = createText_Text(new String[] { "信用卡账户",
				"持卡人姓名", "本期应还款额", "本期最低还款额", "本期到期还款日" }, value);
		setListAdapter(adapter);
		addLayoutBlow(R.layout.midle_btn);
		btnNext = (Button)findViewById(R.id.button);
		btnNext.setText("下一步");
		btnNext.setOnClickListener(btnOnClick);
	}

	private OnClickListener btnOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent= new Intent(CreditCardInfo.this,SelectRepaymentAcc.class);
			startActivity(intent);
		}
	};
}