package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Lately extends GeneralListActivity {
	private String[] name={"2011-03-08","2011-03-09","2011-03-10",
						   "2011-03-11","2011-03-12","2011-03-13","2011-03-14"};
	private String[] value={"水费","电费","房租","天然气","手机充值","Q币充值","网易充值"};
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
	        tvClassThird.setText("历史缴费记录");
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("从2011-03-01到2011-03-19的历史缴费记录:");
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//水费
			Intent water_intent=new Intent();
			String[] value={"2011-07-11","水费","111111","20.00","s32332"};
			water_intent.putExtra("value", value);
			water_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(water_intent);
		}else if(id==1){//电费
			Intent electricity_intent=new Intent();
			String[] value={"2011-07-11","电费","222222","30.00","s36575"};
			electricity_intent.putExtra("value", value);
			electricity_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(electricity_intent);
		}else if(id==2){//房租费
			Intent rent_intent=new Intent();
			String[] value={"2011-07-19","房租","111111","200.00","s23421"};
			rent_intent.putExtra("value", value);
			rent_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(rent_intent);
		}
		
	}
}


