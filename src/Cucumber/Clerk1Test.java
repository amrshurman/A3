package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class Clerk1Test extends TestCase {
	StartTerminals st = new StartTerminals();
	
	@Given("^I want to test a clerk$")
	public void i_want_to_test_a_clerk() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String[] args = new String[1];
		args[0] = "test";
		st.main(args);
	}

	@Then("^Run the Clerk(\\d+) on test mode$")
	public void run_the_Clerk_on_test_mode(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	 
	}


}
