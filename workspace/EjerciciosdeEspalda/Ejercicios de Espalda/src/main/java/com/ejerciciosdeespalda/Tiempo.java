package com.ejerciciosdeespalda;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;*/

public class Tiempo extends Activity {



    private Button back ;

    float[] times=new float [15];


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
        ActionBar.Tab tab3 =
                abar.newTab().setText("Tiempos espalda");
        ActionBar.Tab tab4 =
                abar.newTab().setText("Tiempos extremidades inferiores");
        ActionBar.Tab tab5 =
                abar.newTab().setText("Tiempos oculares");


        Bundle extras = getIntent().getExtras();
        float [] tiempos= extras.getFloatArray("tiempos");

        //Creamos los fragments de cada pestaña
        Fragment tab1frag = new Tab1(tiempos);
        Fragment tab2frag = new Tab2(tiempos);
        Fragment tab3frag = new Tab3(tiempos);
        Fragment tab4frag = new Tab4(tiempos);
        Fragment tab5frag = new Tab5(tiempos);


        //Asociamos los listener a las pestañas
        tab1.setTabListener(new MiTabListener(tab1frag));
        tab2.setTabListener(new MiTabListener(tab2frag));
        tab3.setTabListener(new MiTabListener(tab3frag));
        tab4.setTabListener(new MiTabListener(tab4frag));
        tab5.setTabListener(new MiTabListener(tab5frag));

        //Añadimos las pestañas a la action bar
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

                Intent activity1 = new Intent(Tiempo.this, MainActivity.class);
                startActivityForResult(activity1, 0);
                finish();

                BroadcastReceiver br = new BroadcastReceiver() {
                    //define que hacer al pasar el tiempo
                    @Override
                    public void onReceive(Context c, Intent i) {
                        Toast.makeText(c, "Te avisaremos en 1 hora", Toast.LENGTH_LONG).show();



                    }
                };






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
