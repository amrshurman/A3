package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class Clerk3Test {
	StartTerminals st = new StartTerminals();
	@Given("^I want to test a clerk creating a course$")
	public void i_want_to_test_a_clerk_creating_a_course() throws Throwable {
		String[] args = new String[1];
		args[0] = "3";
		st.main(args);
	}

	@Then("^Run Clerk(\\d+) on test mode$")
	public void run_Clerk_on_test_mode(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

}
