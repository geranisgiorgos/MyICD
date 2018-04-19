package com.icd.icd10;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowCodes extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_codes);
        
        DatabaseHelper mydb = new DatabaseHelper(this);
        try{ 
      	  	mydb.createDataBase(); 
        } catch (IOException e){ 
        	throw new Error("Error copying database"); 
        }
        mydb.openDataBase();
		// List of chapters
        ArrayList<String> chapters = new ArrayList<String>();
        String disease = getIntent().getStringExtra("DISEASE");
        String exact = getIntent().getStringExtra("EXACT");
        Cursor c;
        if (exact.equalsIgnoreCase("false"))
            	c=mydb.searchInTitle(disease);
            else
            	c=mydb.searchByDiseaseTitle(disease);
        startManagingCursor(c);
        // Copy diseases from sql results to the array list
        if(c.moveToFirst()){
        	do{
        		chapters.add(c.getString(6)+", chapter"+c.getString(3)+":\n"+c.getString(8));
        	}while(c.moveToNext());
        }
        // Add into ListView
        ListView listResults = (ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,chapters); 
        listResults.setAdapter(adapter);
        
        mydb.close();
	}
}
