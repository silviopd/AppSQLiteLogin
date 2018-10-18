package com.example.user.appsqlitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 01/02/2016.
 */
public class accesoBD extends SQLiteOpenHelper {

    static String Nombre_Bd="usuarios.db";
    String Nombre_Tabla="Usuarios";
    static int Version_Bd=1;
    String Tabla_Usuario="CREATE TABLE Usuarios(id integer PRIMARY KEY autoincrement,usuario Text ,nombres TEXT,clave TEXT,sexo TEXT)";

    public accesoBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,Nombre_Bd, null, Version_Bd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla_Usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Nombre_Tabla);
    }

    public void agregarUsuario(String Usu,String Nom,String Cla,String Se){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("usuario",Usu);
        cv.put("nombres",Nom);
        cv.put("clave",Cla);
        cv.put("sexo",Se);

        db.insert("Usuarios",null,cv);
        db.close();
    }

    public boolean buscar(String Usuario,String Clave){
        boolean estado = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;

        c=db.rawQuery("Select * from "+Nombre_Tabla+" where usuario='"+Usuario+"' and clave='"+Clave+"'",null);
        if (c.moveToFirst()){
            estado=true;
        }
        return estado;
    }
}
