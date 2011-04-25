package ubank.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralFinanceActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.webservice.ConnectWs;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ExchangeCalc extends GeneralFinanceActivity{
	
	private EditText InputAmt;
	private Spinner SpSource;
	private Spinner SpDestinations;
	private Button BtnCalc;
	
	private String amt;
	private String calcamt;
	private String currencySource;
	private String currencyDestinations;
	
	private List<String> MTpye = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	//private Intent intent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.main_exchange);
        
        //设置导航栏和监听
        tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("金融助手>");
		setListener(tvClassFirst, this, FinanceAss.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("汇率计算");
        
		//从服务器读取所有币种
		loaderData();
		
		adapter = new ArrayAdapter<String>(this,R.xml.spinner_item,MTpye);
		
        //选择所要转换的原始币种
		SpSource = (Spinner)findViewById(R.id.sourceCurrencySpinner);
		SpSource.setAdapter(adapter);
		SpSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currencySource = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//选择所要转换的目标币种
		SpDestinations = (Spinner)findViewById(R.id.delatinCurrencySpinner);
		SpDestinations.setAdapter(adapter);
		SpDestinations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currencyDestinations = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//计算汇率
		BtnCalc = (Button)findViewById(R.id.currencyConCulate);
		BtnCalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputAmt = (EditText)findViewById(R.id.currencyInputEdit);
				amt = InputAmt.getText().toString();
				if(Double.parseDouble(amt)>0){
				if (EHelper.hasInternet(ExchangeCalc.this)) {
				try {
					//服务器计算转换后的结果
					JSONObject json = ConnectWs.connect(ExchangeCalc.this, EAccType.NULL,EOperation.GET_EXCHANGE_RESULT,amt,currencySource,currencyDestinations);
					System.out.println(json.toString());
					calcamt = EHelper.toStr(json);
					
					//计算结果用Dialog显示
					
					MyDialogOne dialog = new MyDialogOne(ExchangeCalc.this,R.style.dialog);
					dialog.setTitleAndInfo("汇率计算", "\n"+amt+currencySource+"兑换为：\n"+calcamt+currencyDestinations);
					//dialog.Listener(intent, ExchangeCalc.this);
					dialog.show();
					
				} catch (IOException e) {
					Toast.makeText(ExchangeCalc.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					finish();
					e.printStackTrace();
				}
				
				}else {
					Toast.makeText(ExchangeCalc.this, "没有连接网络", Toast.LENGTH_SHORT).show();
					finish();
				}
			}else{
				MyDialogOne dialog = new MyDialogOne(ExchangeCalc.this,R.style.dialog);
				dialog.setTitleAndInfo("输入错误", "\n\n转换金额必须大于零！");
				//dialog.Listener(intent, ExchangeCalc.this);
				dialog.show();
			}
			}
		});
    }
    
    
    //当不再需要时finish该页面
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		ExchangeCalc.this.finish();
	}
	
    //从服务器读取所有币种
    private void loaderData(){
		if (EHelper.hasInternet(this)) {
		try {
			//从服务器取出所有币种
			JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_MONEY_TYPE,"");
			//将JSON数据转换为MAP型
			MTpye = EHelper.toList(json);
			
		} catch (IOException e) {
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
		
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
