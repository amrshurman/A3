package main.server.network;

import main.utilities.Config;

public class StartServer {
	
	public static long start;

	public static void main(String[] args) {
		if (args==null ||(args.length==0)) {
		System.out.println("Starting server ...");
		new ATServer(Config.DEFAULT_PORT);
		}
		else {
			System.out.println("Starting server on test mode...");
			new ATServer(Config.DEFAULT_PORT, args[0]);
		}

	}

}
