package com.spotify.outh2.api;

import com.spotify.outh2.utils.ConfigeLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.outh2.api.specBuilder.getresponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expires_time;

    public synchronized static String getToken()
    {
        try {
               if (access_token == null || Instant.now().isAfter(expires_time))
                 {  System.out.println("Renew ingToken ......");
                     Response response = reNewToken();
                     access_token = response.path("access_token");
                     int expiryDurationInSeconds = response.path("expires_in");
                     expires_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
                  }
                else System.out.println("Token is Good to use");
        }
        catch (Exception e) {
            throw new RuntimeException("Abort Failed to get token");
        }
        return access_token;
    }

    public static Response reNewToken()
    {
        HashMap<String,String> formparams= new HashMap<String,String>();
        formparams.put("client_id", ConfigeLoader.getInstance().getClientID());
        formparams.put("client_secret",ConfigeLoader.getInstance().getClientSecret());
        formparams.put("refresh_token",ConfigeLoader.getInstance().getRefreshToken());
        formparams.put("grant_type",ConfigeLoader.getInstance().getGrantType());

        Response response =RestResource.postAccountGenerateToken(formparams);

        if (response.statusCode()!= 200)
        {
            throw new RuntimeException("Abort !! Renew token Failed");
        }

        System.out.println("access_token : "+response.path("access_token"));
        return response;
    }


}
