package com.example.guidegenshinimpact.api;

import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.models.element.Element;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

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

    public void getElements(Callback<ArrayList<String>> response) {
        Call<ArrayList<String>> callCharacters = apiInterface.doGetInformation("elements");
        callCharacters.enqueue(response);
    }

    public void getElement(String name,String codeLang,Callback<Element> response){
        if (!codeLang.trim().equals("")){
            Call<Element> callCharacter = apiInterface.doGetInformationParticularElement("elements",name,codeLang);
            callCharacter.enqueue(response);
        }else{
            Call<Element> callCharacter = apiInterface.doGetInformationParticularElement("elements",name);
            callCharacter.enqueue(response);
        }
    }
}
