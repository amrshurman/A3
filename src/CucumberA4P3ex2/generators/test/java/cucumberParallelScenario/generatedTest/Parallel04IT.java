package test.java.cucumberParallelScenario.generatedTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/emad1/eclipse-workspace/A3/src/CucumberA4P3ex2/features/ex2Student3.feature:6"},
        plugin = {"json:C:/Users/emad1/eclipse-workspace/A3/target/cucumber-parallel/json/4.json"},
        monochrome = true,
        glue = {"CucumberA4P3ex2.classes"})
public class Parallel04IT {
}