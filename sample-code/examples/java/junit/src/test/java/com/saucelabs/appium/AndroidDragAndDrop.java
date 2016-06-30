package com.saucelabs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.xamarin.testcloud.appium.Factory;
import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

import java.io.File;
import java.net.URL;

/**
 * Created by saikrisv on 26/04/16.
 */
public class AndroidDragAndDrop {
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
        driver.quit();
    }

    @Test
    public void testDragAndDrop() throws InterruptedException {
        driver.scrollTo("Views").click();
        driver.scrollTo("Drag and Drop").click();
        driver.label("Three dots");
        MobileElement calc = (MobileElement) driver.findElementById("io.appium.android.apis:id/drag_dot_1");
        TouchAction touchAction = new TouchAction(driver);
        driver.label("Longpressing on the first dot");
        touchAction.press(calc).perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.label("Longpressed on the first dot");
        touchAction.moveTo(driver.findElementById("io.appium.android.apis:id/drag_dot_2")).release().perform();
        driver.label("Moving it to on the second dot");
        Thread.sleep(5000);
        driver.label("Dropped on the second dot");
        Assert.assertEquals(driver.findElementById("io.appium.android.apis:id/drag_result_text").getText(),"Dropped!");
    }
}
