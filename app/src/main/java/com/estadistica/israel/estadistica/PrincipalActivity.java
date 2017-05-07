package com.estadistica.israel.estadistica;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;


public class PrincipalActivity extends ActionBarActivity {


//    private static final String DB_NAME="nombrecamisetadb";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);
       /* UsuariosSQLiteHelper db2 = new UsuariosSQLiteHelper(this, DB_NAME, null, 1);
        try {
            db2.createDataBase();
            db2.openDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal2, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        *//*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*//*
    }*/

    public void nuevaEstadistica(View v) {

        Intent intento = new Intent(this, JugadoresEquiposNuevosActivity.class);
        startActivity(intento);
    }

    public void listarEstadisticas(View v) {

        Intent intento = new Intent(this, ListViewActivity.class);
        startActivity(intento);
    }


}
