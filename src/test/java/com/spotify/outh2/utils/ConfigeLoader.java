package com.spotify.outh2.utils;

import java.util.Properties;

public class ConfigeLoader {

        private  final Properties properties;
        private static ConfigeLoader configeLoader;

    private ConfigeLoader() {
       properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigeLoader getInstance()   {
        if (configeLoader==null)
        {
            configeLoader=new ConfigeLoader();
        }
         return configeLoader;
    }

    public String getClientID()    {
        String prop = properties.getProperty("client_id");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getClientSecret()    {
        String prop = properties.getProperty("client_secret");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_Secret is not specified in the config.properties file");
    }

    public String getGrantType()    {
        String prop = properties.getProperty("grant_type");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getRefreshToken()    {
        String prop = properties.getProperty("refresh_token");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getUser()    {
        String prop = properties.getProperty("user_id");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

}
