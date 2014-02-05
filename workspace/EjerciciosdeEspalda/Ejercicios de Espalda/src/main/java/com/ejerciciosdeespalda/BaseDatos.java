package com.ejerciciosdeespalda;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ejerciciosdeespalda.tiempos.Tiempo;


public class BaseDatos extends Activity {
    ListView listView;
    private SQLiteDatabase db;
    public float[] tiempo=new float []{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


dataBase();// obtiene datos de la base

        //obtiene el último codigo y nombre de la base
        Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);
        c.moveToLast();




        c.moveToPrevious();


        listView = (ListView) findViewById(R.id.listView);

        String[] code = new String[15];
        final String[] value =new String[15];
        for (int i =0; i<14; i++){
            if (c.isFirst()) {
                code [i]="";
                value [i]="";

            }else {
                value [i]=c.getString(1);

                code [i]=c.getString(0);
            c.moveToPrevious();}

        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, code);








        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
               cambio(value[itemPosition]);
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        value[itemPosition], Toast.LENGTH_LONG)
                        .show();

            }

        });

    }
    public void dataBase(){
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();


    }

    public void cambio( String value) {
String [] tiempoString;

       tiempoString=value.split(";");

        for (int i=0; i>12; i++){
            tiempo[i]= Integer.parseInt (tiempoString [i]);
   }



        Intent activity4 = new Intent(BaseDatos.this, Tiempo.class);


        activity4.putExtra("tiempos", tiempo);
        startActivityForResult(activity4, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_datos, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_base_datos, container, false);
            return rootView;
        }
    }

}
