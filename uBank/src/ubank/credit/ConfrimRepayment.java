package ubank.credit;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class ConfrimRepayment extends GeneralListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 模拟数据
		String[] value = new String[] { "asdf", "15435" };
		SimpleAdapter adapter = createText_Text(
				new String[] { "还款账户", "账户余额" }, value);
		setListAdapter(adapter);
		addLayoutBlow(R.layout.account_type);

	}
}
