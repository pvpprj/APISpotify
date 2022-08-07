package com.spotify.outh2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.outh2.api.Route.BASE_PATH;

public class specBuilder {
    public static RequestSpecification getrequestSpec()
    {
        return new RequestSpecBuilder().
                //           setBaseUri(System.getProperty("BASE_URI")).
           setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }
    public static ResponseSpecification getresponseSpec()
    {
        return new ResponseSpecBuilder().
                        log(LogDetail.ALL).build();

    }



}
