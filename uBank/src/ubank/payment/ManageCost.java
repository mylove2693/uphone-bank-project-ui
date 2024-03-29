package ubank.payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ManageCost extends GeneralListActivity {
	private String[] name={"平安保险","人寿保险","交通罚款","水费","电费","报纸订阅"};
	private String[] statevalue={"state0","state1","state2","state3","state4","state5"};
	private String[] value;
	private JSONObject jsonObj;
//	static int [] yy={0,0,0,1,1,1};
	private TextView txt=null;
	ImageView img;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //获取上一个activity的状态值
	        Intent intent=getIntent();
	        value=intent.getStringArrayExtra("state");
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("缴费项目管理");
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("已开通项目:");
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	//文字--文字--图片 的适配器
	    public SimpleAdapter createText_Text_Img(String[] name,String[] value){
	    	ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();
	    	HashMap<String, Object> paylist1 = null;
	    	for (int i = 0; i < name.length; i++) {
				paylist1 = new HashMap<String, Object>();
				paylist1.put("text1",name[i]);
				if(value[i].equals("1")){
					paylist1.put("img2", R.drawable.item_enable);
				}else{
					paylist1.put("img2", R.drawable.item_stop);
				}
				mainlist.add(paylist1);
			}
			adapter = new SimpleAdapter(this, mainlist,
					R.xml.payment_manage_list, new String[] { "text1", "img2"}, new int[] { R.id.left_txt,
							R.id.only_img});
			return adapter;
	    }
	    protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			if (id == 0) {//1
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(0);
			}
			else if(id==1){//2
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(1);
			}
			else if (id == 2) {//3
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(2);
	        
			}
			else if(id==3){//4
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(3);
			}
			else if (id == 4) {//5
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(4);
	        
			}
			else if(id==5){//6
				img=(ImageView)v.findViewById(R.id.only_img);
				changeState(5);
			}
		}
		private void changeState(int tag){
			if(value[tag].equals("0")){//"0表示停用"
				ManageCost.this.img.setImageResource(R.drawable.item_enable);
				value[tag]="1";
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.UPDATE_PAYMENT_STATE,statevalue[tag].trim(),value[tag]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(value[tag].equals("1")){//"1表示启用"
				ManageCost.this.img.setImageResource(R.drawable.item_stop);
				value[tag]="0";
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.UPDATE_PAYMENT_STATE,statevalue[tag].trim(),value[tag]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}


