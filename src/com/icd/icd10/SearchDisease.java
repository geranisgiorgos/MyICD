package com.icd.icd10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class SearchDisease extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_disease);
        final CheckBox exact=(CheckBox)findViewById(R.id.checkExact);
        
        Button button_search=(Button)findViewById(R.id.button_search);
        final EditText diseaseView=(EditText)findViewById(R.id.editText1);
        button_search.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String disease=diseaseView.getText().toString();
				Intent i = new Intent(SearchDisease.this, ShowCodes.class);
				// pass extra parameters
				if (exact.isChecked())
					i.putExtra("EXACT", "true");
				else
					i.putExtra("EXACT", "false");
				
				i.putExtra("DISEASE", disease);
	            startActivity(i);		
			}
		});
        Button button_clear=(Button)findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				diseaseView.setText("");		
			}
		});
    }
    
}