package com.example.guidegenshinimpact.api;

import android.util.Log;

import com.example.guidegenshinimpact.models.CharacterGenshin;

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
        Call<ArrayList<String>> callCharacters = apiInterface.doGetInformation("characters");
        callCharacters.enqueue(response);
    }
    public void getCharacter(String name,String codeLang,Callback<CharacterGenshin> response){
        if (!codeLang.trim().equals("")){
            Call<CharacterGenshin> callCharacter = apiInterface.doGetInformationParticular("characters",name,codeLang);
            callCharacter.enqueue(response);
        }else{
            Call<CharacterGenshin> callCharacter = apiInterface.doGetInformationParticular("characters",name);
            callCharacter.enqueue(response);
        }
    }
}
