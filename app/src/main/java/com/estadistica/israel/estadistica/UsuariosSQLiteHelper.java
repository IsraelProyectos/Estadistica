package com.estadistica.israel.estadistica;

/**
 * Created by Israel on 11/03/2015.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.estadistica.israel.estadistica/databases/";
    private static String DB_NAME = "nombrecamisetadb";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public UsuariosSQLiteHelper(Context contexto, String nombre, CursorFactory factory,
                       int version) {

        super(contexto, nombre, factory, version);
        this.myContext = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No hacemos nada aqui
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cuando haya cambios en la estructura deberemos
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("nombrecamisetadb");
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }


/*import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//TODO mirar de hacer que cree varias bases de datos
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios



    String sqlCreate = "CREATE TABLE IF NOT EXISTS NombreBDs" +
            "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "nombreBaseDeDatos VARCHAR, camiseta INTEGER) ";

    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase dbs) {
        //Se ejecuta la sentencia SQL de creación de la tabla

        dbs.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbs, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        dbs.execSQL("DROP TABLE IF EXISTS NombreBDs");

        //Se crea la nueva versión de la tabla
        dbs.execSQL(sqlCreate);
    }
*/
    public void tablaNueva(String tabla){
        /*SQLiteDatabase dbs = getWritableDatabase();
        dbs.execSQL("CREATE TABLE "+tabla +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "dorsalJugador INTEGER, nombreJugador VARCHAR, totalPuntos INTEGER, aciertoDos INTEGER, falloDos INTEGER, porcentajeDos VARCHAR," +
                "anotadosDe3Text INTEGER, totalTirosDe3 INTEGER, porcAciertoDe3 VARCHAR, " +
                "anotadosDe1Text INTEGER, totalTirosDe1 INTEGER, porcAciertoDe1 VARCHAR," +
                "rebotesDefensivos INTEGER, rebotesOfensivos INTEGER, rebotesTotales INTEGER," +
                "asistencias INTEGER, recuperaciones INTEGER, perdidas INTEGER, taponesRealizados INTEGER," +
                "taponesRecibidos INTEGER, faltasCometidas INTEGER, faltasRecibidas INTEGER, valoracion INTEGER)");*/
    }


}
