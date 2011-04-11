package ubank.payment;

import ubank.account_manager.FirstAccount;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class SelectAcc extends GeneralListActivity {
	private String[] value={"首选账户","其他账户"};
	private TextView txt=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("账户类型选择");
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("缴费账户类型选择");
	        this.setListAdapter(createText_Img(value));
	  }
	  protected void onListItemClick(ListView l,View v,int position,long id){
			
			super.onListItemClick(l, v, position, id);
			if(id==0){//首选账户
				Intent intent=new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				String account="1111111";
				String money="10";
				intent.putExtra("account", account);
				intent.putExtra("money", money);
				intent.setClass(SelectAcc.this, InputPsw.class);
				SelectAcc.this.startActivity(intent);
			}else if(id==1){//其他账户
				Intent elseAcc_intent=new Intent();
				elseAcc_intent.setClass(SelectAcc.this, ElseAcc.class);
				SelectAcc.this.startActivity(elseAcc_intent);
			}
	  }
}


