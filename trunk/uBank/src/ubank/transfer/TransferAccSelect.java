package ubank.transfer;

import java.io.IOException;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.payment.SelectAcc;
import ubank.webservice.ConnectWs;
/**
 * 转账的
 * 首选和其他账户
 * 的选择
 * @author Administrator
 *
 */
public class TransferAccSelect extends GeneralListActivity{
	private String[] value={"首选账户","其他账户"};
	private TextView txt=null;
	String title=null;
	String acc_type,acc_num;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent up_intent=getIntent();
	        //获得传过来的标题
	        title=up_intent.getStringExtra("title");
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("转账汇款>");
	       //转账汇款的监听
	        setListener(tvClassSecond, this, TransferMain.class);
	        
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText(title);
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("请选择账户：");
	        
	        /**
	         * 
	         *获取到后台的数据
	         */ 
	        
	        this.setListAdapter(createText_Img(value));
	  }
	  protected void onListItemClick(ListView l,View v,int position,long id){
			
			super.onListItemClick(l, v, position, id);
			if(id==0){//首选账户
				
				
				/**
				 * 向服务器发请求获取首选账户和其余额
				 */
				JSONObject jsonObj=null;
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,EOperation.GET_PRE_ACC, "1");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String str=EHelper.toStr(jsonObj);
				if(str==null){
//					String acc_type,acc_num;
					acc_type="110";
					acc_num="100000";
				}
				acc_type=str.split("#")[0];
				acc_num=str.split("#")[1];

				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				Intent elseAcc_intent = new Intent();
				elseAcc_intent.putExtra("acc_type", acc_type);
				elseAcc_intent.putExtra("acc_num", acc_num);
				elseAcc_intent.putExtra("title",
						TransferAccSelect.this.title);
				elseAcc_intent.setClass(TransferAccSelect.this,
						Transfer_inpsd.class);
				TransferAccSelect.this.startActivity(elseAcc_intent);
			}else if(id==1){//其他账户
				Intent elseAcc_intent=new Intent();
				elseAcc_intent.putExtra("title",this.title);
				elseAcc_intent.setClass(TransferAccSelect.this, Transfer_OtherAccSelect.class);
				TransferAccSelect.this.startActivity(elseAcc_intent);
			}
	  }

}
