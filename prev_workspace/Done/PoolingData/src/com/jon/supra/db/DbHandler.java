package com.jon.supra.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper{
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "supra";
    private String URL_UPDATE = "http://10.0.2.2/supra_api/get_update.php";
    ServiceHandler jsonParser = new ServiceHandler();
    String json = jsonParser.makeServiceCall(URL_UPDATE, ServiceHandler.GET, null);
 
    // Contacts table name
    private static final String TABLE_NEWS = "news";
    private static final String TABLE_LOST = "lostitem";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIP = "descrip";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";
    
    ArrayList<HashMap<String, String>> dataList;

	public DbHandler(Context context, String news,
			int version) {
		super(context, news, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_NEWS_TABLE = "CREATE TABLE " + TABLE_NEWS + "("
                + KEY_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY," + KEY_TITLE + " VARCHAR,"
                + KEY_DESCRIP + " VARCHAR" + KEY_CREATED_AT + " VARCHAR"+ KEY_UPDATED_AT+ " VARCHAR" +  ")";
        db.execSQL(CREATE_NEWS_TABLE);
        
        String CREATE_LOSTITEM_TABLE = "CREATE TABLE " + TABLE_NEWS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " VARCHAR,"
                + KEY_DESCRIP + " VARCHAR" + KEY_CREATED_AT + " VARCHAR"+ KEY_UPDATED_AT+ " VARCHAR" +  ")";
        db.execSQL(CREATE_LOSTITEM_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOST);
		
	}
	void addUpdata(Updata updata){
		
		List<Updata> dataList = new ArrayList<Updata>();
		//dataList = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase db = this.getWritableDatabase();
		 
        ContentValues values = new ContentValues();
        
		
		Log.e("Response: ", "> " + json);

		if (json != null) {
			try {
				JSONObject jsonObj = new JSONObject(json);
				if (jsonObj != null) {
					JSONArray updates = jsonObj
							.getJSONArray("updates");						

					for (int i = 0; i < updates.length(); i++) {
						JSONObject catObj = (JSONObject) updates.get(i);
					
						// adding each child node to HashMap key => value
						values.put("id", String.valueOf(catObj.getInt("id")));
						values.put("title", catObj.getString("title"));
						values.put("descrip",catObj.getString("descrip"));
						values.put("created_at",catObj.getString("created_at"));
						values.put("updated_at",catObj.getString("updated_at"));
						
						db.insert(TABLE_NEWS, null, values);
						db.close();
						
					}
					
					
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
		
        
     // Inserting Row
        db.insert(TABLE_NEWS, null, values);
        //db.insert(TABLE_LOST, null, values);
        db.close(); // Closing database connection
	}
	 // Getting All Contacts
    public List<Updata> getAllUpdates() {
    	
        List<Updata> dataList1 = new ArrayList<Updata>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NEWS;
        //String selectQuery1 = "SELECT  * FROM " + TABLE_LOST;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
       /* // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                //contactList.add(contact);
            } while (cursor.moveToNext());
        }*/
        
        Updata updata = new Updata(0, selectQuery, selectQuery, selectQuery, selectQuery);
        updata.setId(Integer.parseInt(cursor.getString(0)));
        updata.setTitle(cursor.getString(1));
        updata.setDescrip(cursor.getString(2));
        updata.setCreated_at(cursor.getString(3));
        updata.setUpdated_at(cursor.getString(4));
        dataList1.add(updata);
        
        // return contact list
        return dataList1;
    }

	public void add(ArrayList<HashMap<String, String>> dataList) {
		
		
	}

}
