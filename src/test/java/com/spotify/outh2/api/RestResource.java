package com.spotify.outh2.api;

import com.spotify.outh2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.outh2.api.Route.API;
import static com.spotify.outh2.api.Route.TOKEN;
import static com.spotify.outh2.api.specBuilder.getrequestSpec;
import static com.spotify.outh2.api.specBuilder.getresponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path , String token , Object requestPlaylist)
    {
        return given(getrequestSpec()).       // Stored Response in responseplaylist
                body(requestPlaylist).
                header("Authorization","Bearer "+ token).
                when().post(path).    // "/users/z2692t0nymgelta3qk5bgl88b/playlists"
                then().spec(getresponseSpec()).
                extract().response();
    }


    public static Response postAccountGenerateToken(HashMap<String,String> formparams)
    {
       return given().
               baseUri("https://accounts.spotify.com").
               contentType(ContentType.URLENC).
                formParams(formparams).
                log().all().
       when().post(API + TOKEN).
       then().spec(getresponseSpec()).
                extract().response() ;
    }


    public static Response get(String path , String token)
    {     return given(getrequestSpec()).
            header("Authorization","Bearer "+token).
                when().get(path).    //   /playlists/3gyE4lbLDIU9FCwIoARW0a
                then().spec(getresponseSpec()).
                extract().response()    ;
    }

    public static Response update(String path , String token , Object requestPlaylist)
    {
       return given(getrequestSpec()).
               header("Authorization","Bearer "+ token).
                body(requestPlaylist).
                when().put(path).   //    /playlists/3gyE4lbLDIU9FCwIoARW0a
                then().spec(getresponseSpec()).
               extract().response();

    }


}
