package test.web.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class Driver {

    private Driver() {
    }

    public static WebDriver driver;

    public static void init() {
        //Configurations for file properties using
        Properties properties = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
            //Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        } //Configurations for file properties using

        String s = System.getProperty("test.browser");
    	
        if (s.equals("firefox")) {
        	
        	File pathToBinary = new File("/Applications/Firefox.app/Contents/MacOS/");
        	FirefoxProfile ffprofile = new FirefoxProfile(pathToBinary);
        	ffprofile.setPreference("dom.webnotifications.enabled", false);
            driver = new FirefoxDriver();

        } else if (s.equals("chrome")) {
        	Map<String, Object> prefs = new HashMap<String, Object>();
        	prefs.put("profile.default_content_setting_values.notifications", 2);
        	ChromeOptions options = new ChromeOptions();
         	//For Windows
        	//options.addArguments("--start-maximized");
         	// For Mac
        	options.addArguments("--kiosk");
        	options.setExperimentalOption("prefs", prefs);
        	driver = new ChromeDriver(options);
        	
        } else if (s.equals("chrome_hub")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                throw new AssertionError("Unexpected error during remote WebDriver setup");
            }

        } else {
            throw new AssertionError("Unsupported browser: " + System.getProperty("test.browser"));
        }

        //Configurations for synchronization
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(System.getProperty("test.timeout")),
                TimeUnit.SECONDS
        ); //Configurations for synchronization
        	
    }


    public static void tearDown() {
        driver.quit();
    }
}