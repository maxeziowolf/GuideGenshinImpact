package com.example.guidegenshinimpact.api;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIConection {
    private APIInterface apiInterface;

    public APIConection() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void getAllCharacers(Callback<ArrayList<String>> response) {
        Call<ArrayList<String>> callCharacter = apiInterface.doGetInformation("characters");
        callCharacter.enqueue(response);
    }
}
