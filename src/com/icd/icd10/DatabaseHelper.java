package com.icd.icd10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {
	// database path
	private static String DB_PATH = "/data/data/com.icd.icd10/databases/";
	// database name
	private static String DB_NAME = "icd_10.jpg";
	// database version
	private static final int DB_VERSION = 1;
	// name of the tables of the database
	private static final String TABLE_CHAPTERS="CHAPTERS";
	private static final String TABLE_BLOCKS="BLOCKS";
	private static final String TABLE_CODES="CODES";

	
	
	private SQLiteDatabase myDataBase; 
	 
	private final Context myContext;

	// Constructor
	DatabaseHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
		myContext=context;
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		 
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
         
	}
		 
	// Create the database if it does not exist
	public void createDataBase() throws IOException{
		boolean dbExists = checkDB();
		 
		if(!dbExists){
			this.getReadableDatabase();
		     try {
		    	copyDB();
		    } catch (IOException e) {
		        throw new Error("Error copying database");
		    }
		}
	}
	
	// Check if the DataBase exists in folder /data/data/com.icd.icd10/databases
	private boolean checkDB(){
		SQLiteDatabase db = null;
		try{
			String path = DB_PATH + DB_NAME;
		    db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

		}catch(SQLiteException e){
		    // Database does not exist
		}
		 
		if(db != null)
			db.close();
		if(db==null) 
		    return false;
		else 
			return true;
		}
		    
	// Copy the DataBase from folder assets, if needed
	private void copyDB() throws IOException{
		// open source file
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		// path
		String outFileName = DB_PATH + DB_NAME; 
		OutputStream myOutput = new FileOutputStream(outFileName);
		// copy
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		//Close streams
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}
		    
	public void openDataBase() throws SQLiteException{		    	 
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 	}
		    	 
	@Override
	public synchronized void close() {	    	
		if(myDataBase != null)
		myDataBase.close();  	 
		super.close();    	 
	}
		    	
	// Get the chapters
	public Cursor getChapters() {
		// create SQL command
		String sql="select * from "+TABLE_CHAPTERS;
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}
	//Get the blocks
	public Cursor getBlocks() {
		// create SQL command
		String sql="select * from "+TABLE_BLOCKS;
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}	    	
	//Search by Code
	public Cursor searchByCode(String code) {
		// create SQL command
		String sql="select * from "+TABLE_CODES+" WHERE code_no_asterisc  like \'"+code+"\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}
	//Search by Disease Title
	public Cursor searchByDiseaseTitle(String title) {
		// create SQL command
		String sql="select * from "+TABLE_CODES+" WHERE title like \'"+title+"\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}
	
	//Search for Codes that contain a word or phrase
	public Cursor searchInTitle(String title) {
		// create SQL command
		String sql="select * from "+TABLE_CODES+" WHERE title like \'"+title+"%\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}
	
	// Check if a chapter number exists
	boolean existsChapter(String chapter){
		Cursor cur=searchByChapterNo(chapter);
		if(cur.moveToFirst())
			return true;
		return false;
	}
	
	// Check if a code exists
	boolean existsCode(String code){
		Cursor cur=searchByCode(code);
		if(cur.moveToFirst())
			return true;
		return false;
	}
	
	//Check if a  block exists (its starting code)
	public boolean existsBlock(String code) {
		this.openDataBase();
		// create SQL command
		String sql="select * from "+TABLE_BLOCKS+" WHERE \"from\" like \'"+code+"\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		if(cur.moveToFirst())
			return true;
		return false;
	}	    	
	
	//Find the chapter of a block
	public String chapterOfBlock(String code) {
		this.openDataBase();
		// create SQL command
		String sql="select chapter from "+TABLE_BLOCKS+" WHERE \"from\" = \'"+code+"\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		if(cur.moveToFirst())
			return cur.getString(0);
		return "";
	}	  
	
	//Search by Chapter No
	public Cursor searchByChapterNo(String chapter) {
		this.openDataBase();
		// create SQL command
		String sql="select * from "+TABLE_CHAPTERS+" WHERE chapter_no = \'"+chapter+"\'";
		// run SQL command
		Cursor cur=this.myDataBase.rawQuery(sql,new String[]{});
		return cur;
	}
	
	//Add a new chapter
	public void addChapter(String chapter, String title) {
			this.openDataBase();
			// create SQL command
			String sql="insert into "+TABLE_CHAPTERS+
					"(chapter_no, title) values  (\'"+chapter+"\',\'"+title+"\')";
			// run SQL command
			this.myDataBase.execSQL(sql);
			this.close();
			
		}
	//Add a new block
	public void addBlock(String from, String to, String chapter, String title) {
		// create SQL command
		String sql="insert into "+TABLE_BLOCKS+
						"('from', 'to', chapter, title) values  (\'"+from+"\',\'"+to+"\',\'"+chapter+"\',\'"+title+"\')";
		// run SQL command
		this.myDataBase.execSQL(sql);
		this.close();		
	}
	
	//Add a new block
		public void addCode(String code, String block, String title, String chapter) {
			// create SQL command
			String sql="insert into "+TABLE_CODES+
							"(code_no_dagger, code_no_asterisc, code_no_dot, block_from, title, chapter, level,terminal,type_of_node) values  (\'"+code+"\',\'"+code+"\',\'"+code+"\',\'"+block+"\',\'"+title+"\',\'"+chapter+"\',\'5\',\'T\',\'X\')";
			// run SQL command
			this.myDataBase.execSQL(sql);
			this.close();					
		}
		
		//Delete by Code
		public void deleteCode(String code) {
			// create SQL command
			String sql="delete  from "+TABLE_CODES+" WHERE code_no_asterisc  like \'"+code+"\'";
			// run SQL command
			this.myDataBase.execSQL(sql);
		}
}

