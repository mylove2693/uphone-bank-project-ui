package ubank.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ubank.base.GeneralListActivity;
import ubank.main.R;
/**
 * 转账之前的详细信息显示
 */
public class Transfer_information extends GeneralListActivity{	
	//各项名字数组
	String[] s=null;
	//对应的数据数组
	String[] data=null;
	String title=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        Intent up_intent=getIntent();
	        //获得传过来的导航栏标题
	        title=up_intent.getStringExtra("title");
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("转账汇款>");
	        //转账汇款的监听
	        setListener(tvClassSecond, this, TransferMain.class);
	        
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText(title);
	        
	    //为list添加数据
	        s=new String[]{"账户：","类型：","余额："};
	        data=new String[]{"532434","活期存储","100,000"}; 
	        this.setListAdapter(createText_Text(s, data));
	    //添加两个按钮    
	        addLayoutBlow(R.layout.transfer_information);
	        
	       Button Re_election=(Button)findViewById(R.id.Re_election_acc).findViewById(R.id.button);
	       Re_election.setTextSize(18);
	       Re_election.setText("重选账户");
	       
	       Button Continue_transfer=(Button)findViewById(R.id.Continue_transfer).findViewById(R.id.button);
	       Continue_transfer.setTextSize(18);
	       Continue_transfer.setText("继续转账");
	       
	       //为重选账户加监听
	       Re_election.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent=new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				payment_intent.putExtra("title",Transfer_information.this.title);
				payment_intent.setClass(Transfer_information.this, TransferAccSelect.class);
				Transfer_information.this.startActivity(payment_intent);
				
			}
		});
	       //为继续转账加监听
	       Continue_transfer.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent=new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				payment_intent.putExtra("title",Transfer_information.this.title);
				payment_intent.setClass(Transfer_information.this, TransferPhToSignedAcc.class);
				Transfer_information.this.startActivity(payment_intent);
				
			}
		});
	        
	  }

}
