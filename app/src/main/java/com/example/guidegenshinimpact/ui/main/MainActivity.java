package com.example.guidegenshinimpact.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.api.APIClient;
import com.example.guidegenshinimpact.api.APIInterface;
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.ui.MenuDrawerActivity;
import com.example.guidegenshinimpact.utils.Singleton;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIInterface apiInterface;
    private TextView tvcharacter;
    private Button btnPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcharacter = findViewById(R.id.hola);
        btnPersonajes = findViewById(R.id.btnPersonajes);

        tvcharacter.setText(String.valueOf(Singleton.getInstance().getListCharacters().size()));
        btnPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MenuDrawerActivity.class);
                startActivity(intent);
            }
        });
    }


}