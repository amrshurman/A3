package CucumberA4.Part3.CucumberA4P3ex2.classes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import main.client.StartTerminals;

public class ex2Student4 {
	StartTerminals st = new StartTerminals();
	@Given("I want to test part three ex two for student four")
	public void i_want_to_test_part_three_ex_two_for_student_four() {
		String[] args = new String[1];
		args[0] = "P2ex2 Student2 4";
		//System.out.println("LOL");
		st.main(args);
	}

	@Then("Run student four on test mode using parralel plugin for ex two")
	public void run_student_four_on_test_mode_using_parralel_plugin_for_ex_two() {
	    // Write code here that turns the phrase above into concrete actions
	}
}
