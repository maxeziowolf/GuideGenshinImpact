package com.example.guidegenshinimpact.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.models.character.CharacterGenshin;
import com.example.guidegenshinimpact.ui.main.MainActivity;
import com.example.guidegenshinimpact.utils.Singleton;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    //Variables
    private final String menssageError = "No se pudo cargar la información. Verifique su internet.";
    private final String messageNoInternet = "No tiene conexión a internet, verifique su conexión. ";
    private SplashScreenViewModel model;
    private TextView tvNotificationInternet;
    private int numProcess = 2;
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();
            onNetworkChange(ni);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Singleton.getInstance();
        getSupportActionBar().hide();
        tvNotificationInternet = findViewById(R.id.tvNotificationInternet);
        initModel();
    }

    private void initModel() {
        model = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        model.getNumProcess().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 0){
                    tvNotificationInternet.setVisibility(View.VISIBLE);
                    showMessage(menssageError);
                }else if(integer == numProcess){
                    changeHome();
                }
            }
        });
    }

    private void showMessage(String menssage) {
        Toast.makeText(getBaseContext(), menssage,Toast.LENGTH_LONG).show();
    }


    private void changeHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(networkStateReceiver);
    }

    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                model.findCharacterInfo();
                tvNotificationInternet.setVisibility(View.GONE);
            } else {
                showMessage(messageNoInternet);
                tvNotificationInternet.setVisibility(View.VISIBLE);

            }
        }
    }




}