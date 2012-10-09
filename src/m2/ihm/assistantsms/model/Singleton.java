package m2.ihm.assistantsms.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

public class Singleton extends Activity{
	private static Model model = null;
	
	private Singleton() {
	}
	
	public static Model getModel(){
		if(model==null){
			model = new Model();
		}
		return model;
	}
	
	public void saveModel() throws IOException{
		FileOutputStream fos = openFileOutput("assistant_sms.sav", Context.MODE_PRIVATE);
		fos.write(model.toString().getBytes());
		fos.close();
	}
	
	public void restoreModel() throws IOException{
		FileInputStream fis = openFileInput("assistant_sms.sav");
		fis.read();
		fis.close();
	}
}
