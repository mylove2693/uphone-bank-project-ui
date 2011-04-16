package ubank.payment;

import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class WaitCostItem extends GeneralListActivity {
	private String[] name=null;//上一个界面传来的费用名称
	private String[] nextName={"项目名称:","缴费合同号:","缴费金额:","收费方:","缴费期限:"};//下一个界面要显示的名称
	private String[] value=null;
	private Intent intent;
	private String[] valueDB;//服务端获得数据需发送下去
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        initializeData();// 初始化数据
	        setTile();//设置导航栏
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	  
	  // 初始化数据
	  private void initializeData(){
		  Intent intent=getIntent();
		  name=intent.getStringArrayExtra("name");
		  value=intent.getStringArrayExtra("value");
		  if(name==null||value==null){
			   String[] name1={"水费","房租费","煤气费","电费"};
			   String[] value1={"30.00元","200.00元","150.00元","80.00元"};
			   name=name1;
			   value=value1;
		  }
	  }
	  //设置导航栏
	  private void setTile(){
		   tvClassFirst.setVisibility(View.VISIBLE);
	      //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("待缴费项目");
	  }
	  
	  //每一项进行监听
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//三月份水费
			intent=new Intent();
			
			
			//从服务器上取数据
			/**
			 * 参数为
			 * <userid>1</userid>
		     *<id>1</id>
		     *<name>水费</name>
		     *<dunum>s323454</dunum>
			 */
			JSONObject jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,EOperation.GET_PAYMENT_INFO, "1","1");
			Map<String,String> map = EHelper.toMap(jsonObj);
			valueDB=new String[map.size()];//获取值
			  int i=0;
			  for (Entry<String, String> kv : map.entrySet()) {
				  valueDB[i++]=kv.getValue();
			  }
			  if(valueDB==null){
				  String[] value1={"水费","s323454","30.00","无锡自来水公司","2011-7-12"};
				  valueDB=value1;
				  Log.e("--class-WaitCostItem", "id==0 is value is null");
			  }
			  /**
			   * 创建 Bundle对象包装"paymoney"
			   */
			Bundle bubdle=new Bundle();
			bubdle.putString("payname",valueDB[0]);//要交费的名称
			bubdle.putString("paynum",valueDB[1]);//要交费的和同号
			bubdle.putString("paymoney",valueDB[2]);//要交费的金额
			bubdle.putString("payaddress",valueDB[3]);//收费方
			intent.putExtras(bubdle);
			intent.putExtra("name", nextName);
			intent.putExtra("value", valueDB);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}else if(id==1){//三月份电费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份电费","100.00元","无锡电力公司","s342","2011-07-10"};
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}else if(id==2){//三月份煤气费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
		    String[] value={"三月份煤气费","78.00元","无锡能源公司","s42526","2011-07-08"};
		    intent.putExtra("name", name);
		    intent.putExtra("value", value);
		    intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}
		else if(id==3){//三月份房租费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份房租费","200.00元","无锡市房产公司","s34561","2011-07-29"};
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}
	}
}


