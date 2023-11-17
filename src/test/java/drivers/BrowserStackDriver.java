package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AndroidConfig;
import config.UserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    static AndroidConfig androidConfig = ConfigFactory.create(AndroidConfig.class, System.getProperties());
    static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", userConfig.getUserName());
        mutableCapabilities.setCapability("browserstack.key", userConfig.getUserPassword());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", androidConfig.getAppUrl());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", androidConfig.getDeviceName());
        mutableCapabilities.setCapability("os_version", androidConfig.getPlatformVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(userConfig.getRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
