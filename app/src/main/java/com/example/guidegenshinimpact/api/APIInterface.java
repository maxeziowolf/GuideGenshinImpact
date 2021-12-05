package com.example.guidegenshinimpact.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/{opcion}")
    Call<ArrayList<String>> doGetInformation(@Path("opcion") String opcion);

    @GET("/{opcion}/{name}")
    Call<ArrayList<String>> doGetInformationParticular(@Path("opcion") String opcion,@Path("name") String name);


}
