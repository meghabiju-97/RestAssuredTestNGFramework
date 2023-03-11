package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String path,String token,Object requestPlaylist){
         return given(SpecBuilder.getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
                when().
                post(path).
                then().
                spec(SpecBuilder.getResponseSpec()).
                extract().
                response();

    }

    public static Response postAccount(HashMap<String,String> formParams){
        return given(SpecBuilder.getAccountRequestSpec()).
                header("Authorization", ConfigLoader.getInstance().getAuthorization()).
                formParams(formParams).
                when().
                post(API+TOKEN).
                then().
                spec(SpecBuilder.getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path,String token){
        return given(SpecBuilder.getRequestSpec()).
                auth().oauth2(token).
                when().
                get(path).
                then().
                spec(SpecBuilder.getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path,String token,Object requestPlaylist){
        return given(SpecBuilder.getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
                when().
                put(path).
                then().
                spec(SpecBuilder.getResponseSpec()).
                extract().
                response();
    }
}
