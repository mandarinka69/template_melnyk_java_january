package com.example;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:config.properties" })
public interface  ProjectConfig extends Config {


    @Key("apiPath.${env}")
    @DefaultValue("apiPath.prod")
    String apiPath();

    @Key("servers.api.auto.${env}")
    @DefaultValue("api.auto.prod")
    String apiAuto();

}
