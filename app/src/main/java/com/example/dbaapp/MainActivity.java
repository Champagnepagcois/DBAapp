package com.example.dbaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Delcarar componentes de XML
    TextView textView1;
    //Declarar conexión
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) this.findViewById(R.id.id_textview1);

        //Abrir o crear la DB
        db = this.openOrCreateDatabase("mybase.db", MODE_PRIVATE, null);

        //Crear estructura
        createTables();

        //Insertar datos
        insertRecords();

        //Actualizar datos
        updateRecords();

        //Borrar datos
        //credeleteRecords();

        //Leer datos
        selectRecords();

        //Cerrar la DB
        if(db != null) {
            db.close();
        }
    }

    private void deleteRecords() {
        String strSql = "DELETE FROM phone WHERE _id=1;";


        db.execSQL(strSql);
    }

    private void updateRecords() {
        String strSql = "UPDATE phone SET phone='1122334455' WHERE _id=13";
        db.execSQL(strSql);
    }

    private void selectRecords() {
        String strSql = "SELECT _id, name, phone FROM phone";
        //Declarar el Cursor
        Cursor cursor;
        cursor = db.rawQuery(strSql,null);
        String data = "";
        while (cursor.moveToNext()) {
            //Recuperar datos por posición
            data += cursor.getInt(0) + " | " + cursor.getString(1) + " | " + cursor.getString(2) + "\n\r";
            //Recuperar por nombre de columna
//            data += cursor.getInt(cursor.getColumnIndex("_id")) + " | " + getString(cursor.getColumnIndex("name")) + " | " + cursor.getString(cursor.getColumnIndex("phone")) + "\n\r";
        }

        textView1.setText(data);
    }

    private void insertRecords() {
        String strSql4 = "INSERT INTO phone (name, phone) VALUES ('MArlon', '5615088526');";
        db.execSQL(strSql4);

        String strSql = "INSERT INTO phone (name, phone) VALUES ('Juan', '5588994455');";
        db.execSQL(strSql);

        String strSql2 = "INSERT INTO phone (name, phone) VALUES ('Jimmy', '5566449955');";
        db.execSQL(strSql2);

        String strSql3 = "INSERT INTO phone (name, phone) VALUES ('Alma', '5566441122');";
        db.execSQL(strSql3);
    }

    private void createTables() {
        String strSql = "CREATE TABLE IF NOT EXISTS phone (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "phone TEXT" +
                ");";

        db.execSQL(strSql);
    }
}


