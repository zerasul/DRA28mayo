package com.example.ejemplorest;

import com.example.ejemplorest.asynctasks.AsyncRest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText edtTexta;
	private EditText edtTextb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.edtTexta=(EditText) findViewById(R.id.edta);
		this.edtTextb=(EditText) findViewById(R.id.edtb);
		Button btnsumar = (Button) findViewById(R.id.btnsumar);
		btnsumar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AsyncRest rest = new AsyncRest(MainActivity.this);
				Integer a= Integer.parseInt(edtTexta.getText().toString());
				Integer b= Integer.parseInt(edtTextb.getText().toString());
				rest.execute(a,b);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
