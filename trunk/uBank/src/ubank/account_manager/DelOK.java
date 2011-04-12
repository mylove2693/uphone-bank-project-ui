package ubank.account_manager;


import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DelOK extends GeneralActivity {

	private TextView txt=null;
	private Button next_btn=null;
	private String[] value=null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.del_acc_info);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("账户删除>");
	       //监听
	        setListener(tvClassSecond, this, ManagerHome.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("删除账户");
	        /**
	         * 接收上一个Activity穿过来的 值
	         */
	        Intent intent=getIntent();
	        value=intent.getStringArrayExtra("value"); 
	        //设置上面的字
	        txt=(TextView)findViewById(R.id.txt_one).findViewById(R.id.Text_View_20);
	        txt.setText("账户:");
	        txt=(TextView)findViewById(R.id.txt_two).findViewById(R.id.Text_View_20);
	        txt.setText(value[0]);
	        txt=(TextView)findViewById(R.id.txt_three).findViewById(R.id.Text_View_20);
	        txt.setText("账户别名:");
	        txt=(TextView)findViewById(R.id.txt_four).findViewById(R.id.Text_View_20);
	        txt.setText(value[1]);
	        txt=(TextView)findViewById(R.id.txt_five).findViewById(R.id.Text_View_20);
	        txt.setText("账户类型:");
	        txt=(TextView)findViewById(R.id.txt_six).findViewById(R.id.Text_View_20);
	        txt.setText(value[2]);
	        //下一步按钮
	        next_btn=(Button)findViewById(R.id.ok_btn).findViewById(R.id.button);
	        next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					/**
					 * 对话框
					 */
				}
	  });
	}
}


