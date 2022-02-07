package com.example.guidegenshinimpact.ui.splash;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guidegenshinimpact.api.APIConection;
import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.models.element.Element;
import com.example.guidegenshinimpact.utils.Singleton;
import com.example.guidegenshinimpact.utils.StringFormatter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenViewModel extends ViewModel {
    private final String prefix = "https://api.genshin.dev/characters/";
    private final String prefix2 = "https://api.genshin.dev/elements/";
    private final String sufixIconAether = "/icon-big-aether.png";
    private final String sufixBigIcon = "/icon-big.png";
    private final String sufixIcon = "/icon.png";
    private final String sufixSplash = "/gacha-splash.png";
    private final String sufixSplashAether = "/portraitf.png";
    private final String sufixPortrait = "/portrait.png";
    private APIConection apiConection = new APIConection();
    private MutableLiveData<Integer> numProcess = new MutableLiveData<>();
    private ArrayList<CharacterGenshin> listCharactersAux;
    private ArrayList<Element> listElementArrayList;
    private int contador;
    private int contador2;

    //Getter


    public MutableLiveData<Integer> getNumProcess() {
        return numProcess;
    }

    //Function
    protected void findCharacterInfo(){
        apiConection.getAllCharacers(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                findElements();
                Singleton.getInstance().setListNames(response.body());
                findInfo(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                numProcess.setValue(0);
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
                        characterGenshin.setPortrait(prefix+name+sufixSplashAether);
                    }else{
                        characterGenshin.setBigIcon(prefix+name+sufixBigIcon);
                        characterGenshin.setIcon(prefix+name+sufixIcon);
                        characterGenshin.setGachaSplash(prefix+name+sufixSplash);
                        characterGenshin.setPortrait(prefix+name+sufixPortrait);
                    }
                    characterGenshin.setName(StringFormatter.upperCaseFirst(name));
                    listCharactersAux.add(characterGenshin);
                    contador--;
                }else{
                    find(name,"");
                }
                if(contador == 0){
                    Singleton.getInstance().setListCharacters(listCharactersAux);
                    numProcess.setValue(1);
                }
            }

            @Override
            public void onFailure(Call<CharacterGenshin> call, Throwable t) {

            }
        });
    }

    private void findElements(){
        apiConection.getElements(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                findElementInfo(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                numProcess.setValue(0);
            }
        });
    }

    private void findElementInfo(ArrayList<String> elementsList) {
        listElementArrayList = new ArrayList<>();
        contador2 = 0;
        contador2 = elementsList.size();
        Singleton.getInstance().setListElementsNames(elementsList);
        for (String name : elementsList) {
            findElement(name, "es");
        }
    }

    private void  findElement(String name,String codeLLang){
        apiConection.getElement(name, codeLLang, new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if(response.isSuccessful()){
                    response.body().setIcon(prefix2+name+sufixIcon);
                    listElementArrayList.add(response.body());
                    contador2--;
                }else{
                    findElement(name,"");
                }
                if(contador2 == 0){
                    Singleton.getInstance().setListElements(listElementArrayList);
                    numProcess.setValue(2);
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
            }
        });
    }

}
