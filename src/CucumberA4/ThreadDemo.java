package CucumberA4;

public class ThreadDemo extends Thread{
	 private Thread t;
	   private String threadName;

	   ThreadDemo( String name) {
	      threadName = name;

	   }
		public void printCount() {
		      try {
		         for(int i = 5; i > 0; i--) {
		            System.out.println("Counter   ---   "  + threadName +" " + i );
		         }
		      } catch (Exception e) {
		         System.out.println("Thread  interrupted.");
		      }
		   }
	   public void run() {
		   try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      printCount();
	      System.out.println("Thread " +  threadName + " exiting.");
	   }

	   public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}
