package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class Student1Test extends TestCase {
	StartTerminals st = new StartTerminals();
	
	@Given("^I want to test logging in and off for a student$")
	public void i_want_to_test_logging_in_and_off_for_a_student() throws Throwable {
		String[] args = new String[1];
		args[0] = "4";
		try
		{
		    Thread.sleep(5000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		st.main(args);
	}

	@Then("^Run the student(\\d+) on test mode$")
	public void run_the_student_on_test_mode(int arg1) throws Throwable {

	}



}
