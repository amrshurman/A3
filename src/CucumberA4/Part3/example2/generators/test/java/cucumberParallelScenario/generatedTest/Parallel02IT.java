package test.java.cucumberParallelScenario.generatedTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/amrwo/eclipse-workspace/A3/src/CucumberA4/Part3/example2/features/ex2Student1.feature:6"},
        plugin = {"json:C:/Users/amrwo/eclipse-workspace/A3/target/cucumber-parallel/json/2.json"},
        monochrome = true,
        glue = {"CucumberA4.Part3.example2.classes"})
public class Parallel02IT {
}
