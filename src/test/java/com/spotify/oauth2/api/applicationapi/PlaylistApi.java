package com.spotify.oauth2.api.applicationapi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static io.restassured.RestAssured.given;

public class PlaylistApi {


    public static Response post(Playlist requestPlaylist){
         return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS, TokenManager.getToken(),requestPlaylist);

    }

    public static Response post(String token,Playlist requestPlaylist){
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS,token,requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS+"/"+playlistId,TokenManager.getToken());
    }

    public static Response update(String playlistId,Playlist requestPlaylist){
        return RestResource.update(PLAYLISTS+"/"+playlistId,TokenManager.getToken(),requestPlaylist);
    }
}
