package com.example.user.appsqlitelogin;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegistroActivity extends AppCompatActivity {

    EditText mNombres,mUsuario,mClave;
    RadioButton mMasculino,mFemenino;
    CheckBox mAcuerdo;
    accesoBD miBD;

    Button mRegistrar,mLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mNombres = (EditText) findViewById(R.id.txtNombreRegistro);
        mUsuario = (EditText) findViewById(R.id.txtUsuarioRegistro);
        mClave = (EditText) findViewById(R.id.txtClaveRegistro);
        mMasculino = (RadioButton) findViewById(R.id.rbMasculinoRegistro);
        mFemenino = (RadioButton) findViewById(R.id.rbFemeninoRegistro);

        mAcuerdo = (CheckBox) findViewById(R.id.chkAcuerdoRegistro);

        mRegistrar = (Button) findViewById(R.id.btnRegistrarRegistro);
        mLimpiar = (Button) findViewById(R.id.btnLimpiarRegistro);

        miBD = new accesoBD(this.getApplicationContext(),"",null,0);


        mAcuerdo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mRegistrar.setEnabled(true);
                    mRegistrar.setBackgroundColor(getResources().getColor(R.color.habilitado));
                }else{
                    mRegistrar.setEnabled(false);
                    mRegistrar.setBackgroundColor(getResources().getColor(R.color.deshabilitado));
                }
            }
        });

    }



    public void registrarUsuario(View view){
        String nom,usu,cla,sex="";
        AlertDialog dialogo = new AlertDialog.Builder(this).create();

            nom=mNombres.getText().toString();
            usu=mUsuario.getText().toString();
            cla=mClave.getText().toString();


            if (mMasculino.isSelected()){
                sex="Masculino";
            }else if (mFemenino.isSelected()){
                sex="Femenino";
            }

            miBD.agregarUsuario(usu,nom,cla,sex);

            dialogo.setTitle("Usuario Agregado");
            dialogo.show();

            finish();
    }

    public void limpiar(View view){
        mNombres.setText("");
        mUsuario.setText("");
        mClave.setText("");
        mMasculino.setSelected(true);
        mAcuerdo.setSelected(false);
    }
}
