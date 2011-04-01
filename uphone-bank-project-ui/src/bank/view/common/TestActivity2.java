package bank.view.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TestActivity2 extends Activity{

	private Integer[] mThumbIds = {
            R.drawable.icon, R.drawable.icon,
            R.drawable.button1, R.drawable.icon,
            R.drawable.button2, R.drawable.icon
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2);
		
	}
}
