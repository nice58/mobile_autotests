package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})

public interface DevicesConfig extends Config {
    @Key("appUrl")
    String getAppUrl();

    @Key("deviceName")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDeviceName();

    @Key("platformVersion")
    @DefaultValue("12.0")
    String getPlatformVersion();
}
