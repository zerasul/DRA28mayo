package es.ual.unia.cocheduinodroid.bluethoot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class BluethoothController {

	private BluetoothAdapter adapter;
	private List<BluetoothDevice> devices;
	private BluetoothSocket socket;
	private InputStream inputBluetoothStream;
	private OutputStream outBluetoothStream;
	private BroadcastReceiver receiver;
	
	private Context context;
	public BluethoothController(Context context) throws Exception {
	   this.adapter=BluetoothAdapter.getDefaultAdapter();
	   this.devices=new ArrayList<BluetoothDevice>();
	   this.context=context;
	   if(this.adapter==null){
		   throw new BluetoothNotSupportedException();
	   }
	   
	}
	public void init() throws BluetoothNotAvaiableException
	{
		if(!this.adapter.isEnabled())
		{
			throw new BluetoothNotAvaiableException();
		}
	}
	
	public void Discover()
	{
		this.adapter.startDiscovery();
		this.devices.clear();
		receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				String action= intent.getAction();
				if(BluetoothDevice.ACTION_FOUND.equals(action)){
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					if(!devices.contains(device))
						devices.add(device);
				}
				
			}
		};
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.context.registerReceiver(receiver, filter);
		Set<BluetoothDevice> pairedDevices = this.adapter.getBondedDevices();
		if(pairedDevices.size()>0){
			this.devices.addAll(pairedDevices);
		}
		
	}
	public String[] getPairedDeviceNames()
	{
		
		List<String> devicesNames= new ArrayList<String>();
		for (BluetoothDevice mydevices : this.devices) {
			devicesNames.add(mydevices.getName());
		}
		String[] adevicesNames= new String[devicesNames.size()];
		devicesNames.toArray(adevicesNames);
		return adevicesNames;
	}
	public void connectTo(int deviceNo)throws IOException
	{
		BluetoothDevice device= devices.get(deviceNo);
		
		Thread thread = new connectThread(device);
		thread.start();
		
	}
	public void sendData(byte[] data) throws IOException{
		if(isConnected()){
			
			this.outBluetoothStream.write(data);
		}
	}
	public void close()
	{
		try {
			//this.inputBluetoothStream.close();
			
			this.socket.close();
			this.Discover();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socket=null;
	}
	public boolean isConnected()
	{
		return this.socket!=null;
	}
	class connectThread extends Thread{
		
		 private BluetoothDevice device;
		 
		 public connectThread(BluetoothDevice device) {
			this.device=device;
		}
		 public void run()
		 {
			 try {
				adapter.cancelDiscovery();
				Thread.sleep(1000);
				context.unregisterReceiver(receiver);
				 socket=device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				 socket.connect();
				 outBluetoothStream=socket.getOutputStream();
				 
				
				 //inputBluetoothStream=socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}


 
