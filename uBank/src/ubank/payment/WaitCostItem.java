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
	private String[] name=null;
	private String[] value=null;
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
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value=null;
			JSONObject jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,EOperation.GET_PAYMENT_INFO, "1","1");
			Map<String,String> map = EHelper.toMap(jsonObj);
			  System.out.println(map.size());
			  value=new String[map.size()];//获取值
			  int i=0;
			  for (Entry<String, String> kv : map.entrySet()) {
				  value[i++]=kv.getValue();
			  }
			  if(value==null){
				  Log.e("--class-WaitCostItem", "id==0 is value is null");
			  }
			intent.putExtra("name", name);
			intent.putExtra("value", value);
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


