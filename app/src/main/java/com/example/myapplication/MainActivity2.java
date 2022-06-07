package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button salir, json, sqlite, sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        salir=findViewById(R.id.button);
        json=findViewById(R.id.btnjson);
        sqlite=findViewById(R.id.btnsqlite);

        sensor=findViewById(R.id.btnsensor);
        preferences=this.getSharedPreferences("session", Context.MODE_PRIVATE);
        editor= preferences.edit();

        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sensor= new Intent(MainActivity2.this, Sensor.class);
                startActivity(sensor);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("session",false);
                editor.apply();
                Toast.makeText(MainActivity2.this, "La sesion ha sido cerrada", Toast.LENGTH_SHORT).show();
            }
        });

        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent2);

            }
        });
        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSQL=new Intent(MainActivity2.this, SQLite.class);
                startActivity(intentSQL);
            }
        });



    }
}