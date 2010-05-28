
package melonjuice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Server implements Runnable {

	public static final String SERVERIP = "192.168.0.96";
	public static final int SERVERPORT = 4444;
        private int packetLenght;

	@Override
	public void run() {
		try {
			/* Retrieve the ServerName */

			InetAddress serverAddr = InetAddress.getByName(SERVERIP);

			 System.err.println("UDP"+ "S: Connecting...");
			/* Create new UDP-Socket */
			DatagramSocket socket = new DatagramSocket(SERVERPORT, serverAddr);
			/* By magic we know, how much data will be waiting for us */
			byte[] buf = new byte[100];
			/* Prepare a UDP-Packet that can
			 * contain the data we want to receive */
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			 System.err.println("UDP"+ "S: Receiving...");
			/* Receive the UDP-Packet */
                         while(true){
                            socket.receive(packet);
                            String data =new String(packet.getData());
                            if(data.contains("INT")){
                                String sub = data.substring(3, data.indexOf("."));
                                packetLenght=Integer.valueOf(sub);
                            }
                            else{
                                System.err.println("UDP"+ "S: Received: '" + data.substring(0, packetLenght) + "'");
                                String response = "Server response";
                                byte[] dataBytes = ("INT"+response.length()+".").getBytes();
                                packet.setData(dataBytes);
                                socket.send(packet);
                                dataBytes = (response).getBytes();
                                packet.setData(dataBytes);
                                socket.send(packet);
                                packet.setData(data.getBytes());
                            }
                         }
		} catch (Exception e) {
			 System.err.println("UDP"+ "S: Error"+ e);
		}
	}
}