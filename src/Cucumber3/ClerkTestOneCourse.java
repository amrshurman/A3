package Cucumber3;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;
public class ClerkTestOneCourse {
	StartTerminals st = new StartTerminals();
	@Given("^I want to create a one cap sized course$")
	public void i_want_to_create_a_one_cap_sized_course() throws Throwable {
		String[] args = new String[1];
		args[0] = "20";
		st.main(args);
	}

	@Then("^Run clerk on test mode and create course$")
	public void run_clerk_on_test_mode_and_create_course() throws Throwable {
	    
	}
}
