#!/usr/bin/env bash

rm target/upload/test-classes/com/saucelabs/appium/AndroidContactsTest.class
rm target/upload/test-classes/com/saucelabs/appium/iOSPageObjectTest.class
rm target/upload/test-classes/com/saucelabs/appium/SafariTest.class
rm target/upload/test-classes/com/saucelabs/appium/SauceTest.class
rm target/upload/test-classes/com/saucelabs/appium/SimpleTest.class
rm target/upload/test-classes/com/saucelabs/appium/UICatalogTest.class
rm target/upload/test-classes/com/saucelabs/appium/AndroidWebViewTest.class
find target/upload/test-classes/com/saucelabs/appium/ -name "*Test*" -exec rm {} \;


