package ubank.payment;

import org.json.JSONObject;

import ubank.account_manager.FirstAccount;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
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
				String account=null;
				String money=null;
				Intent intent=getIntent();
				
				/**
				 * 向服务器发请求获取首选账户和其余额
				 */
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,EOperation.GET_PRE_ACC, "1");
				String str=EHelper.toStr(jsonObj);
				if(str==null){
					account="110";
					money="100000";
				}
				account=str.split("#")[1];
				money=str.split("#")[2];
				
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


