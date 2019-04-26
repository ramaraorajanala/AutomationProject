package com.dwp.Automation.utils;

import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AbstractWebconn {

    public static WebDriver driver = null;

    WebDriver mozilla = null;
    WebDriver headless = null;

    private final Properties properties;
    private final Properties config;

    protected AbstractWebconn(String resource) {
        this.properties = loadProperties(resource);
        this.config = loadProperties(properties.getProperty("testEnv") + "_config.properties");

    }


    private static Properties loadProperties(String resource) {
        Properties properties = new Properties();

        try {
            properties.load(AbstractWebconn.class.getResourceAsStream(resource));
        } catch (IOException e) {

            e.getMessage();
        }

        return properties;
    } //loadProperties


    /**
     * Get the {@link Properties} object.<p>
     *
     * @return
     */
    protected Properties getProperties() {
        return properties;
    }

    /**
     * Get the environment Config {@link Properties}.<p>
     *
     * @return
     */
    protected Properties getConfig() {
        return config;
    }


    public void openBrowser(String browserType) {
        Properties props = new Properties();
//        try {
//            InputStream is = getClass().getResourceAsStream("/host.properties");
//            System.out.println("is = " + is.toString());
//            props.load(is);
//        } catch (IOException e) {
//            // ain't no-one got time for dat
//        }
        if ("mozilla".equals(browserType)) {
            if (mozilla == null) {
                // fp.setEnableNativeEvents(true);
                // driver = new FirefoxDriver(fp);
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                //Headless Option
                FirefoxOptions jumbo = new FirefoxOptions();
                // jumbo.setHeadless(true);
                FirefoxProfile driverfx = new FirefoxProfile();
                driverfx.setPreference("browser.download.dir", "geckodriver");
                driverfx.setPreference("browser.download.folderList", 2);
                driverfx.setPreference("browser.download.manager.showWhenStarting", false);
                driverfx.setPreference("pdfjs.disabled", true);
                driverfx.setPreference("browser.download.dir", "mydownloads");
                driverfx.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
                driverfx.setPreference("browser.download.panel.shown", false);
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", false);
                capabilities.setCapability(FirefoxDriver.PROFILE, driverfx);
                driver = new FirefoxDriver(jumbo);
              /*   DesiredCapabilities cap = DesiredCapabilities.firefox();
                 cap.setCapability("version","");
                 cap.setCapability("platform", "LINUX");
    */
                   /* try {
                        props.load(AbstractWebConnector.class.getResourceAsStream("/host.properties"));
                    } catch (IOException e) {
                        // ain't no-one got time for dat
                    }
                    try {
                        driver = new RemoteWebDriver(new URL(
                                props.getProperty("webdriver.host", "http://localhost:4444/wd/hub")), cap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }*/
                mozilla = driver;
            } // if
            driver = mozilla;
//        } else if ("chrome".equals(browserType)) {
//            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browsers/mac/chromedriver");
//            driver = new ChromeDriver();
//        }  else if ("headless".equals(browserType)) {
//            if (headless == null) {
//                // fp.setEnableNativeEvents(true);
//                // driver = new FirefoxDriver(fp);
//                driver = new HtmlUnitDriver();
//                headless = driver;
//            } // if
//            driver = headless;
        } else {
            driver = new HtmlUnitDriver();
            headless = driver;
            driver = headless;
            System.out.println("THERE IS ERRgetProperties() LAUNCHING BROWSER");
        }
        // Maximaize the browser
        driver.manage().window().maximize();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void navigate(String Url) {
        //   driver=mozilla;
        openBrowser("mozilla");
        driver.get(getConfig().getProperty(Url));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    } //navigate

    public void sleep(String time) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickXpath(String arg) {
        driver.findElement(By.xpath(getProperties().getProperty(arg))).click();
    }

    //typeByxpath
    public void typeByxpath(String text, String xpath) {
        driver.findElement(
                By.xpath(
                        getProperties().getProperty(xpath)))
                .clear();
        driver.findElement(
                By.xpath(
                        getProperties().getProperty(xpath)))
                .sendKeys(text);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void screenShot(Scenario scenario) {

        try {

            if (scenario.isFailed()) {
                try {

                    System.out.println("Taking Picture");
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    String testName = scenario.getName();
                    System.out.println("scenario name " + testName);
                    scenario.embed(screenshot, "image/png");
                    System.out.println("Storing it");
                    scenario.write(testName);
                } catch (WebDriverException wde) {
                    System.err.println(wde.getMessage());
                } catch (ClassCastException cce) {
                    cce.printStackTrace();
                }
            } //ifh(
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void quitDriver() {
        try {
            // Quits Driver ..//
            driver.quit(); // ... and embed it in the report.

        } catch (Exception somePlatformsDontSupportQuitting) {
            System.err.println(somePlatformsDontSupportQuitting.getMessage());
        }
    }

    public boolean explicitWaitObject(String object) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    getProperties().getProperty(object))));
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

        return true;
    }


}

