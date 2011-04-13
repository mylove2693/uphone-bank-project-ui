package ubank.payment;


import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cost extends GeneralActivity {

	private TextView txt=null;
	private Button next_btn=null;
	private String num=null;
	private EditText edit=null;
	private Dialog dialog;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.input_num);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("便捷服务");
	        /**
	         * 接收上一个Activity穿过来的 值
	         */
	        Intent intent=getIntent();
	        num=intent.getStringExtra("num"); 
	        //设置上面的字
	        txt=(TextView)findViewById(R.id.txt_one).findViewById(R.id.Text_View_20);
	        txt.setText("请选择运营商:");
//	        txt=(TextView)findViewById(R.id.spinner_one).findViewById(R.id.Spinner);
//	        txt.setText(accTye);
	        txt=(TextView)findViewById(R.id.txt_two).findViewById(R.id.Text_View_20);
	        txt.setText("请选择充值面额:");
//	        txt=(TextView)findViewById(R.id.spinner_two).findViewById(R.id.Spinner);
//	        txt.setText(money);
	        txt=(TextView)findViewById(R.id.txt_three).findViewById(R.id.Text_View_20);
	        txt.setText("请输入目标"+num+"号");
	        //号码输入框
	        edit=(EditText)findViewById(R.id.edit).findViewById(R.id.et_amt);
	        //下一步按钮
	        next_btn=(Button)findViewById(R.id.ok_btn).findViewById(R.id.button);
	        next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String editStr=edit.getText().toString().trim();
					if (editStr.length() == 0) {//判断文本框中是否输入为空

						
						// 设置对话框的布局
						View view= getLayoutInflater().inflate(
								R.xml.comdialog1, null);
						/**
						 * 设置对话框的样式
						 */
						dialog= new Dialog(Cost.this,R.style.dialog);
						/**
						 * 显示对话框
						 */
						dialog.show();
						// 设置具体对话框布局的宽和高
						LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
						// 将设置好的布局View加到对话框中
						dialog.addContentView(view, params);
						// 设置标题
						((TextView) view.findViewById(R.id.tv_comdlog_title))
								.setText("警告");
						// 设置显示的信息
						((TextView) view.findViewById(R.id.tv_comdlog_con1))
								.setText("号码不能为空");
						Button Ok_btn = (Button) view
								.findViewById(R.id.btn_comdlog_ok);
						Ok_btn.setText("确定");
						Ok_btn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
					}else{
					Intent intent=new Intent();
					String[] name={"项目名称:"," ","缴费金额","收费方:","缴费合同号:"};
					String[] value=new String[5];
					if(num.equals("手机")){
						name[1]="目标手机号:";
						value[0]="手机充值";
						value[1]="110";
						value[2]="10";
						value[3]="中国移动";
						value[4]="st110";
					}else if(num.equals("QQ")){
						name[1]="目标QQ号:";
						value[0]="手机充值";
						value[1]="553018332";
						value[2]="20";
						value[3]="腾讯QQ";
						value[4]="st553018";
						
					}else if(num.equals("网易")){
						name[1]="目标网易号:";
						value[0]="网易充值";
						value[1]="120";
						value[2]="20";
						value[3]="网易";
						value[4]="st3232";
						
					}else{
						name[1]=" ";
						value[0]=" ";
						value[1]=" ";
						value[2]=" ";
						value[3]=" ";
						value[4]=" ";
					}
					intent.putExtra("name", name);
					intent.putExtra("value", value);
					intent.setClass(Cost.this, WaitCost.class);
					Cost.this.startActivity(intent);
				 }
				}
			});
	  }
}


