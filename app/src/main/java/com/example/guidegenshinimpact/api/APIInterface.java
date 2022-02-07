package com.example.guidegenshinimpact.api;

import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.models.element.Element;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/{opcion}")
    Call<ArrayList<String>> doGetInformation(@Path("opcion") String opcion);

    @GET("/{opcion}/{name}")
    Call<CharacterGenshin> doGetInformationParticular(@Path("opcion") String opcion, @Path("name") String name, @Query("lang") String language);

    @GET("/{opcion}/{name}")
    Call<CharacterGenshin> doGetInformationParticular(@Path("opcion") String opcion, @Path("name") String name);

    @GET("/{opcion}/{name}")
    Call<Element> doGetInformationParticularElement(@Path("opcion") String opcion, @Path("name") String name, @Query("lang") String language);

    @GET("/{opcion}/{name}")
    Call<Element> doGetInformationParticularElement(@Path("opcion") String opcion, @Path("name") String name);

}
