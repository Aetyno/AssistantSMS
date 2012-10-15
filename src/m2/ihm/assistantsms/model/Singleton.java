package m2.ihm.assistantsms.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;

public class Singleton extends Activity{
	private static Model model = null;
	
	private Singleton(){
	}
	
	public static Model getModel(){
		if(model==null){
		}
		return model;
	}
	
	
	/*
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
	*/
	
	/*public static void save() throws IOException{
		FileOutputStream fos = openFileOutput("assistant_sms.sav", Context.MODE_PRIVATE);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(model);
		os.close();
	}
	
	public static void restore() throws IOException, ClassNotFoundException{
		FileInputStream fis = openFileInput("assistant_sms.sav");
		ObjectInputStream is = new ObjectInputStream(fis);
		model = (Model)is.readObject();
		is.close();
	}*/
}
