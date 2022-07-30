package com.spotify.outh2.tests;

import com.spotify.outh2.api.StatusCode;
import com.spotify.outh2.api.applicationApi.PlayListApi;
import com.spotify.outh2.pojo.Error;
import com.spotify.outh2.pojo.Playlist;
import com.spotify.outh2.utils.Dataloader;
import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.outh2.api.specBuilder.*;
import static com.spotify.outh2.utils.FakerUtils.generateDescription;
import static com.spotify.outh2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("PlayList API")
public class playlistTest extends BaseTest {
/*
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQApUrTwoZbtCzqVbRso_QHCrQR4DxdSN2heVGOOy-SbvFfAMQ3tfAEXlqmBWLYtHVZ_PbkVV8yU7sfVO1FmtYQKP_lXUpl-gcgncB4EvZEQoTMFNmU6qCJCqOQjzhKJQimBrSRH5ysOFi9nZMYSZKdWIGcF0h_9kc0s7K-A-dd-unSy-fIXyB2HnO9MGJnVPV8M3greO82Z6RU4L5H8u96a0xX4JUXq3jVhmV0ik9Mbj2CzxklcGjlPVkF0Sppk49fPIkIUbgd0";
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
*/

    @Story("Create a PlayList Story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("This is the Description ")
    @Test(description = "Should Be Able To Create a PlayList")                                                     // Create playList 193
    public void shouldBeAbleToCreatePlayList()
    {
        Playlist requestPlaylist =  playlistBuilder(generateName() , generateDescription() , false );

//        Playlist requestPlaylist = new Playlist();     // PoJo class is created as PlayList
//        requestPlaylist.setName("New Playlist");
//        requestPlaylist.setDescription("New PlayList Description");
//        requestPlaylist.set_public(false);

       Response response = PlayListApi.post(requestPlaylist);
      //  assertThat(response.statusCode(),equalTo(201));
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        Playlist responseplaylist = response.as(Playlist.class);    // Playlist responseplaylist using for De-Serializeing
        assertplayListEqual(responseplaylist , requestPlaylist);

//    Playlist responseplaylist = given(getrequestSpec()).       //  Playlist responseplaylist using for De-Serializeing
//                body(requestPlaylist).
//        when().post("/users/z2692t0nymgelta3qk5bgl88b/playlists").
//        then().spec(getresponseSpec()).
//                assertThat().statusCode(201).
//                extract().response().
//                as(Playlist.class);
//

//        assertThat(responseplaylist.getName(),equalTo(requestPlaylist.getName()));
//        assertThat(responseplaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
//        assertThat(responseplaylist.getPublic(),equalTo(requestPlaylist.getPublic()));

    }

    @Test    // Get playList 194
    public void shouldBeAbleToGetPlayList()
    {
        Playlist requestPlaylist =  playlistBuilder("New Playlist" , "New PlayList Description" , false );

       Response response = PlayListApi.get(Dataloader.getInstance().getGetPlayListID());
        assertStatusCode(response.statusCode(),  StatusCode.CODE_200);
       Playlist responseplaylist = response.as(Playlist.class);    // Playlist responseplaylist using for De-Serializeing
        assertplayListEqual(responseplaylist , requestPlaylist);
    }

    @Test   // Update playList 195
    public void shouldBeAbleToUpdatePlayList_195()
    {
        Playlist requestPlaylist =  playlistBuilder(generateName() , generateDescription() , false );
        Response response = PlayListApi.update(Dataloader.getInstance().getUpdateplayList(),requestPlaylist);
        assertStatusCode(response.statusCode(),  StatusCode.CODE_200);
    }

    @Story("Create a PlayList Story")
    @Test     // Automate Negative Test cases 196 as not name mention
    public void shouldBeAbleToCreatePlayListWith_Empty_Name()
    {
        Playlist requestPlaylist =  playlistBuilder("" ,  generateDescription() , false );
      Response response = PlayListApi.post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_400.code));
        Error error= response.as(Error.class);    // Playlist responseplaylist using for De-Serializeing
        assertError(error , StatusCode.CODE_400);
    }

    @Story("Create a PlayList Story")
    @Test     // Automate Negative Test cases 196
    public void shouldBeAbleToCreatePlayListWithExpiredToken()
    {
        Playlist requestPlaylist =  playlistBuilder(generateName() , generateDescription() , false );
         String inValid_Token = "12345";
        Response response = PlayListApi.post(inValid_Token , requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401);
        Error error= response.as(Error.class);    // Playlist responseplaylist using for De-Serializeing
        assertError(error , StatusCode.CODE_401);
    }

    // Optimizing Code ---->
    @Step
    public  void assertplayListEqual(Playlist responseplaylist , Playlist requestPlaylist)
    {
        assertThat(responseplaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responseplaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        assertThat(responseplaylist.get_public(),equalTo(requestPlaylist.get_public()));
    }
    @Step
    public void assertStatusCode(int actualStatusCode , StatusCode statusCode)
    {
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
    @Step
    public void assertError( Error responseError ,StatusCode statusCode)
    {
        assertThat(responseError.getError().getStatus(),equalTo(statusCode.code));
        assertThat(responseError.getError().getMessage(),equalTo(statusCode.msg));

    }


    @Step
    public Playlist playlistBuilder(String name ,String description , boolean _public)
    {
        /*
        return Playlist.builder().         // Using with @Data at Playlist class
                name(name).
                description(description).
                _public(_public).
                build();
*/

        Playlist requestPlaylist = new Playlist();     // PoJo class is created as PlayList
        requestPlaylist.setName(name);
        requestPlaylist.setDescription(description);
        requestPlaylist.set_public(_public);
        return  requestPlaylist;
    }

}
