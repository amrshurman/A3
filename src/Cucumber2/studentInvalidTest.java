package Cucumber2;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class studentInvalidTest extends TestCase {
	StartTerminals st = new StartTerminals();
	
	@Given("^we want to test a student making invalid moves$")
	public void we_want_to_test_a_student_making_invalid_moves() throws Throwable {
		String[] args = new String[1];
		args[0] = "12";
		st.main(args);
	}

	@Then("^Run Student on Invalid test mode$")
	public void run_Student_on_Invalid_test_mode() throws Throwable {

	}
}
