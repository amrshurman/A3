package test.java.cucumberParallelScenario.generatedTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/emad1/eclipse-workspace/A3/src/CucumberA4/Part3/CucumberA4P3/features/ex1Student4.feature:6"},
        plugin = {"json:C:/Users/emad1/eclipse-workspace/A3/target/cucumber-parallel/json/5.json"},
        monochrome = true,
        glue = {"CucumberA4.Part3.CucumberA4P3.classes"})
public class Parallel05IT {
}
