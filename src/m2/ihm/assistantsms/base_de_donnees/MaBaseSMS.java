package m2.ihm.assistantsms.base_de_donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSMS extends SQLiteOpenHelper{

	private static final String TABLE_SMS = "table_sms";
	
	private static final String COL_ID = "id";
	private static final String COL_MESSAGE = "message";
	private static final String COL_CONTACT = "contact"; 
	private static final String COL_LOCALISATION = "localisation";
	private static final String COL_DATE = "date";
	private static final String COL_IS_SENT = "is_sent";
	
	private static final String CREATE_TABLE_SMS = 
			" CREATE TABLE " + TABLE_SMS + " ( "	+ COL_ID + 				" INTEGER PRIMARY KEY AUTOINCREMENT, " 
													+ COL_CONTACT + 		" TEXT, " 
													+ COL_LOCALISATION + 	" TEXT, " 
													+ COL_DATE + 			" TIMESTAMP, " 
													+ COL_MESSAGE + 		" TEXT, " 
													+ COL_IS_SENT + 		" INTEGER ) ; ";
	
	private static final String TABLE_SETTINGS = "table_settings";
	
	private static final String COL_NAME = "name";
	private static final String COL_VALUE = "value";
	
	private static final String CREATE_TABLE_SETTINGS =
			"CREATE TABLE " + TABLE_SETTINGS + " ( "	+ COL_NAME + " STRING, "
														+ COL_VALUE + " BOOLEAN);";
	
	
	
	public MaBaseSMS(Context _context, String _name, CursorFactory _factory, int _version){
		super(_context, _name, _factory, _version);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SMS);
		db.execSQL(CREATE_TABLE_SETTINGS);
		
	}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(" DROP TABLE " + TABLE_SMS + ";");
		onCreate(db);
	}
}
