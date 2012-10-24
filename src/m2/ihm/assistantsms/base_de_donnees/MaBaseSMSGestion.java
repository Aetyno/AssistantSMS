package m2.ihm.assistantsms.base_de_donnees;

import java.sql.Timestamp;
import java.util.ArrayList;

import resources.SMS;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.util.List;

public class MaBaseSMSGestion {
	
	private static final int VERSION_BDD = 1;
	
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
	private MaBaseSMS maBase;
	
	public MaBaseSMSGestion(Context _context){
		maBase = new MaBaseSMS(_context, TABLE_SMS, null, VERSION_BDD);
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
	
	public long insertSMS(String _contact, Timestamp _date, String _localisation, String _message){
		ContentValues values = new ContentValues();
		values.put(COL_MESSAGE, _message);
		values.put(COL_CONTACT, _contact);
		values.put(COL_LOCALISATION, _localisation);
		values.put(COL_IS_SENT, 0);
		if(_date==null){
			values.put(COL_DATE,"null");
		}
		else{
			values.put(COL_DATE, _date.toString());
		}
		
		
		return bdd.insert(TABLE_SMS, null, values);
	}
	
	public int updateSMS(int _id, String _contact, Timestamp _date, String _localisation, String _message, int _is_send){
		ContentValues values = new ContentValues();
		
                values.put(COL_MESSAGE, _message);
		values.put(COL_CONTACT, _contact);
		values.put(COL_LOCALISATION, _localisation);
		values.put(COL_IS_SENT, _is_send);
		if(_date==null){
			values.put(COL_DATE,"null");
		}
		else{
			values.put(COL_DATE, _date.toString());
		}
		
		return bdd.update(TABLE_SMS, values, COL_ID + "=" + _id, null);
	}
	
	public int removeSMS(int _id){
		return bdd.delete(TABLE_SMS, COL_ID + "=" + _id, null);
	}
	
	public SMS getSMS(int _id){
		Cursor c =bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT}, 
				COL_ID + " = " + _id,
				null,null,null,null); 
	
		if(c.getCount()==0){
			return null;
		}
		
		c.moveToFirst();
		
		SMS sms = new SMS();
		sms.setMessage(c.getString(NUM_COL_MESSAGE));
		
		if(c.getString(NUM_COL_DATE).equals("null")){
			sms.setDate(null);
		}
		else{
			sms.setDate(Timestamp.valueOf(c.getString(NUM_COL_DATE)));
		}
		
		
		sms.setDestinataire(c.getString(NUM_COL_CONTACT));
		sms.setLocalisation(c.getString(NUM_COL_LOCALISATION));
		sms.setID(c.getInt(NUM_COL_ID));
		sms.setIsSent(c.getInt(NUM_COL_IS_SENT));
		
		c.close();
		
		return sms;
		
	}
	
	public List<SMS> getAllSMS(){
		List<SMS> listeSMS = new ArrayList<SMS>();
                
		Cursor c = bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT},
				null,null,null,null,null);
                listeSMS = gatherSMS(c);
                c.close();
             	
		return listeSMS;
	}
	
	public List<SMS> getAllSMSPrepared(){
		List<SMS> listeSMS = new ArrayList<SMS>();
                
		Cursor c = bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT},
				COL_IS_SENT + "=" + 0,
				null,null,null,null);		
                listeSMS = gatherSMS(c);		
		c.close();
		
		return listeSMS;
	}
	
	public List<SMS> getAllSMSSent(){
		List<SMS> listeSMS = new ArrayList<SMS>();
                
		Cursor c = bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT}, 
				COL_IS_SENT + "!=" + 0,
                                null,null,null,null);
		listeSMS = gatherSMS(c);              
		c.close();
		
		return listeSMS;
	}
	
	public List<SMS> getAllSMSTimestamp(Timestamp _date){
		List<SMS> listeSMS = new ArrayList<SMS>();
		
		Cursor c = bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT}, 
				COL_DATE + "=" + _date.toString(),
				null, null, null, null);	
		listeSMS=gatherSMS(c);		
		c.close();
		
		return listeSMS;
	}
	
	public List<SMS> getAllSMSLocalisation(String _localisation){
		List<SMS> listeSMS = new ArrayList<SMS>();
		
		Cursor c = bdd.query(
				TABLE_SMS, 
				new String[] {COL_ID, COL_MESSAGE, COL_CONTACT, COL_LOCALISATION, COL_DATE, COL_IS_SENT}, 
				COL_LOCALISATION + "=" + _localisation,
				null, null, null, null);
		listeSMS = gatherSMS(c);
		c.close();
		
		return listeSMS;
	}

        private List<SMS> gatherSMS(Cursor _c){
            List<SMS> listeSMS = new ArrayList<SMS>();
            
            if(_c.getCount()!=0){
                    SMS sms = new SMS();
                    _c.moveToLast();
                    do{
                        sms = new SMS();
                        
			sms.setMessage(_c.getString(NUM_COL_MESSAGE));
			if(_c.getString(NUM_COL_DATE).equals("null")){
				sms.setDate(null);
			}
			else{
				sms.setDate(Timestamp.valueOf(_c.getString(NUM_COL_DATE)));
			}
			sms.setDestinataire(_c.getString(NUM_COL_CONTACT));
			sms.setLocalisation(_c.getString(NUM_COL_LOCALISATION));
			sms.setID(_c.getInt(NUM_COL_ID));
			sms.setIsSent(_c.getInt(NUM_COL_IS_SENT));
			listeSMS.add(sms);
                    }
                    while(_c.moveToNext());
                }
            return listeSMS;
        }
}
