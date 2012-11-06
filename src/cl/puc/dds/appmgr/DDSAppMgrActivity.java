package cl.puc.dds.appmgr;

import cl.puc.dds.R;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class DDSAppMgrActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
    }    
}