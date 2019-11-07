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
	private int count11 = 0;
	private int count12 = 0;
	private int count20=-1;
	private int count21=-1;
	private int count22=-1;
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
							Config.testC2 = true;
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
							Config.testC3 = true;
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
								streamOut.write("444444444, Sarah, y" + "\n");
							}
							if (countC3 == 16) {
								streamOut.write("");
							}
							if (countC3 == 17) {
								streamOut.write("Create student" + "\n");
							}
							if (countC3 == 18) {
								streamOut.write("555555555, Sam, y" + "\n");
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
								streamOut.write("Create Course" + "\n");
							}
							if (countC3 == 32) {
								streamOut.write("4106, 369369, 5, n, 2, 1, y, n" + "\n");
							}
							if (countC3 == 34) {
								try {
									Thread.sleep(10000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Cancel Course" + "\n");
							}
							if (countC3 == 35) {
								streamOut.write("369369" + "\n");
							}
							if (countC3 == 36) {
								streamOut.write("" + "\n");
							}
							if (countC3 == 37) {
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
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
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
						if (type.equals("11")) {
							if (count11 == 0) {
								streamOut.write("\n");
							}
							if (count11 == 1) {
								streamOut.write("Clerk" + "\n");
							}
							if (count11 == 3) {
								streamOut.write("idk" + "\n");
							}
							if (count11 == 4) {
								streamOut.write("admin" + "\n");
							}
							if (count11 == 5) {
								streamOut.write("Create Course" + "\n");
							}
							if (count11 == 6) {
								streamOut.write("4109, 2345678, 3, y, 1, 4, y, y" + "\n");
							}
							if (count11 == 7) {
								streamOut.write("4109, 234567, 3, y, 1, 4, y, y" + "\n");
							}
							if (count11 == 8) {
								streamOut.write("" + "\n");
							}
							if (count11 == 9) {
								streamOut.write("Create Course" + "\n");
							}
							if (count11 == 10) {
								streamOut.write("4109, 234567, 3, y, 1, 4, y, y" + "\n");
							}
							if (count11 == 11) {
								streamOut.write("Create Course" + "\n");
							}
							if (count11 == 12) {
								streamOut.write("4004, 123456, 0, y, 0, 4, n, y" + "\n");
							}
							if (count11 == 13) {
								streamOut.write("" + "\n");
							}
							if (count11 == 14) {
								streamOut.write("Cancel Course" + "\n");
							}
							if (count11 == 15) {
								streamOut.write("123456" + "\n");
							}
							if (count11 == 16) {
								streamOut.write("" + "\n");
							}
							if (count11 == 17) {
								streamOut.write("Create Course" + "\n");
							}
							if (count11 == 18) {
								streamOut.write("5106, 456789, 1, n, 1, 3, y, n" + "\n");
							}
							if (count11 == 19) {
								streamOut.write("" + "\n");
							}
							if (count11 == 20) {
								streamOut.write("Delete Course" + "\n");
							}
							if (count11 == 21) {
								streamOut.write("923457" + "\n");
							}
							if (count11 == 22) {
								streamOut.write("" + "\n");
							}
							if (count11 == 23) {
								streamOut.write("Create Student" + "\n");
							}
							if (count11 == 24) {
								streamOut.write("1111111111, Joe, y" + "\n");
							}
							if (count11 == 25) {
								streamOut.write("111111111, Joe, y" + "\n");
							}
							if (count11 == 26) {
								streamOut.write("" + "\n");
							}
							if (count11 == 27) {
								streamOut.write("Create Student" + "\n");
							}
							if (count11 == 28) {
								streamOut.write("111111111, Joe, y" + "\n");
							}
							if (count11 == 29) {
								streamOut.write("Create Student" + "\n");
							}
							if (count11 == 30) {
								streamOut.write("222222222, Will, y" + "\n");
							}
							if (count11 == 31) {
								streamOut.write("Delete Student" + "\n");
							}
							if (count11 == 32) {
								streamOut.write("222222223" + "\n");
							}
							if (count11 == 33) {
								streamOut.write("Delete Student" + "\n");
							}
							if (count11 == 34) {
								streamOut.write("222222222" + "\n");
							}
							if (count11 == 35) {
								streamOut.write("" + "\n");
							}
							if (count11 == 36) {
								try {
									Thread.sleep(10000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Cancel Course" + "\n");
							}
							if (count11 == 36) {
								streamOut.write("923457" + "\n");
							}
							if (count11 == 38) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("123456" + "\n");
							}
							if (count11 == 40) {
								streamOut.write("" + "\n");
							}
							if (count11 == 41) {
								streamOut.write("Exit" + "\n");
							}
							count11++;
						}
						if (type.equals("12")) {
							if (count12 == 0) {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Student" + "\n");
							}
							if (count12 == 1) {
								streamOut.write("111114321, Joe" + "\n");
							}
							if (count12 == 2) {
								streamOut.write("student" + "\n");
							}
							if (count12 == 3) {
								streamOut.write("111111111, Joe" + "\n");
							}
							if (count12 == 4) {
								streamOut.write("Select Course" + "\n");
							}
							if (count12 == 6) {
								streamOut.write("234568" + "\n");
							}
							if (count12 == 7) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 8) {
								streamOut.write("" + "\n");
							}
							if (count12 == 9) {
								streamOut.write("Select Course" + "\n");
							}
							if (count12 == 10) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 11) {
								streamOut.write("" + "\n");
							}
							if (count12 == 12) {
								streamOut.write("Register for Course" + "\n");
							}
							if (count12 == 13) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 14) {
								streamOut.write("" + "\n");
							}
							if (count12 == 15) {
								streamOut.write("Select Course" + "\n");
							}
							if (count12 == 16) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 17) {
								streamOut.write("" + "\n");
							}
							if (count12 == 18) {
								streamOut.write("Deregister Course" + "\n");
							}
							if (count12 == 19) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 20) {
								streamOut.write("" + "\n");
							}
							if (count12 == 21) {
								streamOut.write("Drop Course" + "\n");
							}
							if (count12 == 22) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 23) {
								streamOut.write("" + "\n");
							}
							if (count12 == 24) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Register for Course" + "\n");
							}
							if (count12 == 25) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 26) {
								streamOut.write("" + "\n");
							}
							if (count12 == 27) {
								streamOut.write("Deregister Course" + "\n");
							}
							if (count12 == 28) {
								streamOut.write("2345678" + "\n");
							}
							if (count12 == 29) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 30) {
								streamOut.write("" + "\n");
							}
							if (count12 == 31) {
								streamOut.write("Register for Course" + "\n");
							}
							if (count12 == 32) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 33) {
								streamOut.write("" + "\n");
							}
							if (count12 == 34) {
								streamOut.write("Drop Course" + "\n");
							}
							if (count12 == 35) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 36) {
								streamOut.write("" + "\n");
							}
							if (count12 == 37) {
								streamOut.write("Select Course" + "\n");
							}
							if (count12 == 38) {
								streamOut.write("456789" + "\n");
							}
							if (count12 == 39) {
								streamOut.write("" + "\n");
							}
							if (count12 == 40) {
								streamOut.write("Register for Course" + "\n");
							}
							if (count12 == 41) {
								streamOut.write("456789" + "\n");
							}
							if (count12 == 42) {
								streamOut.write("" + "\n");
							}
							if (count12 == 43) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Register for course" + "\n");
							}
							if (count12 == 44) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 45) {
								streamOut.write("" + "\n");
							}
							if (count12 == 46) {
								streamOut.write("Deregister Course" + "\n");
							}
							if (count12 == 47) {
								streamOut.write("456789" + "\n");
							}
							if (count12 == 48) {
								streamOut.write("Drop Course" + "\n");
							}
							if (count12 == 49) {
								streamOut.write("234567" + "\n");
							}
							if (count12 == 50) {
								streamOut.write("" + "\n");
							}
							if (count12 == 51) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Drop Course" + "\n");
							}
							if (count12 == 52) {
								streamOut.write("456789" + "\n");
							}
							if (count12 == 53) {
								streamOut.write("" + "\n");
							}
							if (count12 == 54) {
								streamOut.write("Exit" + "\n");
							}
							count12++;
						}
						if (type.equals("20")) {
							if (count20 == -1) {
								streamOut.write("" + "\n");
							}
							if (count20 == 0) {
								streamOut.write("Clerk" + "\n");
							}
							if (count20 == 1) {
								streamOut.write("admin" + "\n");
							}
							if (count20 == 2) {
								streamOut.write("Create Course" + "\n");
							}
							if (count20 == 3) {
								streamOut.write("4004, 123456, 1, y, 0, 4, n, y" + "\n");
							}
							if (count20 == 4) {
								streamOut.write("" + "\n");
							}
							if (count20 == 5) {
								streamOut.write("Create Student" + "\n");
							}
							if (count20 == 6) {
								streamOut.write("111111111, Joe, y" + "\n");
							}
							if (count20 == 7) {
								streamOut.write("Create Student" + "\n");
							}
							if (count20 == 8) {
								streamOut.write("222222222, Will, y" + "\n");
							}
							if (count20 == 9) {
								streamOut.write("" + "\n");
							}
							if (count20 == 10) {
								streamOut.write("Exit" + "\n");
							}
							count20++;
						}
						if (type.equals("21")) {
							if (count21 == -1) {
								streamOut.write("" + "\n");
							}
							if (count21 == 0) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Student" + "\n");
							}
							if (count21 == 1) {
								streamOut.write("111111111, Joe" + "\n");
							}
							if (count21 == 2) {
								streamOut.write("Select Course" + "\n");
							}
							if (count21 == 3) {
								streamOut.write("123456" + "\n");
							}
							if (count21 == 4) {
								streamOut.write("" + "\n");
							}
							if (count21 == 5) {
								streamOut.write("Register for Course" + "\n");
							}
							if (count21 == 6) {
								streamOut.write("123456" + "\n");
							}
							if (count21 == 7) {
								streamOut.write("" + "\n");
							}
							if (count21 == 8) {
								streamOut.write("Exit" + "\n");
							}
							count21++;
						}
						if (type.equals("22")) {
							if (count22 == -1) {
								streamOut.write("" + "\n");
							}
							if (count22 == 0) {
								try {
									Thread.sleep(5000);
								} catch (InterruptedException ex) {
									Thread.currentThread().interrupt();
								}
								streamOut.write("Student" + "\n");
							}
							if (count22 == 1) {
								streamOut.write("222222222, Will" + "\n");
							}
							if (count22 == 2) {
								streamOut.write("Select Course" + "\n");
							}
							if (count22 == 3) {
								streamOut.write("123456" + "\n");
							}
							if (count22 == 4) {
								streamOut.write("" + "\n");
							}
							if (count22 == 5) {
								streamOut.write("Register for Course" + "\n");
							}
							if (count22 == 6) {
								streamOut.write("123456" + "\n");
							}
							if (count22 == 7) {
								streamOut.write("" + "\n");
							}
							if (count22 == 8) {
								streamOut.write("Exit" + "\n");
							}
							count22++;
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

	public void handle(String msg) {// System.out.println(msg + "!");
		if (msg.equalsIgnoreCase("Exit")) {
			System.out.println("Exit");
			System.out.println("Good bye.");
			System.exit(1);
			stop();
		} else {
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
