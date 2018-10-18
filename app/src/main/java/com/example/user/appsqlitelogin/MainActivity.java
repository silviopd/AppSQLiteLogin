package com.example.user.appsqlitelogin;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mAcceder,mRegistro;
    EditText mUsuario,mClave;
    accesoBD miBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAcceder=(Button) findViewById(R.id.btnAccederLogin);
        mRegistro=(Button) findViewById(R.id.btnRegistrarLogin);

        mUsuario = (EditText) findViewById(R.id.txtUsuarioLogin);
        mClave = (EditText) findViewById(R.id.txtContraseñaLogin);

        miBD = new accesoBD(this.getApplicationContext(),"",null,0);

        mAcceder.setOnClickListener(this);
        mRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.btnAccederLogin:
                acceder(v);
                break;
            case R.id.btnRegistrarLogin:
                Intent intent = new Intent(this,RegistroActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void acceder(View view) {
        String usu, cla;
        AlertDialog dialogo = new AlertDialog.Builder(this).create();

        usu = mUsuario.getText().toString();
        cla = mClave.getText().toString();

        if (usu.isEmpty() || cla.isEmpty()) {
            dialogo.setTitle("Campo Usuario Vacio");
            dialogo.show();
            return;
        } else {

            boolean respuesta;

            respuesta = miBD.buscar(usu, cla);

            if (respuesta == true) {
                dialogo.setTitle("Acceso Permitido");
                dialogo.show();
            } else {
                dialogo.setTitle("Acceso Denegado");
                dialogo.show();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mUsuario.setText("");
        mClave.setText("");
    }
}
