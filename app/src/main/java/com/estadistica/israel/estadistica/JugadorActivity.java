package com.estadistica.israel.estadistica;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DecimalFormat;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class JugadorActivity extends Activity implements OnClickListener {

    UsuariosSQLiteHelper usdbh =
            new UsuariosSQLiteHelper(this, "DBJugadores", null, 1);

    public int camiseta;
    public String color;
    public String id;
    public Button b;
    public Activity jA;
    private Button rebotesDefensivos,rebotesOfensivos, asist, recuperacionesBoton, perdidas, taponesRealizados,
                   taponesRecibidos, faltasCometidas, faltasRecibidas, salirAplicacion;

    private ImageButton fallo2, acierto2, acierto3, fallo3, acierto1, fallo1;
    TextView totalPuntos;

    TextView anotadosDe2;
    TextView totalTirosDe2;

    TextView anotadosDe3;
    TextView totalTirosDe3;

    TextView anotadosDe1;
    TextView totalTirosDe1;

    TextView porcAcierto2;
    TextView porcAcierto3;
    TextView porcAcierto1;
    TextView rD;
    TextView rO;
    TextView rT;
    TextView asistencias;
    TextView recuperaciones;
    TextView perdidasText;
    TextView taponesReali;
    TextView taponesReci;
    TextView faltasCom;
    TextView faltasRec;
    TextView valorac;
    TextView dorsal;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugador);

        JugadoresEquiposNuevosActivity.ma.finish();
        jA= this;
        id = getIntent().getStringExtra("miId");
        camiseta = getIntent().getIntExtra("camiseta", camiseta);
        color = getIntent().getStringExtra("color");
        crearBoton();
        acierto2 = (ImageButton) findViewById(R.id.aciertoDe2);
        fallo2 = (ImageButton) findViewById(R.id.falloDe2);
        acierto3 = (ImageButton) findViewById(R.id.aciertoDe3);
        fallo3 = (ImageButton) findViewById(R.id.falloDe3);
        acierto1 = (ImageButton) findViewById(R.id.aciertoDe1);
        fallo1 = (ImageButton) findViewById(R.id.falloDe1);
        rebotesDefensivos = (Button) findViewById(R.id.rebotesDefensivos);
        rebotesOfensivos = (Button) findViewById(R.id.rebotesOfensivos);
        asist = (Button) findViewById(R.id.asistencias);
        recuperacionesBoton = (Button) findViewById(R.id.recuperaciones);
        perdidas = (Button) findViewById(R.id.perdidas);
        taponesRealizados = (Button) findViewById(R.id.taponesRealizados);
        taponesRecibidos = (Button) findViewById(R.id.taponesRecibidos);
        faltasCometidas = (Button) findViewById(R.id.faltasCometidas);
        faltasRecibidas = (Button) findViewById(R.id.faltasRecibidas);
        salirAplicacion = (Button) findViewById(R.id.salir);


        totalPuntos = (TextView) findViewById(R.id.puntosAnotados);
        anotadosDe2 = (TextView) findViewById(R.id.anotadosDe2text);
        anotadosDe3 = (TextView) findViewById(R.id.anotadosDe3Text);
        anotadosDe1 = (TextView) findViewById(R.id.anotadosDe1Text);
        totalTirosDe2 = (TextView) findViewById(R.id.totalTirosDe2);
        totalTirosDe3 = (TextView) findViewById(R.id.totalTirosDe3);
        totalTirosDe1 = (TextView) findViewById(R.id.totalTirosDe1);
        rD = (TextView) findViewById(R.id.rebotesDefensivosText);
        rO = (TextView) findViewById(R.id.rebotesOfensivosText);
        rT = (TextView) findViewById(R.id.rebotesTotalesText);
        asistencias = (TextView) findViewById(R.id.asistenciasText);
        recuperaciones = (TextView) findViewById(R.id.recuperacionesText);
        perdidasText = (TextView) findViewById(R.id.perdidasText);
        taponesReali = (TextView) findViewById(R.id.taponesRealizadosText);
        taponesReci = (TextView) findViewById(R.id.taponesRecibidosText);
        faltasCom = (TextView) findViewById(R.id.faltasCometidasText);
        faltasRec = (TextView) findViewById(R.id.faltasRecibidasText);
        valorac = (TextView) findViewById(R.id.valoracionText);
        dorsal = (TextView) findViewById(R.id.dorsal);
        nombre = (TextView) findViewById(R.id.nombre);


        porcAcierto2 = (TextView) findViewById(R.id.porcAciertoDe2);
        porcAcierto3 = (TextView) findViewById(R.id.porcAciertoDe3);
        porcAcierto1 = (TextView) findViewById(R.id.porcAciertoDe1);

        acierto2.setOnClickListener(this);
        fallo2.setOnClickListener(this);
        acierto3.setOnClickListener(this);
        fallo3.setOnClickListener(this);
        acierto1.setOnClickListener(this);
        fallo1.setOnClickListener(this);
        rebotesDefensivos.setOnClickListener(this);
        rebotesOfensivos.setOnClickListener(this);
        asist.setOnClickListener(this);
        recuperacionesBoton.setOnClickListener(this);
        perdidas.setOnClickListener(this);
        taponesRealizados.setOnClickListener(this);
        taponesRecibidos.setOnClickListener(this);
        faltasCometidas.setOnClickListener(this);
        faltasRecibidas.setOnClickListener(this);
        salirAplicacion.setOnClickListener(this);

        //b.setVisibility(View.INVISIBLE);
        consulta();
    }


   /* @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        anotadosDe2.setText(savedInstanceState.getString("texto1"));
        totalTirosDe2.setText(savedInstanceState.getString("texto2"));
        Log.d("texto1", anotadosDe2.getText().toString() + " cargado");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("texto1", anotadosDe2.getText().toString());
        outState.putString("texto2", totalTirosDe2.getText().toString());
        Log.d("texto1", anotadosDe2.getText().toString() + " salvado");
    }*/


    @Override
    public void onClick(View v) {

        if (v == acierto2) {
            TextView tiradosDe2 = (TextView) findViewById(R.id.anotadosDe2text);
            tiradosDe2.setTag(String.valueOf("aciertoDos"));
            acierto(tiradosDe2);
            TextView totalTiros2 = (TextView) findViewById(R.id.totalTirosDe2);
            totalTiros2.setTag(String.valueOf("falloDos"));
            fallo(totalTiros2);
            porcentaje(tiradosDe2, totalTiros2, "porcentajeDos");

        } else if (v == fallo2) {
            TextView totalTiros2 = (TextView) findViewById(R.id.totalTirosDe2);
            totalTiros2.setTag(String.valueOf("falloDos"));
            fallo(totalTiros2);
            TextView tiradosDe2 = (TextView) findViewById(R.id.anotadosDe2text);
            porcentaje(tiradosDe2, totalTiros2, "porcentajeDos");
            calculoValoracion();
        } else if (v == acierto3) {
            TextView tiradosDe3 = (TextView) findViewById(R.id.anotadosDe3Text);
            tiradosDe3.setTag(String.valueOf("anotadosDe3Text"));
            acierto(tiradosDe3);
            TextView totalTiros3 = (TextView) findViewById(R.id.totalTirosDe3);
            totalTiros3.setTag(String.valueOf("totalTirosDe3"));
            fallo(totalTiros3);
            porcentaje(tiradosDe3, totalTiros3, "porcAciertoDe3");
        } else if (v == fallo3) {
            TextView fallos3Text = (TextView) findViewById(R.id.totalTirosDe3);
            fallos3Text.setTag(String.valueOf("totalTirosDe3"));
            fallo(fallos3Text);
            TextView tiradosDe3 = (TextView) findViewById(R.id.anotadosDe3Text);
            porcentaje(tiradosDe3, fallos3Text, "porcAciertoDe3");
            calculoValoracion();
        } else if (v == acierto1) {
            TextView tiradosDe1 = (TextView) findViewById(R.id.anotadosDe1Text);
            tiradosDe1.setTag(String.valueOf("anotadosDe1Text"));
            acierto(tiradosDe1);
            TextView totalTiros1 = (TextView) findViewById(R.id.totalTirosDe1);
            totalTiros1.setTag(String.valueOf("totalTirosDe1"));
            fallo(totalTiros1);
            porcentaje(tiradosDe1, totalTiros1, "porcAciertoDe1");
        } else if (v == fallo1) {
            TextView fallos1Text = (TextView) findViewById(R.id.totalTirosDe1);
            fallos1Text.setTag(String.valueOf("totalTirosDe1"));
            fallo(fallos1Text);
            TextView tiradosDe1 = (TextView) findViewById(R.id.anotadosDe1Text);
            porcentaje(tiradosDe1, fallos1Text, "porcAciertoDe1");
            calculoValoracion();
        } else if (v == rebotesDefensivos) {
            TextView rebotesDefensivos = (TextView) findViewById(R.id.rebotesDefensivosText);
            rebotesDefensivos.setTag(String.valueOf("rebotesDefensivos"));
            sumarEstadisticas(rebotesDefensivos);
            TextView rebotesTotales = (TextView) findViewById(R.id.rebotesTotalesText);
            rebotesTotales.setTag(String.valueOf("rebotesTotales"));
            sumarEstadisticas(rebotesTotales);
            calculoValoracion();
        } else if (v == rebotesOfensivos) {
            TextView rebotesOfensivos = (TextView) findViewById(R.id.rebotesOfensivosText);
            rebotesOfensivos.setTag(String.valueOf("rebotesOfensivos"));
            sumarEstadisticas(rebotesOfensivos);
            TextView rebotesTotales = (TextView) findViewById(R.id.rebotesTotalesText);
            rebotesTotales.setTag(String.valueOf("rebotesTotales"));
            sumarEstadisticas(rebotesTotales);
            calculoValoracion();
        } else if (v == asist) {
            TextView asistencias = (TextView) findViewById(R.id.asistenciasText);
            asistencias.setTag(String.valueOf("asistencias"));
            sumarEstadisticas(asistencias);
            calculoValoracion();
        } else if (v == recuperacionesBoton) {
            TextView recuperaciones = (TextView) findViewById(R.id.recuperacionesText);
            recuperaciones.setTag(String.valueOf("recuperaciones"));
            sumarEstadisticas(recuperaciones);
            calculoValoracion();
        } else if (v == perdidas) {
            TextView perdidas = (TextView) findViewById(R.id.perdidasText);
            perdidas.setTag(String.valueOf("perdidas"));
            sumarEstadisticas(perdidas);
            calculoValoracion();
        } else if (v == taponesRealizados) {
            TextView taponesRealizados = (TextView) findViewById(R.id.taponesRealizadosText);
            taponesRealizados.setTag(String.valueOf("taponesRealizados"));
            sumarEstadisticas(taponesRealizados);
            calculoValoracion();
        } else if (v == taponesRecibidos) {
            TextView taponesRecibidos = (TextView) findViewById(R.id.taponesRecibidosText);
            taponesRecibidos.setTag(String.valueOf("taponesRecibidos"));
            sumarEstadisticas(taponesRecibidos);
        } else if (v == faltasCometidas) {
            TextView faltasCometidas = (TextView) findViewById(R.id.faltasCometidasText);
            faltasCometidas.setTag(String.valueOf("faltasCometidas"));
            sumarEstadisticas(faltasCometidas);
            calculoValoracion();
        } else if (v == faltasRecibidas) {
            TextView faltasRecibidas = (TextView) findViewById(R.id.faltasRecibidasText);
            faltasRecibidas.setTag(String.valueOf("faltasRecibidas"));
            sumarEstadisticas(faltasRecibidas);
            calculoValoracion();
        } else if(v == salirAplicacion){
            salirDeAplicacion();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estadistica, menu);

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

    public void salirDeAplicacion(){

        finish();
    }

    private void anotacion(TextView v) {

        CharSequence textBoton = totalPuntos.getText();
        String tag = (String) v.getTag();
        String valor = textBoton.toString();
        int numero = Integer.parseInt(valor);


        SQLiteDatabase db = usdbh.getWritableDatabase();
        if (tag.equals("aciertoDos") & db != null) {
            //Insertamos los datos en la tabla Usuarios
            int resultado = numero + 2;
            db.execSQL("UPDATE Nueva SET totalPuntos = '" + resultado + "' WHERE id = " + id);

        } else if (tag.equals("anotadosDe3Text") & db != null) {
            //Insertamos los datos en la tabla Usuarios
            int resultado = numero + 3;
            db.execSQL("UPDATE Nueva SET totalPuntos = '" + resultado + "' WHERE id = " + id);

        } else if (tag.equals("anotadosDe1Text") & db != null) {
            //Insertamos los datos en la tabla Usuarios
            int resultado = numero + 1;
            db.execSQL("UPDATE Nueva SET totalPuntos = '" + resultado + "' WHERE id = " + id);


        }
        if (db != null) {
            db.close();
        }
        consulta();

    }


    private void acierto(TextView v) {
        CharSequence textBoton = v.getText();
        String tag = (String) v.getTag();
        String valor = textBoton.toString();
        int numero = Integer.parseInt(valor);
        int resultado = numero + 1;


        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {
            //Insertamos los datos en la tabla Usuarios
            db.execSQL("UPDATE Nueva SET " + tag + " = '" + resultado + "' WHERE id = " + id);
            db.close();
            consulta();
            anotacion(v);

        } else {
            Toast.makeText(this, "No existe ese ID",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void fallo(TextView v) {
        CharSequence textBoton = v.getText();
        String tag = (String) v.getTag();
        String valor = textBoton.toString();
        int numero = Integer.parseInt(valor);
        int resultado = numero + 1;
        //Abrimos la base de datos 'DBUsuarios' en modo escritura

        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {
            //Insertamos los datos en la tabla Usuarios
            db.execSQL("UPDATE Nueva SET " + tag + " = '" + resultado + "' WHERE id = " + id);
            db.close();
            consulta();
            calculoValoracion();
        } else {
            Toast.makeText(this, "No existe ese ID",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void pasarListview(View v) {

        Intent intento = new Intent(this, ListViewActivity.class);
        startActivity(intento);
    }

    public void cambioJugador(View view) {
        jA.finish();
        Intent act = new Intent(this, JugadorActivity.class);
        act.putExtra("miId", view.getTag().toString());
        act.putExtra("camiseta", camiseta);
        act.putExtra("color", color);
        startActivity(act);

    }

    //TODO hacer una clase para los metodos
    //TODO hacer que al salir de la estadistica guarde bd
    //TODO mirar el quitar un numero a la coma en el tanto por ciento
    //TODO quitar tamaño estatico de creacion de botones

    public void consulta() {

        SQLiteDatabase bd = usdbh.getWritableDatabase();

        Cursor fila = bd.rawQuery("select totalPuntos, aciertoDos, falloDos, porcentajeDos, " +
                "anotadosDe3Text, totalTirosDe3, porcAciertoDe3, anotadosDe1Text, totalTirosDe1, " +
                "porcAciertoDe1, rebotesDefensivos, rebotesOfensivos, rebotesTotales, " +
                "asistencias, recuperaciones, perdidas, taponesRealizados, taponesRecibidos, " +
                "faltasCometidas, faltasRecibidas, valoracion, dorsalJugador, nombreJugador from Nueva where id = " + id, null);
        if (fila.moveToFirst()) {

            totalPuntos.setText(fila.getString(0));
            anotadosDe2.setText(fila.getString(1));
            totalTirosDe2.setText(fila.getString(2));
            porcAcierto2.setText(fila.getString(3));
            anotadosDe3.setText(fila.getString(4));
            totalTirosDe3.setText(fila.getString(5));
            porcAcierto3.setText(fila.getString(6));
            anotadosDe1.setText(fila.getString(7));
            totalTirosDe1.setText(fila.getString(8));
            porcAcierto1.setText(fila.getString(9));
            rD.setText(fila.getString(10));
            rO.setText(fila.getString(11));
            rT.setText(fila.getString(12));
            asistencias.setText(fila.getString(13));
            recuperaciones.setText(fila.getString(14));
            perdidasText.setText(fila.getString(15));
            taponesReali.setText(fila.getString(16));
            taponesReci.setText(fila.getString(17));
            faltasCom.setText(fila.getString(18));
            faltasRec.setText(fila.getString(19));
            valorac.setText(fila.getString(20));
            dorsal.setText(fila.getString(21));
            nombre.setText(fila.getString(22));

        } else {
            Toast.makeText(this, "No existe ese ID",
                    Toast.LENGTH_SHORT).show();
        }
        fila.close();
        bd.close();
    }

    public void calculoValoracion() {

        SQLiteDatabase bd = usdbh.getWritableDatabase();
        Cursor fila = bd.rawQuery("select totalPuntos, aciertoDos, falloDos, porcentajeDos, " +
                "anotadosDe3Text, totalTirosDe3, porcAciertoDe3, anotadosDe1Text, totalTirosDe1, " +
                "porcAciertoDe1, rebotesDefensivos, rebotesOfensivos, rebotesTotales, " +
                "asistencias, recuperaciones, perdidas, taponesRealizados, taponesRecibidos, " +
                "faltasCometidas, faltasRecibidas, valoracion from Nueva where id = " + id, null);

        if (fila.moveToFirst()) {
            int puntosTotales = fila.getInt(0);
            int totalTirosMetidos2 = fila.getInt(1);
            int totalTiros2 = fila.getInt(2);
            int totalTirosMetidos3 = fila.getInt(4);
            int totalTiros3 = fila.getInt(5);
            int totalTirosMetidos1 = fila.getInt(7);
            int totalTiros1 = fila.getInt(8);
            int rebotes = fila.getInt(12);
            int totalAsistencias = fila.getInt(13);
            int totalRobos = fila.getInt(14);
            int totalPerdidas = fila.getInt(15);
            int totalTapones = fila.getInt(16);
            int totalFaltasCometidas = fila.getInt(18);
            int totalFaltasRecibidas = fila.getInt(19);
            int tiros = (totalTiros2 + totalTiros3 + totalTiros1) - (totalTirosMetidos2 + totalTirosMetidos3 + totalTirosMetidos1);
            int totalValoracion = (puntosTotales + rebotes + totalAsistencias + totalRobos + totalTapones + totalFaltasRecibidas) - (tiros + totalPerdidas + totalFaltasCometidas);

            //Insertamos los datos en la tabla Usuarios
            bd.execSQL("UPDATE Nueva SET valoracion = '" + totalValoracion + "' WHERE id = " + id);

        }
        fila.close();
        bd.close();
        consulta();
    }

    public void crearBoton() {

        SQLiteDatabase bd = usdbh.getWritableDatabase();

        Cursor numeroFilas = bd.rawQuery("select dorsalJugador from Nueva", null);

        int totalFilas = numeroFilas.getCount();
        int contadorFilas = 1;
        for (int i = 0; i < totalFilas; i++) {


            Cursor fila = bd.rawQuery("select dorsalJugador from Nueva WHERE id=" + contadorFilas, null);

            if (fila.moveToFirst()) {

                String numeroDorsal = fila.getString(0);


                LinearLayout ll = (LinearLayout) findViewById(R.id.layoutNumeros);
                Typeface font = Typeface.SERIF;
                b = new Button(JugadorActivity.this);
                b.setTypeface(font);

                b.setTextColor(Color.parseColor(color));

                b.setText(numeroDorsal);

                b.setWidth(46);
                b.setHeight(50);

                b.setBackgroundResource(camiseta);
                b.setTag(contadorFilas);

                int botonApretado = Integer.parseInt(id);


                LinearLayout.LayoutParams lp =
                        new LinearLayout.LayoutParams
                                (65, 90);

                ll.addView(b, lp);
                if (contadorFilas == botonApretado) {

                    b.setVisibility(View.GONE);
                }
                contadorFilas++;
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        cambioJugador(arg0);
                    }

                });


            }
            fila.close();

        }
        numeroFilas.close();

    }


    public void sumarEstadisticas(TextView v) {

        CharSequence textBoton = v.getText();
        String tag = (String) v.getTag();
        String valor = textBoton.toString();
        int numero = Integer.parseInt(valor);
        int resultado = numero + 1;
        //Abrimos la base de datos 'DBUsuarios' en modo escritura

        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {
            //Insertamos los datos en la tabla Usuarios
            db.execSQL("UPDATE Nueva SET " + tag + " = '" + resultado + "' WHERE id = " + id);
        } else {
            Toast.makeText(this, "No existe ese ID",
                    Toast.LENGTH_SHORT).show();
        }
        if (db != null) {
            db.close();
        }
        consulta();

    }


    public void porcentaje(TextView aciertos, TextView fallos, String columna) {

        CharSequence totAci = aciertos.getText();
        String valorAciertos = totAci.toString();
        double totalAciertos = Integer.parseInt(valorAciertos);
        CharSequence totFa = fallos.getText();
        String valorFallos = totFa.toString();
        double totalFallos = Integer.parseInt(valorFallos);
        double totalPorcentaje = totalAciertos * 100 / totalFallos;

        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (totalPorcentaje % 5 == 0 || totalPorcentaje % 2 == 0) {
            DecimalFormat df = new DecimalFormat("0.00");
            String n = df.format(totalPorcentaje);
            String numSinComa = n.substring(0, n.length() - 3);
            //Abrimos la base de datos 'DBUsuarios' en modo escritura

            if (db != null) {
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("UPDATE Nueva SET " + columna + " = '" + numSinComa + "' WHERE id = " + id);
            } else {
                Toast.makeText(this, "No existe ese ID",
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            String n = df.format(totalPorcentaje);
            String numSinComa = n.substring(0, n.length() - 1);
            //Abrimos la base de datos 'DBUsuarios' en modo escritura
            if (db != null) {
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("UPDATE Nueva SET " + columna + " = '" + numSinComa + "' WHERE id = " + id);
            }
        }
        if (db != null) {
            db.close();
        }
        consulta();
    }

}

