package CucumberA4;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import main.server.network.StartServer;

public class Part1Server {
	StartServer ss = new StartServer();
	@Given("^I want to test if students register properly for part one$")
	public void i_want_to_test_if_students_register_properly_for_part_one() throws Throwable {
		String[] args = new String[1];
		args[0] = "4";
		ss.main(args);
	}

	@Then("^Run the server on part one test mode$")
	public void run_the_server_on_part_one_test_mode() throws Throwable {
	    
	}
}
