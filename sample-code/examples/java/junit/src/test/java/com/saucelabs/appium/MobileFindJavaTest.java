package com.saucelabs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.xamarin.testcloud.appium.Factory;
import com.xamarin.testcloud.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;


public class MobileFindJavaTest {
  @Rule
  public TestWatcher watcher = Factory.createWatcher();

  private EnhancedAndroidDriver driver;
  private static final String     url    = "http://127.0.0.1:4723/wd/hub";

  @Test
  public void apiDemo() throws Exception {
    driver.label("Is there an 'about phone' item");
    driver.scrollTo("about phone");
    driver.label("Is there an 'bluetooth' item");
    driver.scrollTo("bluetooth");
  }


  @Before
  public void setUp() throws Exception {
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("appPackage", "com.android.settings");
    capabilities.setCapability("appActivity", ".Settings");
    driver = Factory.createAndroidDriver(new URL(url), capabilities);
  }

  @After
  public void tearDown() throws Exception {
    driver.label("Stopping app");
    driver.quit();
  }
}