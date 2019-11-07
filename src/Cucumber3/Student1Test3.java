package Cucumber3;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;
public class Student1Test3 {
	StartTerminals st = new StartTerminals();
	@Given("^student one wants to register in a one cap sized course$")
	public void student_one_wants_to_register_in_a_one_cap_sized_course() throws Throwable {
		String[] args = new String[1];
		args[0] = "21";
		st.main(args);
	}

	@When("^another student two is to register to that course at the same time$")
	public void another_student_two_is_to_register_to_that_course_at_the_same_time() throws Throwable {
	    
	}

	@Then("^run student one on test mode$")
	public void run_student_one_on_test_mode() throws Throwable {
	   
	}
}
