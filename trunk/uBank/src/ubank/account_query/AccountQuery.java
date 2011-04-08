/**
 * 
 */
package ubank.account_query;

import ubank.base.GeneralListActivity;
import ubank.main.Login;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Administrator
 *
 */
public class AccountQuery extends GeneralListActivity {
	private static String TAG = "ACCOUNT";
	private TextView accNum = null;
	private TextView accNumValue = null;
	private TextView accType = null;
	private TextView accTypeValue = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_query);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, Login.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询");
		
		Intent intent = this.getIntent();
		
		accNum = (TextView)findViewById(R.id.accNum).findViewById(R.id.blue_Text_View);
		accNum.setText(R.string.accounNum);
		accNumValue = (TextView)findViewById(R.id.accNumValue).findViewById(R.id.Text_View_18);
		accNumValue.setText(intent.getCharSequenceExtra("accNumValue"));
		accType = (TextView)findViewById(R.id.accType).findViewById(R.id.blue_Text_View);
		accType.setText(R.string.accountType);
		accTypeValue = (TextView)findViewById(R.id.accTypeValue).findViewById(R.id.Text_View_18);
		accTypeValue.setText(intent.getCharSequenceExtra("accTypeValue"));
		
		String[] value = new String[]{"账户信息及余额查询","账户明细查询","账户来账查询"};
		setListAdapter(createImg_Text_ImgAdapter(value));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		intent.putExtra("accNumValue", accNumValue.getText());
		intent.putExtra("accTypeValue", accTypeValue.getText());
		Log.i(TAG, String.valueOf(id));
		if(id == 0){
			intent.setClass(this, AccountQueryInfo.class);
		}else if(id == 1){
			intent.setClass(this, Inventory.class);
		}
		
		startActivity(intent);
		
	}

}
