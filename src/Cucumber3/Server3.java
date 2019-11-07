package Cucumber3;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.server.logic.handler.model.*;
import main.server.logic.model.*;
import main.server.network.StartServer;
public class Server3 {
	StartServer ss = new StartServer();
	@Given("^I want to run the system with rigged times$")
	public void i_want_to_run_the_system_with_rigged_times() throws Throwable {
		String[] args = new String[1];
		args[0] = "test3";
		ss.main(args);
	}

	@Then("^run server on test mode$")
	public void run_server_on_test_mode() throws Throwable {
	    
	}
}
