package com.saucelabs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import com.xamarin.testcloud.appium.Factory;
import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;


public class AndroidTest {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    private EnhancedAndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.label("Stopping app");
        driver.quit();
    }

    @Test
    public void apiDemo(){
        WebElement el = driver.findElement(By.xpath(".//*[@text='Animation']"));
        assertEquals("Animation", el.getText());
        driver.label("I could find Animation button via XPath");
        el = driver.findElementByClassName("android.widget.TextView");
        assertEquals("API Demos", el.getText());
        driver.label("The title says API Demos");
        el = driver.findElement(By.xpath(".//*[@text='App']"));
        driver.label("Found button that reads App, wonder...");
        el.click();
        driver.label("So this is the App section?");
        List<WebElement> els = driver.findElementsByClassName("android.widget.TextView");
        assertEquals("Activity", els.get(2).getText());
        driver.label("It do have an Activity item");
    }

}
