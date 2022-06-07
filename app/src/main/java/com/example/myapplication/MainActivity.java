package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    CheckBox guardar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave="session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarElementos();
        if (revisarSesion()){
            startActivity(new Intent(this, MainActivity2.class));
        }else{
            Toast.makeText(this, "Inicie Sesión", Toast.LENGTH_SHORT).show();
        }

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarSesion(guardar.isChecked());
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

            }
        });
    }

    private boolean revisarSesion(){
        return this.preferences.getBoolean(llave,false);
    }

    private void guardarSesion(boolean checked){
        editor.putBoolean(llave,checked);
        editor.putString("nombre","miguel");
        editor.apply();

    }

    private void inicializarElementos(){
        preferences=this.getSharedPreferences("session",Context.MODE_PRIVATE);
        editor= preferences.edit();

        btnIniciar=findViewById(R.id.btninicio);
        guardar=findViewById(R.id.checkBox);

    }
}