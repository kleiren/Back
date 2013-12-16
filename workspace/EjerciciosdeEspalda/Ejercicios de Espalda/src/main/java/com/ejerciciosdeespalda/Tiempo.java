package com.ejerciciosdeespalda;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;*/

public class Tiempo extends Activity {



    private TextView time1, time2, time3, time4;

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
        ActionBar.Tab tab1 =
                abar.newTab().setText("Tiempos cabeza");

        ActionBar.Tab tab2 =
                abar.newTab().setText("Tiempos extremidades superiores");

        //Creamos los fragments de cada pestaña
        Fragment tab1frag = new Tab1();
        Fragment tab2frag = new Tab2();

        //Asociamos los listener a las pestañas
        tab1.setTabListener(new MiTabListener(tab1frag));
        tab2.setTabListener(new MiTabListener(tab2frag));

        //Añadimos las pestañas a la action bar
        abar.addTab(tab1);
        abar.addTab(tab2);












        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        time1=(TextView) findViewById(R.id.tiempo1);
        time2=(TextView) findViewById(R.id.tiempo2);
        time3=(TextView) findViewById(R.id.tiempo3);
        time4=(TextView) findViewById(R.id.tiempo4);

        Bundle extras = getIntent().getExtras();
        float[] tiempos= extras.getFloatArray("tiempos");

        time1.setText("Ejercicio 1: "+(int) tiempos[0]+" sec");
        time2.setText("Ejercicio 2: "+(int) tiempos[1]+" sec");
        time3.setText("Ejercicio 3: "+(int) tiempos[2]+" sec");
        time4.setText("Ejercicio 4: "+(int) tiempos[3]+" sec");

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
