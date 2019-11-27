package CucumberA4;

import main.client.StartTerminals;

public class TestThread {
	public static void main(String args[]) {

	      StartTerminals T1 = new StartTerminals( "Student 1");
	      StartTerminals T2 = new StartTerminals( "Student 2");

	      T1.start();
	      T2.start();
	         while(true) {
	        		
	         }
	   }
	
}
