package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader(){
        properties=PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }

    public String getGrantType(){
        String prop=properties.getProperty("grant_type");
        if (prop!=null) return prop;
        else throw new RuntimeException("Property grant_type is not specified in config.properties file");

    }

    public String getRefreshToken(){
        String prop=properties.getProperty("refresh_token");
        if (prop!=null) return prop;
        else throw new RuntimeException("Property refresh_token is not specified in config.properties file");

    }

    public String getAuthorization(){
        String prop=properties.getProperty("Authorization");
        if (prop!=null) return prop;
        else throw new RuntimeException("Property Authorization is not specified in config.properties file");

    }

    public String getUserId(){
        String prop=properties.getProperty("user_Id");
        if (prop!=null) return prop;
        else throw new RuntimeException("Property user_Id is not specified in config.properties file");

    }

}
