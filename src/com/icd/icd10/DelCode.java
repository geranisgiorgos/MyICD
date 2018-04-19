package com.icd.icd10;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DelCode extends Activity {

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
        setContentView(R.layout.del_code);
        Button button_del=(Button)findViewById(R.id.button_del);
        final TextView message=(TextView)findViewById(R.id.message);
        final EditText codeView=(EditText)findViewById(R.id.editText1);
        button_del.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String code=codeView.getText().toString();
				if(code.length()>0){
					if(mydb.existsCode(code)){
						mydb.deleteCode(code);
						message.setText("Code "+code+" deleted");
					}
					else {
						message.setText("Code "+code+" not exists");
					}
				}
			}
		});
        Button button_clear=(Button)findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				codeView.setText("");		
			}
		});
        mydb.close();
    } 
}