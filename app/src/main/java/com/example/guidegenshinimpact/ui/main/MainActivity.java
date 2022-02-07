package com.example.guidegenshinimpact.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.ui.MenuDrawerActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvcharacter;
    private Button btnPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPersonajes = findViewById(R.id.btnPersonajes);

        btnPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MenuDrawerActivity.class);
                startActivity(intent);
            }
        });
    }


}