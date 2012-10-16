package m2.ihm.assistantsms.base_de_donnees;

import java.util.ArrayList;

import resources.SMS;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.List;

public class MaBaseGestion {
	
	private static final int VERSION_BDD = 2;
	
	private static final String TABLE_SMS = "table_sms";
	

	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	
	private static final String COL_MESSAGE = "message";
	private static final int NUM_COL_MESSAGE = 1;
	
	private static final String COL_CONTACT = "contact"; 
	private static final int NUM_COL_CONTACT = 2; 
	
	private static final String COL_LOCALISATION = "localisation";
	private static final int NUM_COL_LOCALISATION = 3;
	
	private static final String COL_DATE = "date";
	private static final int NUM_COL_DATE = 4;
	
	private static final String COL_IS_SENT = "is_sent";
	private static final int NUM_COL_IS_SENT = 5;		
	
	
	private SQLiteDatabase bdd;
	private MaBase maBase;
	
	public MaBaseGestion(Context _context){
		maBase = new MaBase(_context, TABLE_SMS, null, VERSION_BDD);
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
	
	public long insertSMS(SMS _sms){
		ContentValues values = new ContentValues();
		values.put(COL_MESSAGE, _sms.getMessage());
		values.put(COL_CONTACT, _sms.getDestinataire());
		values.put(COL_LOCALISATION, _sms.getLocalisation());
		values.put(COL_DATE, _sms.getDateString());
		values.put(COL_IS_SENT, _sms.getIsSent());
		
		return bdd.insert(TABLE_SMS, null, values);
	}
	
	public int updateSMS(SMS _sms, int _id){
		ContentValues values = new ContentValues();
		values.put(COL_MESSAGE, _sms.getMessage());
		values.put(COL_CONTACT, _sms.getDestinataire());
		values.put(COL_LOCALISATION, _sms.getLocalisation());
		values.put(COL_DATE, _sms.getDateString());
		values.put(COL_IS_SENT, _sms.getIsSent());
		
		return bdd.update(TABLE_SMS, values, COL_ID + "=" + _id, null);
	}
	
	public int removeSMS(int _id){
		return bdd.delete(TABLE_SMS, COL_ID + "=" + _id, null);
	}
	
	/*public SMS getSMS(int _id){
		Cursor c =bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT}, COL_ID + "LIKE \"" + _id +"\"",null,null,null,null); 
	
		if(c.getCount()==0){
			return null;
		}
		
		c.moveToFirst();
		
		SMS sms = new SMS();
		sms.setMessage(c.getString(NUM_COL_MESSAGE));
		sms.setDateString(c.getString(NUM_COL_DATE));
		sms.setDestinataire(c.getString(NUM_COL_CONTACT));
		sms.setLocalisation(c.getString(NUM_COL_LOCALISATION));
		sms.setID(c.getInt(NUM_COL_ID));
		sms.setIsSent(c.getInt(NUM_COL_IS_SENT));
		
		c.close();
		
		return sms;
		
	}*/
	
	public List<SMS> getAllSMS(){
		List<SMS> listeSMS = new ArrayList<SMS>();
		Cursor c = bdd.query(TABLE_SMS, new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT},null,null,null,null,null);
		SMS sms;
		int nbMessage = c.getCount();
		c.moveToFirst();
		for(int i=0;i<nbMessage;i++){
			sms = new SMS();
			sms.setMessage(c.getString(NUM_COL_MESSAGE));
			sms.setDateString(c.getString(NUM_COL_DATE));
			sms.setDestinataire(c.getString(NUM_COL_CONTACT));
			sms.setLocalisation(c.getString(NUM_COL_LOCALISATION));
			sms.setID(c.getInt(NUM_COL_ID));
			sms.setIsSent(c.getInt(NUM_COL_IS_SENT));
			listeSMS.add(sms);
			
		}
		
		return listeSMS;
	}
}
