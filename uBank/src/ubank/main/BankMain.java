package ubank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class BankMain extends Activity {
	private GridView gridview = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gridview);
        
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
    } 
}