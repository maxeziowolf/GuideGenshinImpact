package com.example.guidegenshinimpact.splash;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guidegenshinimpact.api.APIConection;
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.utils.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenViewModel extends ViewModel {
    private final String prefix = "https://api.genshin.dev/characters/";
    private final String sufixIconAether = "/icon-big-aether.png";
    private final String sufixBigIcon = "/icon-big.png";
    private final String sufixIcon = "/icon.png";
    private final String sufixSplash = "gacha-splash.png";
    private final String sufixSplashAether = "/portraitf.png";
    private MutableLiveData<ArrayList<CharacterGenshin>> listCharacters = new MutableLiveData<>();
    private APIConection apiConection = new APIConection();
    private ArrayList<CharacterGenshin> listCharactersAux;
    private int contador;

    //Getter
    public MutableLiveData<ArrayList<CharacterGenshin>> getListCharacters() {
        return listCharacters;
    }

    //Function
    protected void findCharacterInfo(){
        apiConection.getAllCharacers(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                Singleton.getInstance().setListNames(response.body());
                findInfo(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                listCharacters.setValue(null);
            }
        });
    }

    private void findInfo(ArrayList<String> listNames){
        listCharactersAux = new ArrayList<>();
        contador = listNames.size();
        for (String name: listNames) {
            find(name,"es");
        }
    }

    private void  find(String name,String codeLLang){
        apiConection.getCharacter(name,codeLLang, new Callback<CharacterGenshin>() {
            @Override
            public void onResponse(Call<CharacterGenshin> call, Response<CharacterGenshin> response) {
                if(response.isSuccessful()){
                    CharacterGenshin characterGenshin = response.body();
                    if(name.contains("traveler")){
                        characterGenshin.setBigIcon(prefix+name+sufixIconAether);
                        characterGenshin.setIcon(prefix+name+sufixIconAether);
                        characterGenshin.setGachaSplash(prefix+name+sufixSplashAether);
                    }else{
                        characterGenshin.setBigIcon(prefix+name+sufixBigIcon);
                        characterGenshin.setIcon(prefix+name+sufixIcon);
                        characterGenshin.setGachaSplash(prefix+name+sufixSplash);
                    }
                    listCharactersAux.add(characterGenshin);
                    contador--;
                }else{
                    find(name,"");
                }
                if(contador == 0){
                    listCharacters.setValue(listCharactersAux);
                }
            }

            @Override
            public void onFailure(Call<CharacterGenshin> call, Throwable t) {

            }
        });
    }
}
