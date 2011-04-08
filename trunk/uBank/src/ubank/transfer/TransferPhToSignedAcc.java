package ubank.transfer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;

/**
 * 手机到签约账户转账
 * 
 * @author Administrator
 * 
 */
public class TransferPhToSignedAcc extends GeneralActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer_phtosigacc);
		TextView transfer_num_txtview = (TextView) findViewById(
				R.id.transfer_acc_txt).findViewById(R.id.Text_View_18);
		transfer_num_txtview.setText("请输入转账金额:");
		
		
		TextView transfer_acc_txtview = (TextView) findViewById(
				R.id.transfer_amt_txt).findViewById(R.id.Text_View_18);
		transfer_acc_txtview.setText("请输入转账金额:");
		
		
		TextView transfer_psd_txtview = (TextView) findViewById(
				R.id.transfer_psd_txt).findViewById(R.id.Text_View_18);
		transfer_psd_txtview.setText("请输入转账金额:");
		
		
		Button next_btn=(Button)findViewById(R.id.next_btn);
		next_btn.setText("下一步");

	}

}
