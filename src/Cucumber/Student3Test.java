package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class Student3Test extends TestCase {
	StartTerminals st = new StartTerminals();
	
	@Given("^I want to test student(\\d+) running registration$")
	public void i_want_to_test_student_running_registration(int arg1) throws Throwable {
		String[] args = new String[1];
		args[0] = "6";
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

	@Then("^Run student(\\d+) on test mode$")
	public void run_student_on_test_mode(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}


}
