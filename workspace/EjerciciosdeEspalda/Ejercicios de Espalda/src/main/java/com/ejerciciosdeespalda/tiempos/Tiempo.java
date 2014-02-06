package com.ejerciciosdeespalda.tiempos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ejerciciosdeespalda.MainActivity;
import com.ejerciciosdeespalda.R;
import com.ejerciciosdeespalda.UsuariosSQLiteHelper;

import java.util.Calendar;

/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;*/

public class Tiempo extends Activity {


    private Button back;

    float[] times = new float[15];
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);


        //tablistener
        //Obtenemos una referencia a la actionbar
        ActionBar abar = getActionBar();

        //Establecemos el modo de navegación por pestañas
        abar.setNavigationMode(
                ActionBar.NAVIGATION_MODE_TABS);

        //Ocultamos si queremos el título de la actividad
        //abar.setDisplayShowTitleEnabled(false);

        //Creamos las pestañas
        ActionBar.Tab tab0 =
                abar.newTab().setText("Tiempo total");
        ActionBar.Tab tab1 =
                abar.newTab().setText("Tiempos cabeza");
        ActionBar.Tab tab2 =
                abar.newTab().setText("Tiempos extremidades superiores");
        ActionBar.Tab tab3 =
                abar.newTab().setText("Tiempos espalda");
        ActionBar.Tab tab4 =
                abar.newTab().setText("Tiempos extremidades inferiores");
        ActionBar.Tab tab5 =
                abar.newTab().setText("Tiempos oculares");


        Bundle extras = getIntent().getExtras();
        float[] tiempos = extras.getFloatArray("tiempos");
        insertar(tiempos);


        //Creamos los fragments de cada pestaña
        Fragment tab0frag = new Tab0(tiempos);
        Fragment tab1frag = new Tab1(tiempos);
        Fragment tab2frag = new Tab2(tiempos);
        Fragment tab3frag = new Tab3(tiempos);
        Fragment tab4frag = new Tab4(tiempos);
        Fragment tab5frag = new Tab5(tiempos);


        //Asociamos los listener a las pestañas
        tab0.setTabListener(new MiTabListener(tab0frag));
        tab1.setTabListener(new MiTabListener(tab1frag));
        tab2.setTabListener(new MiTabListener(tab2frag));
        tab3.setTabListener(new MiTabListener(tab3frag));
        tab4.setTabListener(new MiTabListener(tab4frag));
        tab5.setTabListener(new MiTabListener(tab5frag));

        //Añadimos las pestañas a la action bar
        abar.addTab(tab0);
        abar.addTab(tab1);
        abar.addTab(tab2);
        abar.addTab(tab3);
        abar.addTab(tab4);
        abar.addTab(tab5);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences(
                                Tiempo.this);
                int tiempomin = Integer.parseInt(pref.getString("opcion2", ""));
                Toast.makeText(getApplicationContext(), "Te avisaremos en " + tiempomin + " minutos", Toast.LENGTH_LONG).show();


                Intent activity1 = new Intent(Tiempo.this, MainActivity.class);
                startActivityForResult(activity1, 0);
                finish();


            }
        });


//grafico();


    }


    //de momento crashea el gráfico

/*private void grafico(){
    GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[] {
            new GraphView.GraphViewData(1, 2.0d)
            , new GraphView.GraphViewData(2, 1.5d)
            , new GraphView.GraphViewData(3, 2.5d)
            , new GraphView.GraphViewData(4, 1.0d)
    });

    GraphView graphView = new LineGraphView(
            this // context
            , "GraphViewDemo" // heading
    );
    graphView.addSeries(exampleSeries); // data


    LinearLayout layout = (LinearLayout) findViewById(R.id.graph);
    layout.addView(graphView);

}*/

/*    private void basededatos(float [] tiempos){
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        SQLiteHelper usdbh =
                new SQLiteHelper(this, "DBTiempos", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int codigo = i;


                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + tiempos +"')");
            }

            //Cerramos la base de datos
            db.close();
        }
    }

    */


    public void insertar(float[] tiempos) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);


        db = usdbh.getWritableDatabase();
        //Recuperamos los valores de los campos de texto


        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        String cod = Integer.toString(hour)+":"+Integer.toString(minute)+" "+Integer.toString(day)+"/"+Integer.toString(month+1)+"/"+Integer.toString(year);
        String nom="";

        for (int i=0; i<tiempos.length; i++){

         nom+=( Integer.toString((int)tiempos[i]))+";";

        }

        //Alternativa 1: m�todo sqlExec()
        //String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
        //db.execSQL(sql);

        //Alternativa 2: m�todo insert()
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("codigo", cod);
        nuevoRegistro.put("nombre", nom);
        db.insert("Usuarios", null, nuevoRegistro);
    }

    public String arrayToString(float[] tiempos) {
        String cadena = "";
        int a;
        for (int x = 0; x < 11; x++) {
            a = (int) tiempos[x] * 10;
            cadena = cadena + (String.valueOf(a));
        }
        return cadena;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tiempo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tiempo, container, false);
            return rootView;
        }
    }

}
