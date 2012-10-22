package m2.ihm.assistantsms.base_de_donnees;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class MaBaseSettingsGestion {
	
	private static final int VERSION_BDD = 1;
	
	private static final String TABLE_SETTINGS = "table_settings";
	
	private static final String COL_NAME = "name";
	private static final int NUM_COL_NAME = 0;
	
	private static final String COL_VALUE = "value";
	private static final int NUM_COL_VALUE = 1;
	
	
	private SQLiteDatabase bdd;
	private MaBaseSettings maBase;
	
	public MaBaseSettingsGestion(Context _context){
		maBase = new MaBaseSettings(_context, TABLE_SETTINGS, null, VERSION_BDD);
	}
	
	public void open(){
		bdd = maBase.getWritableDatabase();
	}
	
	public void close(){
		bdd.close();
	}
	
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public boolean isServiceOn(){
		Cursor c = bdd.query(
				TABLE_SETTINGS, 
				new String[] {COL_NAME, COL_VALUE},
				COL_NAME + " LIKE " + "service",
				null,null,null,null);
		
		if(c.getCount()==0){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, "service");
			values.put(COL_VALUE, 1);
			bdd.insert(TABLE_SETTINGS, null, values);
			return true;
		}
		else{
			c.moveToFirst();
			return c.getInt(NUM_COL_VALUE)!=0;
		}
		
	}
	
	public boolean isNotificationOn(){
		Cursor c =bdd.query(
				TABLE_SETTINGS, 
				new String[] {COL_NAME, COL_VALUE}, 
				COL_NAME + " = " + "notification",
				null,null,null,null); 
		
		if(c.getCount()==0){
			ContentValues values = new ContentValues();
			values.put(COL_NAME, "notification");
			values.put(COL_VALUE, 1);
			bdd.insert(TABLE_SETTINGS, null, values);
			return true;
		}
		else{
			c.moveToFirst();
			return c.getInt(NUM_COL_VALUE)!=0;
		}
	}
	
	public int setService(boolean _service){
		ContentValues values = new ContentValues();
		
		if(_service){
			values.put(COL_VALUE, 1);
		}
		else{
			values.put(COL_VALUE, 0);
		}
		
		return bdd.update(TABLE_SETTINGS,values, COL_NAME + " =  service", null);
	}
	
	public int setNotification(boolean _notification){
		ContentValues values = new ContentValues();
		
		if(_notification){
			values.put(COL_VALUE, 1);
		}
		else{
			values.put(COL_VALUE, 0);
		}
		
		return bdd.update(TABLE_SETTINGS,values, COL_NAME + " =  notification", null);
	}	
}
