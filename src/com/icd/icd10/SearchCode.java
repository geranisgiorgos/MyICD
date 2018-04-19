package com.icd.icd10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SearchCode extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_code);
        Button button_search=(Button)findViewById(R.id.button_search);
        final EditText codeView=(EditText)findViewById(R.id.editText1);
        button_search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String code=codeView.getText().toString();
				if(code.length()>0){
					Intent i = new Intent(SearchCode.this, ShowDisease.class);
					i.putExtra("CODE", code);
					startActivity(i);
				}
			}
		});
        Button button_clear=(Button)findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				codeView.setText("");		
			}
		});
        
    }
    
}