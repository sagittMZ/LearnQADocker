import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.util.*;

import java.io.File;
import java.net.URL;

public class SimpleTest {
    public RemoteWebDriver driver;
    private static final String SELENIUM_URL = "http://127.0.0.1:4444/wd/hub";

    @BeforeTest
    public void start() throws Exception {
        this.driver = new RemoteWebDriver(
                new URL(SELENIUM_URL),
                new ChromeOptions()
        );
    }
    
    @Test
    public void simpleTest() throws Exception {
        this.driver.get("https://www.google.com/search?q=%D1%82%D0%B5%D0%BA%D1%83%D1%89%D0%B5%D0%B5+%D0%B2%D1%80%D0%B5%D0%BC%D1%8F&oq=ntreot&aqs=chrome.1.69i57j0l7.2320j0j9&sourceid=chrome&ie=UTF-8");
        this.takeSceenshot();
    }

    private void takeSceenshot() throws Exception {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date());
        FileUtils.copyFile(source, new File("./screenshots/screenshot_"+timestamp+".png"));
        System.out.println("The Screenshot is taken...");
        System.out.println("and finaly :'( current date is: " + timestamp);

    }

    @AfterTest
    public void closeSeleniumSession() {
        this.driver.close();
        this.driver.quit();
    }
}
