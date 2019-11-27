package CucumberA4;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.ATClient;
import main.client.StartTerminals;
import main.server.network.ATServer;
import main.utilities.Config;
public class Part1 extends Thread{
	
	@Given("^I want to test if two students can register assyncrounously$")
	public void i_want_to_test_if_two_students_can_register_assyncrounously() throws Throwable {
		TestThread t = new TestThread();
		String[] s= new String[0];
		t.main(s); 
	}

	@Then("^Run two students on test mode$")
	public void run_two_students_on_test_mode() throws Throwable {
	   
	}
}
