package com.icd.icd10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Button button_icd=(Button)findViewById(R.id.button_icd);
        
        button_icd.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd.txt");
	            startActivity(i);		
			}
		});
        Button button_icd9=(Button)findViewById(R.id.button_icd9);
        
        button_icd9.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd9.txt");
	            startActivity(i);		
			}
		});
        Button button_icd10=(Button)findViewById(R.id.button_icd10);
        
        button_icd10.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd10.txt");
	            startActivity(i);		
			}
		});
        
        Button button_icd10_cm=(Button)findViewById(R.id.button_icd10_cm);
        
        button_icd10_cm.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd10-cm.txt");
	            startActivity(i);		
			}
		});
        
        Button button_icd10_pc=(Button)findViewById(R.id.button_icd10_pc);
        
        button_icd10_pc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd10-pc.txt");
	            startActivity(i);		
			}
		});
        Button button_icd10_ca=(Button)findViewById(R.id.button_icd10_ca);
        
        button_icd10_ca.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd10-ca.txt");
	            startActivity(i);		
			}
		});
        Button button_icd11=(Button)findViewById(R.id.button_icd11);
        
        button_icd11.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(About.this, AboutICD.class);
				i.putExtra("FILENAME", "icd11.txt");
	            startActivity(i);		
			}
		});

	}
}
