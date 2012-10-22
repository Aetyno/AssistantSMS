package m2.ihm.assistantsms.base_de_donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSettings extends SQLiteOpenHelper{

	private static final String TABLE_SETTINGS = "table_settings";
	
	private static final String COL_NAME = "name";
	private static final String COL_VALUE = "value";
	
	private static final String CREATE_TABLE_SETTINGS =
			"CREATE TABLE " + TABLE_SETTINGS + " ( "	+ COL_NAME + " INTEGER, "
														+ COL_VALUE + " INTEGER);";
	
	
	public MaBaseSettings(Context _context, String _name, CursorFactory _factory, int _version){
		super(_context, _name, _factory, _version);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SETTINGS);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(" DROP TABLE " + TABLE_SETTINGS + ";");
		onCreate(db);
	}
}
