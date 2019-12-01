package CucumberA4.Part2.example2;

import main.client.StartTerminals;

public class TestThread3 {
	public static void main(String args[]) {

	      StartTerminals T1 = new StartTerminals( "P2ex2 Student 1");
	      StartTerminals T2 = new StartTerminals( "P2ex2 Student 2");
	      StartTerminals T3 = new StartTerminals( "P2ex2 Student 3");
	      StartTerminals T4 = new StartTerminals( "P2ex2 Student 4");
	      T1.start();
	      T2.start();
	      T3.start();
	      T4.start();
	         while(true) {
	        		
	         }
	   }
	
}
