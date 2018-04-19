package com.icd.icd10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ICD10 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icd10);
        Button button_search_disease=(Button)findViewById(R.id.button_search_disease);
        Button button_search_code=(Button)findViewById(R.id.button_search_code);
        Button button_chapters=(Button)findViewById(R.id.button_show_chapters);
        Button button_blocks=(Button)findViewById(R.id.button_show_blocks);
        Button button_about=(Button)findViewById(R.id.button_about);
        Button button_modify=(Button)findViewById(R.id.button_modify);
        button_about.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, About.class);
	            startActivity(i);		
			}
		});
        
        button_search_disease.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, SearchDisease.class);
	            startActivity(i);		
			}
		});

        button_search_code.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, SearchCode.class);
	            startActivity(i);		
			}
		});
        button_chapters.setOnClickListener(new View.OnClickListener() {	

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, ShowChapters.class);
	            startActivity(i);		
			}
		});

        button_blocks.setOnClickListener(new View.OnClickListener() {	

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, ShowBlocks.class);
	            startActivity(i);		
			}
		});
        button_modify.setOnClickListener(new View.OnClickListener() {	

			public void onClick(View v) {
				Intent i = new Intent(ICD10.this, ModifyDB.class);
	            startActivity(i);		
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_icd10, menu);
        return true;
    }

    
}
