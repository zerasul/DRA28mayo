package com.example.ejemplosoapclient;

import com.example.ejemplosoapclient.ws.EjemploSoapService;
import com.example.ejemplosoapclient.ws.IWsdl2CodeEvents;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements IWsdl2CodeEvents {

	private EditText edtsum1;
	private EditText edtsum2;
	private Button btnsuma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.edtsum1=(EditText) findViewById(R.id.edtSuma1);
		this.edtsum2=(EditText) findViewById(R.id.edtsuma2);
		this.btnsuma= (Button)findViewById(R.id.btnsumar);
		this.btnsuma.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					EjemploSoapService ejemplo = new EjemploSoapService(MainActivity.this);
					Integer dato1=Integer.parseInt(edtsum1.getText().toString());
					Integer dato2=Integer.parseInt(edtsum2.getText().toString());
					ejemplo.sumarAsync(dato1, dato2);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void Wsdl2CodeStartedRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Resultado");
		Integer datos = (Integer)Data;
		builder.setMessage("El resultado es: "+datos);
		builder.show();
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "Error: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
		ex.printStackTrace();
	}

	@Override
	public void Wsdl2CodeEndedRequest() {
		// TODO Auto-generated method stub
		
	}

}
