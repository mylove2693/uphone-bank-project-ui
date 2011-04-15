package ubank.transfer;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Transfer_OtherAccSelect extends GeneralActivity {

	private TextView txt=null;
	private Button next_btn=null;
	
	// 导航栏三级标题
	String title = null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent up_intent = getIntent();
			// 获得传过来的导航栏标题
			title = up_intent.getStringExtra("title");
	        
	        addLayout(R.layout.account_type);
	       	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("转账汇款>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText(title);
	        
	        /**
	         * 添加数据的代码
	         */
	        {
	        Account_Select s=(Account_Select)findViewById(R.id.account_select);
	        JSONObject type=new ConnectWs().connect(this, EAccType.NULL,EOperation.GET_ACC_TYPE_ALL ,"");
	        System.out.println(type.toString());
//	        s.AddTypeData(type.to)
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	        //确认缴费按钮
	        next_btn=(Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
	        next_btn.setText("下一步");
	        next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					/**
					 * 将服务器上取得的值传给下一个Activity
					 */
					Intent elseAcc_intent=new Intent();
					elseAcc_intent.putExtra("title",Transfer_OtherAccSelect.this.title);
					elseAcc_intent.setClass(Transfer_OtherAccSelect.this, Transfer_inpsd.class);
					Transfer_OtherAccSelect.this.startActivity(elseAcc_intent);
				}
			});
	  }
}
