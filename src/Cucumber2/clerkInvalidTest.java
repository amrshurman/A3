package Cucumber2;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class clerkInvalidTest extends TestCase {
	StartTerminals st = new StartTerminals();
	
	@Given("^we want to test a clerk making invalid moves$")
	public void we_want_to_test_a_clerk_making_invalid_moves() throws Throwable {
		String[] args = new String[1];
		args[0] = "11";
		st.main(args);
	    
	}

	@Then("^Run Clerk on Invalid test mode$")
	public void run_Clerk_on_Invalid_test_mode() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}


}
