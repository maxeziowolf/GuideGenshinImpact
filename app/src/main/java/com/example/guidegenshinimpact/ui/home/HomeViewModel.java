package com.example.guidegenshinimpact.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.utils.Singleton;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> charactersListNames = new MutableLiveData<>();
    private MutableLiveData<ArrayList<CharacterGenshin>> charactersList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<String>> getCharactersListNames() {
        return charactersListNames;
    }

    public MutableLiveData<ArrayList<CharacterGenshin>> getCharactersList() {
        return charactersList;
    }

    public void getAllCharactersNames(){
        charactersListNames.setValue(Singleton.getInstance().getListNames());
    }

    public void getAllCharacters(){
        charactersList.setValue(Singleton.getInstance().getListCharacters());
    }
}
