package es.ual.unia.cocheduinodroid.bluethoot;

public class BluetoothNotSupportedException extends Exception {

	public BluetoothNotSupportedException() {
	  super("Bluetooth not available");
	}
}
