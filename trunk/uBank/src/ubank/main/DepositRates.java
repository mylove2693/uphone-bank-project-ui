package ubank.main;

import ubank.base.*;

import android.os.Bundle;

public class DepositRates extends GeneralActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_login);
        addLayout(R.layout.main_deposit);
    }
}