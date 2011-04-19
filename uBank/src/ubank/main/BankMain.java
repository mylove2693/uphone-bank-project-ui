package ubank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ubank.account_manager.AccountBind;
import ubank.account_manager.ManagerHome;
import ubank.account_query.AccountQueryType;
import ubank.credit.CreditCardMain;
import ubank.payment.AllPaymentSer;
import ubank.transfer.TransferMain;
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

public class BankMain extends Activity {
	
	private ImageView bankmain;
	private ImageView bankhelp;
	private GridView gridview = null;
	private Intent intent = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gridview);
        
        //gridview中的文字和图标数组       
        Object[] icon = new Object[]{R.drawable.ma2_actmanager,R.drawable.ma2_actactivate,R.drawable.ma2_actquery,R.drawable.ma2_transfermoney,R.drawable.ma2_payment,R.drawable.ma2_credit};
        String[] text = new String[]{"账户管理","账户绑定","账户查询","转账汇款","自助缴费","信用卡"};
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
//        bankmain.setImageResource(R.drawable.main_sjyh);
        bankmain.setImageResource(R.drawable.main_sjyh2);
        bankmain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        bankhelp = (ImageView)findViewById(R.id.mainbelow).findViewById(R.id.btnHelper);
//        bankhelp.setImageResource(R.drawable.main_jrzs);
        bankhelp.setImageResource(R.drawable.main_jrzs2);
        bankhelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				intent = new Intent(BankMain.this,FinanceAss.class);
				intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				BankMain.this.startActivity(intent);
				
			}
		});
    } 
    class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				intent = new Intent(BankMain.this,ManagerHome.class);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(BankMain.this, AccountBind.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(BankMain.this, AccountQueryType.class);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(BankMain.this, TransferMain.class);
				startActivity(intent);
				break;
			case 4:
				intent = new Intent(BankMain.this, AllPaymentSer.class);
				startActivity(intent);
				break;
			case 5:
				intent = new Intent(BankMain.this, CreditCardMain.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
    }
}