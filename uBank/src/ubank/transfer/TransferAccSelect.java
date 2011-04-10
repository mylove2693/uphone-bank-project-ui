package ubank.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.payment.SelectAcc;
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
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent up_intent=getIntent();
	        //获得传过来的标题
	        title=up_intent.getStringExtra("title");
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("转账汇款>");
	       //转账汇款的监听
	        setListener(tvClassSecond, this, TransferMain.class);
	        
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText(title);
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("请选择账户：");
	        this.setListAdapter(createText_Img(value));
	  }
	  protected void onListItemClick(ListView l,View v,int position,long id){
			
			super.onListItemClick(l, v, position, id);
			if(id==0){//首选账户
				Intent payment_intent=new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				payment_intent.putExtra("title",this.title);
				payment_intent.setClass(TransferAccSelect.this, Transfer_inpsd.class);
				TransferAccSelect.this.startActivity(payment_intent);
			}else if(id==1){//其他账户
				Intent elseAcc_intent=new Intent();
				elseAcc_intent.putExtra("title",this.title);
				elseAcc_intent.setClass(TransferAccSelect.this, Transfer_OtherAccSelect.class);
				TransferAccSelect.this.startActivity(elseAcc_intent);
			}
	  }

}
