package com.icd.icd10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutICD extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_icd);
        TextView generalInfoTxt = (TextView)findViewById(R.id.generalinfotxt);
        // Make text scrollable
        generalInfoTxt.setMovementMethod(new ScrollingMovementMethod());
        String filename = getIntent().getStringExtra("FILENAME");
        generalInfoTxt.setText(readTxt(filename));
        
	}
	private String readTxt(String filename){
		 InputStream inputStream;
		try {
			inputStream = getAssets().open(filename);
			 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		     
		     int i;
		     try {
		    	 i = inputStream.read();
		    	 while (i != -1){
		    		 byteArrayOutputStream.write(i);
		    		 i = inputStream.read();
		    	 }
		    	 inputStream.close();
		     } catch (IOException e) {   	
		    	 e.printStackTrace();
		     }
		  
		     return byteArrayOutputStream.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		} 
	    
	 }
}