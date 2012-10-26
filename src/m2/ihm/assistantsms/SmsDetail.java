package m2.ihm.assistantsms;

import m2.ihm.assistantsms.base_de_donnees.MaBaseSMSGestion;
import resources.SMS;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SmsDetail extends Activity {

	private SMS sms;
	private MaBaseSMSGestion maBaseGestion;
	private TextView textView2;
	private TextView textView4;
	private TextView textView5;
	private TextView textView7;
	
	private static int id;

        
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_detail);
        maBaseGestion = new MaBaseSMSGestion(this);
        
        textView2 = (TextView) findViewById(R.id.textView2);
    	textView4 = (TextView) findViewById(R.id.textView4);
    	textView5 = (TextView) findViewById(R.id.textView5);
    	textView7 = (TextView) findViewById(R.id.textView7);
    	
        
        
        maBaseGestion.open();
        
        sms = maBaseGestion.getSMS(id);
        
        maBaseGestion.close();
        
        textView2.setText(sms.getDestinataire());
        
        if(sms.getDate()==null){
        	textView4.setVisibility(TextView.INVISIBLE);
        	textView4.setPadding(-100, -100, -100, -100);
        }
        else{
        	textView4.setText(sms.getDate().toString());
        }
        
        if(sms.getLocalisation().equals("null")){
        	textView5.setVisibility(TextView.INVISIBLE);
        	textView5.setPadding(-100, -100, -100, -100);
        }
        else{
        	textView5.setText(sms.getLocalisation());
        }
        
        textView7.setText(sms.getMessage());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sms_detail, menu);
        
        
        
        
        return true;
    }
    
    public static void setId(int _id){
    	id=_id;
    }
}
