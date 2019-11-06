package Cucumber2;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.server.logic.handler.model.*;
import main.server.logic.model.*;
import main.server.network.StartServer;
public class serverInvalidTest extends TestCase{
	StartServer ss = new StartServer();
	@Given("^we want to rig the deadline times for testing invalid results$")
	public void we_want_to_rig_the_deadline_times_for_testing_invalid_results() throws Throwable {
		String[] args = new String[1];
		args[0] = "test2";
		ss.main(args);
	}

	@Then("^run Server on invalid test mode and await invalid input$")
	public void run_Server_on_invalid_test_mode_and_await_invalid_input() throws Throwable {

	}
}
