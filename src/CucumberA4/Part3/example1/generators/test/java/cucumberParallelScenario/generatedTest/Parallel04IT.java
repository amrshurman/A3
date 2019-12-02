package test.java.cucumberParallelScenario.generatedTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/amrwo/eclipse-workspace/A3/src/CucumberA4/Part3/example1/features/ex1Student3.feature:6"},
        plugin = {"json:C:/Users/amrwo/eclipse-workspace/A3/target/cucumber-parallel/json/4.json"},
        monochrome = true,
        glue = {"CucumberA4.Part3.example1.classes"})
public class Parallel04IT {
}
