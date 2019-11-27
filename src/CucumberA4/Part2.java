package CucumberA4;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.ATClient;
import main.client.StartTerminals;
import main.server.network.ATServer;
import main.utilities.Config;
public class Part2 {
	@Given("^I want to test Part two$")
	public void i_want_to_test_Part_two() throws Throwable {
		TestThread2 t2 = new TestThread2();
		String[] s= new String[0];
		t2.main(s);
	}

	@Then("^Run students on test mode$")
	public void run_students_on_test_mode() throws Throwable {
	    
	}

}
