package com.example.guidegenshinimpact.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guidegenshinimpact.api.APIConection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> charactersList = new MutableLiveData<>();
    private APIConection apiConection = new APIConection();

    public MutableLiveData<ArrayList<String>> getCharactersList() {
        return charactersList;
    }

    public void getAllCharactersNames(){
        apiConection.getAllCharacers(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                charactersList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                charactersList.setValue(null);
            }
        });
    }
}