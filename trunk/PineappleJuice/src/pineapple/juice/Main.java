package pineapple.juice;

import java.io.IOException;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {
    Client client;
    boolean running;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		client = new Client();
		client.sendData("Client connected");
		running=true;
		new Thread(){
			@Override
			public void run(){
				while(running){
					client.receive();
				}
			}
		}.start();
    }
    
    @Override
    public void onBackPressed(){
    	running=false;
    	super.onBackPressed();
    }
}