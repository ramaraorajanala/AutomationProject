import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "pretty",
        "json:target/cucumber.json"}, features = "src/test/resources/features/test.feature",
         glue = "com.dwp.Automation.utils.StepDefs", tags ="@test")


public class RunCukesTests {
}