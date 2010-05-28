package pineapple.juice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class Client {
	final static String IP="192.168.0.96";//"91.95.222.90";
	final static int PORT=4444;
	InetAddress serverAddress;
	DatagramSocket socket;
	private int packetLenght;
	
	public Client(){
		try{
		// Retrieve the ServerName
		serverAddress = InetAddress.getByName(IP);
		Log.d("UDP", "C: Connecting...");
		/* Create new UDP-Socket */
		socket = new DatagramSocket();
		}
		catch (Exception e) {
			Log.e("UDP", "C: Error", e);
		}
	}
	public void sendData(String data) {
		try {
			byte[] dataSize = ("INT"+data.length()+".").getBytes();
			DatagramPacket packetSize = new DatagramPacket(dataSize, dataSize.length,serverAddress, PORT);
			socket.send(packetSize);
			/* Prepare some data to be sent. */
			byte[] buf = (data).getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length,	serverAddress, PORT);
			/* Send out the packet */
			socket.send(packet);
			Log.d("UDP", "C: Sent!");
		} catch (Exception e) {
			Log.e("UDP", "C: Error", e);
		}
	}
	public void receive(){
		DatagramPacket packet = new DatagramPacket(new byte[100],100,serverAddress, PORT);
		try {
			socket.receive(packet);
			String data = new String(packet.getData());
			if(data.contains("INT")){
				String sub = data.substring(3,data.indexOf("."));
				packetLenght = Integer.valueOf(sub);
			}
			else{
				Log.d("UDP", "C: Receive -> "+ data.substring(0, packetLenght));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}