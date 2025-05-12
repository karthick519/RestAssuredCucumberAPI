import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.Collections;

public class CucumberReportGenerator {
    public static void main(String[] args) {
        File reportOutputDirectory = new File("target/cucumber-reports");
        String jsonReportPath = "target/cucumber.json";

        Configuration config = new Configuration(reportOutputDirectory, "RestAssured Tests");
        ReportBuilder reportBuilder = new ReportBuilder(Collections.singletonList(jsonReportPath), config);
        reportBuilder.generateReports();
    }
}
