package CucumberA4.Part3.CucumberA4P3.classes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import main.server.network.ATServer;
import main.server.network.StartServer;

public class P3ex1Server {
	StartServer ss = new StartServer();
	@Given("^I want to test if students register properly for part three example one$")
	public void i_want_to_test_if_students_register_properly_for_part_three_example_one() throws Throwable {
		String[] args = new String[1];
		args[0] = "7";
		//System.out.println("LOL");
		ss.main(args);
	}

	@Then("^Run the server on part three example one test mode$")
	public void run_the_server_on_part_three_example_one_test_mode() throws Throwable {
	}
}
