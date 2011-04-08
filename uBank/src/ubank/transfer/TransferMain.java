package ubank.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralListActivity;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.Speedy;
import ubank.payment.WaitCost;
/**
 * 转账的主界面
 * @author Administrator
 *
 */
public class TransferMain extends GeneralListActivity{
	private TextView txt=null;
	String[] s={"手机到手机转账","手机到签约账户转账"};
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费");	        
	        this.setListAdapter(createImg_Text_ImgAdapter(s));       
	  }
	  @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==0){//待缴费项目
			Intent payment_intent=new Intent();
			payment_intent.setClass(TransferMain.this, WaitCost.class);
			TransferMain.this.startActivity(payment_intent);
		}else if(id==1){//便捷服务
			Intent speedy_intent=new Intent();
			speedy_intent.setClass(TransferMain.this, Speedy.class);
			TransferMain.this.startActivity(speedy_intent);
		}
	}

}
