package com.saucelabs.appium;

import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import com.xamarin.testcloud.appium.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

public class AndroidContactsTest {
   @Rule
   public TestWatcher watcher = Factory.createWatcher();

    private EnhancedAndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../apps/ContactManager");
        File app = new File(appDir, "ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "0819231e00d4caac");
        //capabilities.setCapability("deviceName","Android Emulator");
        //capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.label("Stopping app");
        driver.quit();
    }

    @Test
    public void addContact(){
        driver.label("App launched");
        WebElement el = driver.findElement(By.xpath(".//*[@text='Add Contact']"));
        el.click();
        List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        driver.label("Add Contact");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.swipe(100, 500, 100, 100, 2);
        driver.label("Ready to save");
        driver.findElementByXPath(".//*[@text='Save']").click();
        driver.label("Saved");
    }

}
