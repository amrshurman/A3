package CucumberA4;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import main.server.network.StartServer;
public class Part2Server {
	StartServer ss = new StartServer();
	@Given("^I want to test example (\\d+) for part (\\d+)$")
	public void i_want_to_test_example_for_part(int arg1, int arg2) throws Throwable {
		String[] args = new String[1];
		args[0] = "5";
		ss.main(args);
	}

	@Then("^Run the server on part two example (\\d+) test mode$")
	public void run_the_server_on_part_two_example_test_mode(int arg1) throws Throwable {
	   
	}
}
