package com.icd.icd10;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddChapter extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		final DatabaseHelper mydb = new DatabaseHelper(this);
	    try{ 
	      	  	mydb.createDataBase(); 
	    } catch (IOException e){ 
	        	throw new Error("Error copying database"); 
	    }
	    mydb.openDataBase();    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chapter);
        final EditText chapterNo=(EditText)findViewById(R.id.editText1);
        final EditText title=(EditText)findViewById(R.id.editText2);
        final TextView message=(TextView)findViewById(R.id.messageView);
        
        Button button_clear=(Button)findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				chapterNo.setText("");
				title.setText("");
			}
		});
        Button button_add=(Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String chapter=chapterNo.getText().toString();
				String newTitle=title.getText().toString();
				if(chapter.length()>0 && newTitle.length()>0){
					boolean exists=mydb.existsChapter(chapter);
					if(exists) {
						message.setText("Chapter "+chapter+" already exists");
						chapterNo.setText("");
						title.setText("");
					} else {
						mydb.addChapter(chapter, newTitle);
						chapterNo.setText("");
						title.setText("");
						message.setText("New chapter inserted");	
					}
				}
			}
		});
        mydb.close();
	}
}
