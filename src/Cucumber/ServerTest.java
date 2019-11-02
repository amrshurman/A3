package Cucumber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import main.server.logic.handler.model.*;
import main.server.logic.model.*;
import main.server.network.StartServer;
public class ServerTest extends TestCase{
	
	StartServer ss = new StartServer();
	
	@Given("I want to rig the deadline times for testing")
	public void i_want_to_rig_the_deadline_times_for_testing() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String[] args = new String[1];
		args[0] = "test";
		ss.main(args);
	}

	@Then("Run the Server on test mode")
	public void run_the_Server_on_test_mode() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
}
