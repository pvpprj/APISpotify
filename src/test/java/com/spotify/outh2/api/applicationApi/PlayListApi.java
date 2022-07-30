package com.spotify.outh2.api.applicationApi;

import com.spotify.outh2.api.RestResource;
import com.spotify.outh2.pojo.Playlist;
import com.spotify.outh2.utils.ConfigeLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.outh2.api.Route.PLAYLIST;
import static com.spotify.outh2.api.Route.USERS;
import static com.spotify.outh2.api.TokenManager.getToken;
import static com.spotify.outh2.api.TokenManager.reNewToken;
import static com.spotify.outh2.api.specBuilder.getrequestSpec;
import static com.spotify.outh2.api.specBuilder.getresponseSpec;
import static io.restassured.RestAssured.given;

public class PlayListApi {
   // static String access_token = "BQB0EfWfwmq8DRGdcenvGCGRGtt4MWfliAD7Pr6z9fEnMvSSSc5bVXWp4zaMcDqvjbw-g-D0iDjKUV_p6xyEb0_8gQhbBrHvGYBxkbXwqjFTmZqdVoBob6N2bUWChuhBtKJ0dnzJ1mWyG3hS86IeRG22S3-VeZ3Ja65o2wsuuzwJtnXKci3gTu7-yvQMmXq1Gh7X8t5C2ivu0Ep59BGtmM-5exUWBiTyE0b5pFi2FPrNFJFd4Y7vILKWYnZPYx4vy5pAsDZfMkmY";
   @Step
    public static Response post(Playlist requestPlaylist)
    {
        return RestResource.post(USERS+"/"+ ConfigeLoader.getInstance().getUser()+PLAYLIST ,getToken() , requestPlaylist );
    }

    public static Response post(String token ,Playlist requestPlaylist)
    {
        return RestResource.post(USERS+"/"+ ConfigeLoader.getInstance().getUser()+PLAYLIST , token , requestPlaylist );
    }

    public static Response get(String playlistID)
    {
        return RestResource.get(PLAYLIST+"/"+playlistID,getToken());
    }

    public static Response update(String playlistID ,Playlist requestPlaylist)
    {
        return RestResource.update(PLAYLIST+"/"+playlistID,getToken() , requestPlaylist );
    }

}