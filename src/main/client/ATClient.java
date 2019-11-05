package main.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.utilities.Config;
import main.utilities.Trace;

import org.apache.log4j.Logger;

public class ATClient implements Runnable {

	private int ID = 0;
	private Socket socket = null;
	private Thread thread = null;
	private ClientThread client = null;
	private BufferedReader console = null;
	private BufferedReader streamIn = null;
	private BufferedWriter streamOut = null;
	private Logger logger = Trace.getInstance().getLogger(this);
	private int countC1 = 0;
	private int countC2 = 0;
	private int countC3 = 0;
	private int countS1 = 0;
	private int countS2 = 0;
	private int countS3 = 0;
	private int countS4 = 0;
	private int countS5 = 0;
	String type = "";

	public ATClient(String serverName, int serverPort) {
		System.out.println("Connecting to the server. Please wait ...");
		try {
			this.socket = new Socket(serverName, serverPort);
			this.ID = socket.getLocalPort();
			logger.info(String.format("%d : Connected to server: %s", ID, socket.getInetAddress()));
			logger.info(String.format("%d : Connected to portid: %s", ID, socket.getLocalPort()));
			this.start();
			System.out.println("Welcome To Academic Tracking System!");
			System.out.println("Greeting To Start!");
		} catch (UnknownHostException uhe) {
			System.err.println(ID + ": Unknown Host");
			String message = String.format("Exception thrown : %s \n", uhe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
		} catch (IOException ioe) {
			System.out.println(ID + ": Unexpected exception");
			String message = String.format("Exception thrown : %s \n", ioe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
			System.out.println("Fail to connect to the server,please try later!");
		}
	}

	public ATClient(String serverName, int serverPort, String s) {
		Config.testC1 = true;
		type = s;
		System.out.println("Connecting to the server. Please wait ...");
		try {
			this.socket = new Socket(serverName, serverPort);
			this.ID = socket.getLocalPort();
			logger.info(String.format("%d : Connected to server: %s", ID, socket.getInetAddress()));
			logger.info(String.format("%d : Connected to portid: %s", ID, socket.getLocalPort()));
			this.start();
			System.out.println("Welcome To Academic Tracking System!");
			System.out.println("Greeting To Start!");
		} catch (UnknownHostException uhe) {
			System.err.println(ID + ": Unknown Host");
			String message = String.format("Exception thrown : %s \n", uhe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
		} catch (IOException ioe) {
			System.out.println(ID + ": Unexpected exception");
			String message = String.format("Exception thrown : %s \n", ioe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
			System.out.println("Fail to connect to the server,please try later!");
		}
		while (true) {

		}
	}

	public int getID() {
		return this.ID;
	}

	public void start() throws IOException {
		try {
			console = new BufferedReader(new InputStreamReader(System.in));
			streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			streamOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			if (thread == null) {
				client = new ClientThread(this, socket);
				thread = new Thread(this);
				thread.start();
			}
		} catch (IOException ioe) {
			String message = String.format("Exception thrown : %s \n", ioe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
			throw ioe;
		}
	}

	@Override
	public void run() {
		while (thread != null) {
			try {
				if (streamOut != null) {
					streamOut.flush();
					if (Config.testC1 == false) {
						streamOut.write(console.readLine() + "\n");
					} else {
						if (type.equals("1")) {
							if (countC1 == 0) {
								streamOut.write("\n");
								// System.out.println("");
							}
							if (countC1 == 1) {
								streamOut.write("clerk" + "\n");
								// System.out.println("Clerk");
							}
							if (countC1 == 2) {
								streamOut.write("admin" + "\n");
								// System.out.println("admin");
							}
							if (countC1 == 3) {
								streamOut.write("log out" + "\n");
								// System.out.println("admin");
							}
							if (countC1 == 4) {
								streamOut.write("Exit" + "\n");
							}
							countC1++;
						}
						if (type.equals("2")) {
							Config.testC2=true;
							if (countC2 == 0) {
								streamOut.write("\n");
							}
							if (countC2 == 1) {
								streamOut.write("clerk" + "\n");
							}
							if (countC2 == 2) {
								streamOut.write("admin" + "\n");
							}
							if (countC2 == 3) {
								streamOut.write("create student" + "\n");
							}
							if (countC2 == 4) {
								streamOut.write("100996742, Amr, y" + "\n");
							}
							if (countC2 == 5) {
								streamOut.write("Delete student" + "\n");
							}
							if (countC2 == 6) {
								streamOut.write("100996742" + "\n");
							}
							if (countC2 == 7) {
								streamOut.write("" + "\n");
							}
							if (countC2 == 8) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC2 == 9) {
								streamOut.write("Testing, 123456, 2, y, 0, 4, n, n" + "\n");
							}
							if (countC2 == 10) {
								streamOut.write("" + "\n");
							}
							if (countC2 == 11) {
								streamOut.write("Delete Course" + "\n");
							}
							if (countC2 == 11) {
								streamOut.write("123456" + "\n");
							}
							if (countC2 == 12) {
								streamOut.write("Exit" + "\n");
							}
							countC2++;
						}
						if (type.equals("3")) {
							Config.testC3=true;
							if (countC3 == 0) {
								streamOut.write("\n");
							}
							if (countC3 == 1) {
								streamOut.write("clerk" + "\n");
							}
							if (countC3 == 2) {
								streamOut.write("admin" + "\n");
							}
							if (countC3 == 3) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 4) {
								streamOut.write("3004, 456789, 1, y, 1, 2, y, y" + "\n");
							}
							if (countC3 == 5) {
								streamOut.write("");
							}
							if (countC3 == 6) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 7) {
								streamOut.write("111111111, Joe, y" + "\n");
							}
							if (countC3 == 8) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 9) {
								streamOut.write("222222222, Will, y" + "\n");
							}
							if (countC3 == 10) {
								streamOut.write("");
							}
							if (countC3 == 11) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 12) {
								streamOut.write("333333333, Harry, y" + "\n");
							}
							if (countC3 == 13) {
								streamOut.write("");
							}
							if (countC3 == 14) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 15) {
								streamOut.write("444444444, Sam, y" + "\n");
							}
							if (countC3 == 16) {
								streamOut.write("");
							}
							if (countC3 == 17) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 18) {
								streamOut.write("555555555, Sarah, y" + "\n");
							}
							if (countC3 == 19) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 20) {
								streamOut.write("4109, 234567, 3, y, 1, 4, y, y" + "\n");
							}
							if (countC3 == 21) {
								streamOut.write("");
							}
							if (countC3 == 22) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 23) {
								streamOut.write("2404, 654321, 2, y, 2, 3, y, y" + "\n");
							}
							if (countC3 == 24) {
								streamOut.write("");
							}
							if (countC3 == 25) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 26) {
								streamOut.write("2402, 345678, 1, y, 2, 1, y, y" + "\n");
							}
							if (countC3 == 27) {
								streamOut.write("");
							}
							if (countC3 == 28) {
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 29) {
								streamOut.write("3007, 987654, 1, y, 2, 2, y, n" + "\n");
							}
							if (countC3 == 30) {
								streamOut.write("");
							}
							if (countC3 == 31) {
								streamOut.write("Exit" + "\n");
							}
							countC3++;
						}
						if (type.equals("4")) {
							if (countS1 == 0) {
								streamOut.write("\n");
							}
							if (countS1 == 1) {
								streamOut.write("Student" + "\n");
							}
							if (countS1 == 2) {
								streamOut.write("111111111, Joe" + "\n");
							}
							if (countS1 == 3) {
								streamOut.write("log out" + "\n");
							}
							if (countS1 == 4) {
								streamOut.write("Exit" + "\n");
							}
							countS1++;
						}
						if (type.equals("5")) {
							if (countS2 == 0) {
								streamOut.write("\n");
							}
							if (countS2 == 1) {
								streamOut.write("Student" + "\n");
							}
							if (countS2 == 2) {
								streamOut.write("222222222, Will" + "\n");
							}
							if (countS2 == 3) {
								streamOut.write("Select Course" + "\n");
							}
							if (countS2 == 4) {
								streamOut.write("456789" + "\n");
							}
							if (countS2 == 5) {
								streamOut.write("" + "\n");
							}
							if (countS2 == 6) {
								streamOut.write("Register for Course" + "\n");
							}
							if (countS2 == 7) {
								streamOut.write("456789" + "\n");
							}
							if (countS2 == 8) {
								streamOut.write("Deregister Course" + "\n");
							}
							if (countS2 == 9) {
								streamOut.write("456789" + "\n");
							}
							if (countS2 == 10) {
								streamOut.write("" + "\n");
							}
							if (countS2 == 11) {
								streamOut.write("log out" + "\n");
							}
							if (countS2 == 12) {
								streamOut.write("Exit" + "\n");
							}
							countS2++;
						}
						if (type.equals("7")) {
							if (countS3 == 0) {
								streamOut.write("\n");
							}
							if (countS3 == 1) {
								streamOut.write("Student" + "\n");
							}
							if (countS3 == 2) {
								streamOut.write("333333333, Harry" + "\n");
							}
							if (countS3 == 3) {
								streamOut.write("Select Course" + "\n");
							}
							if (countS3 == 4) {
								streamOut.write("234567" + "\n");
							}
							if (countS3 == 5) {
								streamOut.write("" + "\n");
							}
							if (countS3 == 6) {
								streamOut.write("Register for Course" + "\n");
							}
							if (countS3 == 7) {
								streamOut.write("234567" + "\n");
							}
							if (countS3 == 8) {
								streamOut.write("" + "\n");
							}
							if (countS3 == 9) {
								try
								{
								    Thread.sleep(5000);
								}
								catch(InterruptedException ex)
								{
								    Thread.currentThread().interrupt();
								}
								streamOut.write("Drop Course" + "\n");
							}
							if (countS3 == 10) {
								streamOut.write("234567" + "\n");
							}
							if (countS3 == 11) {
								streamOut.write("log out" + "\n");
							}
							if (countS3 == 12) {
								streamOut.write("Exit" + "\n");
							}
							countS3++;
						}
						if (type.equals("6")) {
							if (countS4 == 0) {
								streamOut.write("\n");
							}
							if (countS4 == 1) {
								streamOut.write("Student" + "\n");
							}
							if (countS4 == 2) {
								streamOut.write("444444444, Sarah" + "\n");
							}
							if (countS4 == 3) {
								streamOut.write("Select Course" + "\n");
							}
							if (countS4 == 4) {
								streamOut.write("987654" + "\n");
							}
							if (countS4 == 5) {
								streamOut.write("" + "\n");
							}
							if (countS4 == 6) {
								streamOut.write("Register for Course" + "\n");
							}
							if (countS4 == 7) {
								streamOut.write("987654" + "\n");
							}
							if (countS4 == 8) {
								streamOut.write("" + "\n");
							}
							if (countS4 == 9) {
								streamOut.write("Exit" + "\n");
							}
							countS4++;
						}
					}
				} else {
					System.out.println(ID + ": Stream Closed");
				}
			} catch (IOException e) {
				String message = String.format("Exception thrown : %s \n", e.getMessage());
				logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
				stop();
			}
		}

	}

	public void handle(String msg) {//System.out.println(msg + "!");
		if (msg.equalsIgnoreCase("Exit")) {
			System.out.println("Exit");
			System.out.println("Good bye.");
			System.exit(1);
			stop();
		}
		else {
			System.out.println(msg);
		}
	}

	public void stop() {
		try {
			if (thread != null)
				thread = null;
			if (console != null)
				console.close();
			if (streamIn != null)
				streamIn.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();

			this.socket = null;
			this.console = null;
			this.streamIn = null;
			this.streamOut = null;
		} catch (IOException ioe) {
			String message = String.format("Exception thrown : %s \n", ioe.getMessage());
			logger.info(String.format("Class: %-12s: %s", this.getClass().getSimpleName(), message));
		}
		client.close();
	}

}
