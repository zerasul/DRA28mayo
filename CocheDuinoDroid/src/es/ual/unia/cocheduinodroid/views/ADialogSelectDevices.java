package es.ual.unia.cocheduinodroid.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ADialogSelectDevices {

	private AlertDialog.Builder builder;
	
	private Context context;
	
	public ADialogSelectDevices(Context context,String[] data,DialogInterface.OnClickListener callBack) {
		this.builder= new AlertDialog.Builder(context);
		this.context=context;
		this.builder.setItems(data, callBack);
		this.builder.setTitle("Select Device");
		this.builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
	}
	public void show()
	{
		
		this.builder.create().show();
	}
	
}
