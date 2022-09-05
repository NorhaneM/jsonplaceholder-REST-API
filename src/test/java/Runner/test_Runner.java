package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = {"src/main/resources/features"},
            glue = {"Steps_Definition"},
          //  cucumber.publish.enabled=true

            plugin = {
                    "pretty",
                    "html:target/cucumberReports/cucumber-pretty.html",
                    "json:target/cucumberReports/cucumber-TestReport.json",
                    "rerun:target/cucumberReports/rerun.txt",
                    "junit:target/cucumberReports/cukes.xml",
            },
            dryRun = false,
            tags = "@Runner"
           )
    public class test_Runner extends AbstractTestNGCucumberTests {
    }

