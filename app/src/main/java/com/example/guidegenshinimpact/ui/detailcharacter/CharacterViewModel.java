package com.example.guidegenshinimpact.ui.detailcharacter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guidegenshinimpact.api.APIConection;
import com.example.guidegenshinimpact.models.CharacterGenshin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    private MutableLiveData<CharacterGenshin> characterGenshin = new MutableLiveData<>();
    private APIConection apiConection = new APIConection();

    public MutableLiveData<CharacterGenshin> getCharacterGenshin() {
        return characterGenshin;
    }

    public  void findCharacter(String name){
    }
}