package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ManageCost extends GeneralListActivity {
	private String[] name={"平安保险","人寿保险","交通罚款","水费","电费","报纸订阅"};
	private String[] value={"平安保险","人寿保险","交通罚款","水费","电费","报纸订阅"};
	private TextView txt=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("缴费项目管理");
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("已开通项目:");
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
}


