package com.dwp.Automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AbstractWebconn {

public static WebDriver driver = null;

        WebDriver mozilla = null;
     WebDriver headless=null;


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
                driverfx.setPreference("browser.download.dir","geckodriver");
                driverfx.setPreference("browser.download.folderList",2);
                driverfx.setPreference("browser.download.manager.showWhenStarting",false);
                driverfx.setPreference("pdfjs.disabled", true);
                driverfx.setPreference("browser.download.dir","mydownloads");
                driverfx.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
                driverfx.setPreference("browser.download.panel.shown", false);
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", false);
                capabilities.setCapability(FirefoxDriver.PROFILE, driverfx);
                driver = new FirefoxDriver( jumbo);
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
        }else {
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
    public void navigate(String Url)
    {
     //   driver=mozilla;
        openBrowser("mozilla");
        driver.get(Url);
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
        driver.findElement(By.xpath(arg));
    }

    //typeByxpath
    public void typeByxpath(String text, String xpath)
    {

                driver.findElement(
                        By.xpath(xpath
                                ))
                        .clear();
                driver.findElement(
                        By.xpath(
                                xpath))
                        .sendKeys(text);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            }


}

