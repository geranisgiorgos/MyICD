package com.icd.icd10;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCode extends Activity {
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
        setContentView(R.layout.add_code);
        final EditText editText1=(EditText)findViewById(R.id.editText1);
        final EditText editText2=(EditText)findViewById(R.id.editText2);
        final EditText editText3=(EditText)findViewById(R.id.editText3);

        final TextView message=(TextView)findViewById(R.id.messageView);
        
        Button button_clear=(Button)findViewById(R.id.button_clear);
        Button button_add=(Button)findViewById(R.id.button_add);
        
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				editText1.setText("");
				editText2.setText("");
				editText3.setText("");
			}
		});
        
        button_add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String code=editText1.getText().toString();
				String block=editText2.getText().toString();
				String newTitle=editText3.getText().toString();
				if(code.length()>0 && block.length()>0 && newTitle.length()>0){
					boolean existsBlock=mydb.existsBlock(block);
					boolean existsCode=mydb.existsCode(code);					
					if(existsBlock && !existsCode) {
						String chapter=mydb.chapterOfBlock(block);
						mydb.addCode(code, block, newTitle, chapter);
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
						message.setText("Code added");
					} else if (existsCode){
						message.setText("Code "+code+" already exists");	
					} else {
						message.setText("Block not exists");	
					}
						
				}
			}
		});
        mydb.close();
	}
}
