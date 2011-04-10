package ubank.payment;

import java.util.ArrayList;
import java.util.HashMap;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ManageCost extends GeneralListActivity {
	private String[] name={"平安保险","人寿保险","交通罚款","水费","电费","报纸订阅"};
	private String[] value={"0","1","0","1","0","1"};
	private TextView txt=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
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
}


