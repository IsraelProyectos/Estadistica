package com.estadistica.israel.estadistica;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class JugadoresEquiposNuevosActivity extends Activity implements OnClickListener {

    public String nombreTabla = "Otramas";
    public String nombreEquipo = "Raro";
    UsuariosSQLiteHelper usdbh =
            new UsuariosSQLiteHelper(this, nombreEquipo, null, 1);

    //String[] dbList = getApplicationContext().databaseList();
    private EditText introducirDorsal;
    private EditText intrNom;
    private TextView bD;
    private Button enviar;
    public static Activity ma;
    public int camiseta;
    public String color;

    private Spinner lista;
    //TODO limitar numero de letras en el nombre del jugador
    //TODO hacer activity para darle nombres diferentes a BD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_equpos_nuevos);
        ma = this;

       if(nombreTabla !=null){

            usdbh.tablaNueva(nombreTabla);
        }



        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();


        datos.add(new Lista_entrada(R.drawable.camisetablanca, null, null));
        datos.add(new Lista_entrada(R.drawable.camisetadoradovioleta, null, null));
        datos.add(new Lista_entrada(R.drawable.camisetarojaamarilla, null, null));
        datos.add(new Lista_entrada(R.drawable.camisetanegra, null, null));

        lista = (Spinner) findViewById(R.id.spinner_conListadoPersonalizado);
        lista.setAdapter(new Lista_adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {


                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
                }
            }
        });

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion);
                camiseta = elegido.get_idImagen();
                colorNumero();
            }

            @Override
            public void onNothingSelected(AdapterView<?> pariente) {
            }
        });

        introducirDorsal = (EditText) findViewById(R.id.introducirDorsal);
        intrNom = (EditText) findViewById(R.id.introducirNombre);
        bD = (TextView) findViewById(R.id.textView5);
        enviar = (Button) findViewById(R.id.btnCrearBoton);

        //bD.setText(dbList.toString());
        enviar.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String dorsal = introducirDorsal.getText().toString();
        if (v == enviar) {
            if (dorsal.matches("")) {
                Toast.makeText(this, "Debes introducir un dorsal", Toast.LENGTH_SHORT).show();
                introducirDorsal.setText("");
            } else
                comprobarDorsal(v);
        }


    }

    public void comprobarDorsal(View v) {
        String dorsal = introducirDorsal.getText().toString();
        int dorsalInteger = Integer.parseInt(dorsal);

        SQLiteDatabase dbs = usdbh.getWritableDatabase();

        Cursor numeroFilas = dbs.rawQuery("select dorsalJugador from "+nombreTabla, null);
        int totalFilas = numeroFilas.getCount();
        if (totalFilas > 0) {
            int contadorFilas = 1;
            for (int i = 0; i < totalFilas; i++) {


                Cursor fila = dbs.rawQuery("select dorsalJugador from "+nombreTabla+" WHERE id=" + contadorFilas, null);

                if (fila.moveToFirst()) {

                    if (dorsalInteger == fila.getInt(0)) {
                        Toast.makeText(this, "Ese dorsal ya lo has introducido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    contadorFilas++;

                }
            }
        }
        llenarBaseDeDatos(v);
    }

    public void llenarBaseDeDatos(View v) {

        SQLiteDatabase dbs = usdbh.getWritableDatabase();


        dbs.execSQL("INSERT INTO " + nombreTabla + " (dorsalJugador, nombreJugador, totalPuntos, aciertoDos, falloDos, porcentajeDos, " +
                "anotadosDe3Text, totalTirosDe3, porcAciertoDe3," +
                "anotadosDe1Text, totalTirosDe1, porcAciertoDe1," +
                "rebotesDefensivos, rebotesOfensivos, rebotesTotales," +
                "asistencias, recuperaciones, perdidas, taponesRealizados," +
                "taponesRecibidos, faltasCometidas, faltasRecibidas, valoracion) " +
                "VALUES (" + introducirDorsal.getText() + ",'" + intrNom.getText() + "',0, 0, 0, '0', 0, 0, '0', 0, 0, '0', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
        introducirDorsal.setText("");
        intrNom.setText("");
        Toast.makeText(this, "Datos introducidos",
                Toast.LENGTH_SHORT).show();
    }


    public void colorNumero(){

        if(camiseta==2130837563){
            color = "black";
        }else if(camiseta==2130837564) {
            color = "white";
        }else if(camiseta==2130837565) {
            color = "yellow";
        }else if(camiseta==2130837566) {
            color = "white";
        }
    }

    public void pasarActividad2(View view) {

        Intent act = new Intent(this, JugadorActivity.class);
        act.putExtra("miId", "1");
        act.putExtra("camiseta", camiseta);
        act.putExtra("color", color);
        startActivity(act);

    }


}
