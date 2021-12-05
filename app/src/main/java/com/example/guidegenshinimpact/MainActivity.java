package com.example.guidegenshinimpact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guidegenshinimpact.api.APIClient;
import com.example.guidegenshinimpact.api.APIInterface;
import com.example.guidegenshinimpact.ui.MenuDrawerActivity;

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
        apiInterface = APIClient.getClient().create(APIInterface.class);
        getAllCharacers();
        tvcharacter = findViewById(R.id.hola);
        btnPersonajes = findViewById(R.id.btnPersonajes);

        btnPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MenuDrawerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAllCharacers() {
        Call<ArrayList<String>> callCharacter = apiInterface.doGetInformationParticular("characters","lisa");

        callCharacter.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ArrayList<String> listaCharacter = response.body();
                Log.d("Character",listaCharacter.toString());
                int i =0;
                String data = "";
                for (String character : listaCharacter) {
                    i++;
                    data += i +".-"+character +"\n";
                }
                tvcharacter.setText(data);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("Error",call.toString());
            }
        });
    }
}