package fr.eg23.wargame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Game.round = 0;


    }

    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}