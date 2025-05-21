package RestAssured.cucumber;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReportGenerator {

    public static void main(String[] args) {
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json"); // Update this path if needed

        String projectName = "RestAssured API Testing";
        Configuration config = new Configuration(reportOutputDirectory, projectName);

        // Optional metadata
        config.addClassifications("Platform", "GitHub Actions");
        config.addClassifications("Browser", "N/A");
        config.addClassifications("Branch", "master");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();

        System.out.println("Cucumber HTML report generated at: target/cucumber-reports");
    }
}
