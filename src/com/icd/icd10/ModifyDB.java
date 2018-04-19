package com.icd.icd10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModifyDB extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_db);
        Button button_add_chapter=(Button)findViewById(R.id.button_add_chapter);
        button_add_chapter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ModifyDB.this, AddChapter.class);
	            startActivity(i);		
			}
		});
        Button button_add_block=(Button)findViewById(R.id.button_add_block);
        button_add_block.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ModifyDB.this, AddBlock.class);
	            startActivity(i);		
			}
		});
        Button button_add_code=(Button)findViewById(R.id.button_add_code);
        button_add_code.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ModifyDB.this, AddCode.class);
	            startActivity(i);		
			}
		});
        Button button_del_code=(Button)findViewById(R.id.button_del_code);
        button_del_code.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(ModifyDB.this, DelCode.class);
	            startActivity(i);		
			}
		});

	}
	
}
