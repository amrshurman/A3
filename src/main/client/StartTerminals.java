package main.client;

import main.utilities.Config;

public class StartTerminals extends Thread {
	private Thread t;
	private String threadName;
public StartTerminals() {
	
}
	public StartTerminals(String s) {
		threadName = s;
	}

	public static void main(String[] args) {
		if (args == null || (args.length == 0)) {
			new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
		} else {
			new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT, args[0]);
		}
	}

	public void run() {
		try {
			Thread.sleep(200); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT, threadName);
		//System.out.println(threadName + " Done.");
	}

	public void start() {
		//System.out.println("Starting" + threadName);
		t = new Thread(this, threadName);
		t.start();
	//	  while(true) {
      		
	  //       }
	}
	
}
