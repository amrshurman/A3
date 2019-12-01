package CucumberA4.Part2.example2;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.client.ATClient;
import main.client.StartTerminals;
import main.server.network.ATServer;
import main.utilities.Config;
public class Part2ex2 {
	@Given("^I want to test example two for Part two$")
	public void i_want_to_test_example_two_for_Part_two() throws Throwable {
		TestThread3 t3 = new TestThread3();
		String[] s= new String[0];
		t3.main(s);
	}

	@Then("^Run students on part two example two test mode$")
	public void run_students_on_part_two_example_two_test_mode() throws Throwable {

	}
}
