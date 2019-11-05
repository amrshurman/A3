package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class Student4Test extends TestCase {
	StartTerminals st = new StartTerminals();
	@Given("^I want to test student(\\d+) running through registration$")
	public void i_want_to_test_student_running_through_registration(int arg1) throws Throwable {
		String[] args = new String[1];
		args[0] = "7";
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

	@Then("^Run student (\\d+) under test mode$")
	public void run_student_under_test_mode(int arg1) throws Throwable {
	 
	}

}
