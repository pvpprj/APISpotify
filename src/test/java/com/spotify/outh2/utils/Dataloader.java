package com.spotify.outh2.utils;

import java.util.Properties;

public class Dataloader {

        private  final Properties properties;
        private static Dataloader dataloader    ;

    private Dataloader() {
       properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static Dataloader getInstance()   {
        if (dataloader ==null)
        {
            dataloader =new Dataloader();
        }
         return dataloader;
    }

    public String getGetPlayListID()    {
        String prop = properties.getProperty("get_playlist_id");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getUpdateplayList()    {
        String prop = properties.getProperty("update_Playlist");
        if (prop!=null) return  prop;
        else throw new RuntimeException("property client_Secret is not specified in the config.properties file");
    }


}
