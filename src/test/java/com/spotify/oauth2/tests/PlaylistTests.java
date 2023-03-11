package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationapi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist Api")
public class PlaylistTests {


    public Playlist playlistBuilder(String name,String description,boolean _public){
        return  Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }


    public void assertPlaylistEqual(Playlist responsePlaylist,Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
    }


    public void assertStatusCode(int actualStatusCode,int expectedStatusCode){
        assertThat(actualStatusCode,equalTo(expectedStatusCode));
    }

    public void assertError(Error responseErr,int expectedStatusCode,String expectedMsg){
        assertThat(responseErr.getInnerError().getStatus(),equalTo(expectedStatusCode));
        assertThat(responseErr.getInnerError().getMessage(),equalTo(expectedMsg));
    }

    @Story("Create Playlist")
    @Test(description = "TC_001_Validate Create Playlist")
    public void validateCreatePlaylist(){
        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);

        Response response=PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
        Playlist responsePlaylist=response.as(Playlist.class);

        assertPlaylistEqual(responsePlaylist,requestPlaylist);
    }

    @Test(description = "TC_002_Validate Get Playlist")
    public void validateGetPlaylist(){

        Playlist requestPlaylist=playlistBuilder("New Playlist","New playlist description",false);

        Response response=PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());
        Playlist responsePlaylist=response.as(Playlist.class);

        assertPlaylistEqual(responsePlaylist,requestPlaylist);
    }

    @Test(description = "TC_003_Validate Update Playlist")
    public void validateUpdatePlaylist(){

        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);


        Response response=PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());


    }


    @Story("Create Playlist")
    @Test(description = "TC_004_Validate Create Playlist without name")
    public void validateCreatePlaylistWithoutName(){
        Playlist requestPlaylist=playlistBuilder("",FakerUtils.generateDescription(),false);

        Response response=PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),400);
        Error error=response.as(Error.class);

        assertError(error,StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMsg());


    }

    @Story("Create Playlist")
    @Test(description = "TC_005_Validate Create Playlist with expired token")
    public void validateCreatePlaylistWithExpiredToken(){
        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);

        Response response=PlaylistApi.post("1234",requestPlaylist);
        assertStatusCode(response.statusCode(),401);
        Error error=response.as(Error.class);

        assertError(error,StatusCode.CODE_401.getCode(),StatusCode.CODE_401.getMsg());

    }



}
