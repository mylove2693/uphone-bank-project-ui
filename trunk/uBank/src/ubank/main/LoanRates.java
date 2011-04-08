package ubank.main;

import ubank.base.*;
import android.app.Activity;
import android.os.Bundle;

public class LoanRates extends GeneralActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.main_loan);
    }
}