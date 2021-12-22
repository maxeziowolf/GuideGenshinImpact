package com.example.guidegenshinimpact.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.ui.main.MainActivity;
import com.example.guidegenshinimpact.utils.Singleton;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    //Variables
    private SplashScreenViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Singleton.getInstance();
        getSupportActionBar().hide();
        initModel();
    }

    private void initModel() {
        model = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        model.getListCharacters().observe(this, new Observer<ArrayList<CharacterGenshin>>() {
            @Override
            public void onChanged(ArrayList<CharacterGenshin> characterGenshins) {
                if (characterGenshins != null ){
                    Singleton.getInstance().setListCharacters(characterGenshins);
                }else{
                    Singleton.getInstance().setListCharacters(null);
                }
                changeHome();
            }
        });
        model.findCharacterInfo();
    }

    private void changeHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}