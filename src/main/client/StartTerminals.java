package main.client;

import main.utilities.Config;

public class StartTerminals {

	public static void main(String[] args) {
		if (args==null ||(args.length==0)) {
		new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
		}
		else {
			new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT, args[0]);	
		}

	}

}
