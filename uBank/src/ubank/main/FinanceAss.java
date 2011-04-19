package ubank.main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class FinanceAss extends Activity {
	
	private ImageView bankmain;
	private ImageView bankhelp;
	private GridView gridview = null;
	private Intent intent = null;
	
	//记录登录状态
	public static boolean loginstatus = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gridview);
        
        //gridview中的文字和图标数组        
        Object[] icon = new Object[]{R.drawable.ma_abank,R.drawable.ma_depositerates,R.drawable.ma_loanrates,R.drawable.ma_exchangerates};
        String[] text = new String[]{"手机银行","存款利率","贷款利率","外汇利率"};
        
        List<Map<String, Object>> itemlist = new ArrayList<Map<String,Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        for (int i = 0; i < text.length; i++) {
        	item = new HashMap<String, Object>();
            item.put("imageItem", icon[i]);
            item.put("textItem", text[i]);
            itemlist.add(item);
		}
        
        //实例化一个适配器
        SimpleAdapter adapter = new SimpleAdapter(this, itemlist, R.layout.main_grid_item, new String[]{"imageItem", "textItem"}, new int[]{R.id.image_item, R.id.text_item});
        
        //获得GridView实例
        gridview = (GridView)findViewById(R.id.gv_main);
        
        //将GridView和数据适配器关联
        gridview.setAdapter(adapter);
        
        //为GridView添加监听
        gridview.setOnItemClickListener(new ItemClickListener());
        
        //设置底部选项卡
        bankmain = (ImageView)findViewById(R.id.mainbelow).findViewById(R.id.btnMain);
        bankmain.setImageResource(R.drawable.main_sjyh);
//        bankmain.setImageResource(R.drawable.main_sjyh2);
        bankmain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (loginstatus) {
					//重新载入主页面
					intent = new Intent(FinanceAss.this,BankMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					FinanceAss.this.startActivity(intent);
					
				}else {
					//载入登录按钮
					intent = new Intent(FinanceAss.this,Login.class);
					FinanceAss.this.startActivity(intent);
					
				}
			}
		});
        
        bankhelp = (ImageView)findViewById(R.id.mainbelow).findViewById(R.id.btnHelper);
        bankhelp.setImageResource(R.drawable.main_jrzs);
//        bankhelp.setImageResource(R.drawable.main_jrzs2);
        bankhelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
			}
		});
        
    } 	
	
    class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				if (loginstatus) {
					
					intent = new Intent(FinanceAss.this,BankMain.class);
					startActivity(intent);
					
				}else {
					intent = new Intent(FinanceAss.this,Login.class);
					startActivity(intent);
				}
				break;
			case 1:
				intent = new Intent(FinanceAss.this, DepositRates.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(FinanceAss.this, LoanRates.class);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(FinanceAss.this, ExchangeCalc.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
    	
    }
}