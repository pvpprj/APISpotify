package com.spotify.outh2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class playlistTest_193_196 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQD_Zgo11eDnuPBV0IUWfUk4SxunnYhE5G-FwNkwmjnHZGJ65KWa0ybAdkudnf_HEIOOktWj23DFkLm40dZMdZROAfbD6CkOcQxTvbNBVohshQIgVNiLujipdPsBFjlZoPN82Q7sSbQg-MNXose-LIDzqRjRLK4ZrOWvq41zayitr-5l6HFcYRED2UX7czbjVPIF-xPlcp9ThjHRVmmhfZPpNHAKEZFV5seH4CF0PoczzSf7lo3v7Ho4_C2nwRw7g6pGpn0WViNh";
    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization","Bearer "+access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
//              expectStatusCode(200).
 //             expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test     // Create playList 193
    public void shouldBeAbleToCreatePlayList()
    {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
        when().post("/users/z2692t0nymgelta3qk5bgl88b/playlists").
        then().spec(responseSpecification).
                assertThat().statusCode(201).
                body("name",equalTo("New Playlist"),
                        "description",equalTo("New playlist description"),
                        "public",equalTo(false)
                    );

    }

    @Test    // Get playList 194
    public void shouldBeAbleToGetPlayList()
    {
        given(requestSpecification).
                when().get("/playlists/3gyE4lbLDIU9FCwIoARW0a").
                then().spec(responseSpecification).
                assertThat().statusCode(200).
                body("name",equalTo("New Playlist"),
                        "description",equalTo("New playlist description"),
                        "public",equalTo(false)
                );

    }

    @Test   // Update playList 195
    public void shouldBeAbleToUpdatePlayList_195()
    {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
        when().put("/playlists/1QHlZuyifASriuRfvEE8c6").
        then().spec(responseSpecification).
                assertThat().statusCode(200);

    }

    @Test     // Automate Negative Test cases 196
    public void shouldBeAbleToCreatePlayListWithName()
    {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
                when().post("/users/z2692t0nymgelta3qk5bgl88b/playlists").
                then().spec(responseSpecification).
                assertThat().statusCode(400).
                body("error.status",equalTo(400),
                "error.message",equalTo("Missing required field: name") );

    }

    @Test     // Automate Negative Test cases 196
    public void shouldBeAbleToCreatePlayListWithExpiredToken()
    {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization","Bearer "+"1555166").
                contentType(ContentType.JSON).
                log().all().
                body(payload).
        when().post("/users/z2692t0nymgelta3qk5bgl88b/playlists").
        then().spec(responseSpecification).
                assertThat().statusCode(401).
                body("error.status",equalTo(401),
                        "error.message",equalTo("Invalid access token") );

    }


}
