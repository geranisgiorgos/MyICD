package com.icd.icd10;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddBlock extends Activity {
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
        setContentView(R.layout.add_block);
        final EditText editText1=(EditText)findViewById(R.id.editText1);
        final EditText editText2=(EditText)findViewById(R.id.editText2);
        final EditText editText3=(EditText)findViewById(R.id.editText3);
        final EditText editText4=(EditText)findViewById(R.id.editText4);
        final TextView message=(TextView)findViewById(R.id.messageView);
        
        Button button_clear=(Button)findViewById(R.id.button_clear);
        Button button_add=(Button)findViewById(R.id.button_add);
        
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				editText1.setText("");
				editText2.setText("");
				editText3.setText("");
				editText4.setText("");
			}
		});
        
        button_add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String from=editText1.getText().toString();
				String to=editText2.getText().toString();
				String chapter=editText3.getText().toString();
				String newTitle=editText4.getText().toString();
				if(from.length()>0 && to.length()>0 && chapter.length()>0 && newTitle.length()>0){
					boolean existsChapter=mydb.existsChapter(chapter);	
					if(existsChapter) {
						mydb.addBlock(from, to, chapter, newTitle);
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
						editText4.setText("");
						message.setText("Block added");
					} else {
						message.setText("Chapter "+chapter+" not exists");	
					} 
						
				}
			}
		});
        mydb.close();
	}
}
