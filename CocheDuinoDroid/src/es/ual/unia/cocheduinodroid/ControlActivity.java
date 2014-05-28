package es.ual.unia.cocheduinodroid;

import java.io.IOException;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import es.ual.unia.cocheduinodroid.bluethoot.BluethoothController;
import es.ual.unia.cocheduinodroid.bluethoot.BluetoothNotAvaiableException;
import es.ual.unia.cocheduinodroid.bluethoot.BluetoothNotSupportedException;
import es.ual.unia.cocheduinodroid.views.ADialogSelectDevices;

public class ControlActivity extends Activity implements DialogInterface.OnClickListener{

	private static final int REQUEST_ENABLE_BT = 1;
	private static final byte ARRIBA ='1';
	private static final byte ABAJO='2';
	private static final byte  DERECHA='3';
	private static final byte IZQUIERDA='4';
	private static final byte PARAR='0';
	private BluethoothController blueController;
	
	private Button btnconectar;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		try {
			
			blueController = new BluethoothController(this);
			blueController.init();
			blueController.Discover();
			//blueController.connectTo(0);
			
			//blueController.sendData(data);
		//	blueController.close();
			btnconectar=(Button) findViewById(R.id.btnconnect);
			btnconectar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(blueController.isConnected()){blueController.close();
					 btnconectar.setText(R.string.conectar);
					 EditText edtCon = (EditText) findViewById(R.id.edtDisp);
					edtCon.setText("");
					}else{
					String[] devicesNames=blueController.getPairedDeviceNames();
					ADialogSelectDevices selectDeviceDialog = new ADialogSelectDevices(ControlActivity.this, devicesNames, ControlActivity.this);
					selectDeviceDialog.show();
					
					}
				}
			});
			ImageButton btnarriba= (ImageButton) findViewById(R.id.btnarriba);
			ImageButton btnabajo= (ImageButton) findViewById(R.id.btnabajo);
			ImageButton btnderecha= (ImageButton) findViewById(R.id.btnderecha);
			ImageButton btnizquierda= (ImageButton) findViewById(R.id.btnizquierda);
			ImageButton btnparar = (ImageButton) findViewById(R.id.btnparar);
			senDataListener lstarriba = new senDataListener(ARRIBA);
			senDataListener lstabajo = new senDataListener(ABAJO);
			senDataListener lstderecha = new senDataListener(DERECHA);
			senDataListener lstizquierda = new senDataListener(IZQUIERDA);
			senDataListener lstparar = new senDataListener(PARAR);
			btnarriba.setOnClickListener(lstarriba);
			btnabajo.setOnClickListener(lstabajo);
			btnderecha.setOnClickListener(lstderecha);
			btnizquierda.setOnClickListener(lstizquierda);
			btnparar.setOnClickListener(lstparar);
			
		} catch (BluetoothNotAvaiableException e) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} catch (BluetoothNotSupportedException e) {
			Toast.makeText(this, "Este dispositivo no soporta bluetooth",
					Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.control, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_CANCELED) {
			try {
				this.blueController.init();
			} catch (BluetoothNotAvaiableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		try {
			Toast.makeText(this, "Conectando...", Toast.LENGTH_SHORT).show();
			blueController.connectTo(which);
			btnconectar.setText(R.string.desconectar);
			EditText edtCon = (EditText) findViewById(R.id.edtDisp);
			edtCon.setText(blueController.getPairedDeviceNames()[which]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private class senDataListener implements OnClickListener{

		private byte data;
		public senDataListener(byte data) {
			this.data=data;
		}
		@Override
		public void onClick(View v) {
			byte[] buffer=new byte[1];
			buffer[0]=this.data;
			try {
				blueController.sendData(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
