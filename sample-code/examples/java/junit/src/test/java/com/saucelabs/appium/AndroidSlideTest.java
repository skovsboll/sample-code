package com.saucelabs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import com.xamarin.testcloud.appium.Factory;
import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;


/**
 * Created by saikrisv on 26/04/16.
 */
public class AndroidSlideTest {
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
    public void testSlider(){
        driver.label("App shown");
        driver.scrollTo("Views").click();
        driver.label("View shown");
        driver.scrollTo("Seek Bar").click();
        driver.label("Seek Bar shown");

        WebElement slider = driver.findElementById("io.appium.android.apis:id/seek");
        Point sliderLocation = getCenter(slider);
        driver.label("About to slide");
        driver.swipe(sliderLocation.getX(), sliderLocation.getY(), sliderLocation.getX()-100, sliderLocation.getY(), 7000);
        driver.label("Sliding is FUN!");

    }

    private Point getCenter(WebElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth()/2, upperLeft.getY() + dimensions.getHeight()/2);
    }
}
