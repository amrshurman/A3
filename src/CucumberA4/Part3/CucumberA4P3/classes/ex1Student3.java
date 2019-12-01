package CucumberA4.Part3.CucumberA4P3.classes;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.StartTerminals;

public class ex1Student3 {
	StartTerminals st = new StartTerminals();
	@Given("^I want to test part three for student three$")
	public void i_want_to_test_part_three_for_student_three() throws Throwable {
		String[] args = new String[1];
		args[0] = "P2ex1 Student2 3";
		//System.out.println("LOL");
		st.main(args);
	}

	@Then("^Run student three on test mode using parralel plugin$")
	public void run_student_three_on_test_mode_using_parralel_plugin() throws Throwable {
	    
	}
}
