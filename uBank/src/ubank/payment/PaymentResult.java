package ubank.payment;

import ubank.base.Listener;
import ubank.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class PaymentResult extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.payment_result_one);
		

		TextView tvflag = (TextView) findViewById(R.id.tv_paymentdl_flag);
		TextView tvshow = (TextView) findViewById(R.id.tv_paymentdl_info);
		Button btnok = (Button) findViewById(R.id.btn_paymentdl_ok);

		Intent receive_intent = getIntent();
	    String flag = receive_intent.getStringExtra("flag");
		String info = receive_intent.getStringExtra("info");
	    tvflag.setText(flag);
		tvshow.setText(info);
		btnok.setText("返回");

		btnok.setOnClickListener(new Listener(PaymentResult.this,AllPaymentSer.class));
	}

	class BtnOkCL implements OnClickListener {
		private Class<Activity> toActivity;
		private BtnOkCL(Class toActivity){
			this.toActivity=toActivity;
		}
		public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent transinfo_intent = new Intent();				
	    		transinfo_intent.setClass(PaymentResult.this, toActivity);
	    		startActivity(transinfo_intent);
	    		PaymentResult.this.finish();
			}
	}
}