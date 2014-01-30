package com.ejerciciosdeespalda;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejerciciosdeespalda.tiempos.Tiempo;

public class Ejercicios extends Activity {


    //declaraciones
    private TextView descripcion;
    private TextView titulo, subtitulo;
    private ImageView imagenppal;
    private int estado = 0;
    private Chronometer cronometro;
    public float[] tiempo = new float[15];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


        titulo = (TextView) findViewById(R.id.titulo);
        subtitulo = (TextView) findViewById(R.id.subtitulo);
        descripcion = (TextView) findViewById(R.id.descripcion);
        imagenppal = (ImageView) findViewById(R.id.imageView);
        cronometro = (Chronometer) findViewById(R.id.chronometer);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.container);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                estado++;
                state(estado);
                return false;
            }
        });
    }

    public void state(int estado) {

        switch (estado) {
            case 0: {
                break;
            }
            case 1: {
                cronometro.setBase(SystemClock.elapsedRealtime());

                //cronometro.setBase(0);
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Cabeza");
                subtitulo.setText("Ejercicio1");
                descripcion.setText(R.string.cEx1);
                imagenppal.setImageResource(R.drawable.ejercicio1cabeza);
                break;

            }
            case 2: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Cabeza");
                subtitulo.setText("Ejercicio2");
                descripcion.setText(R.string.cEx2);
                imagenppal.setImageResource(R.drawable.ejercicio2cabeza);
                break;

            }
            case 3: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Cabeza");
                subtitulo.setText("Ejercicio3");
                descripcion.setText(R.string.cEx3);
                imagenppal.setImageResource(R.drawable.ejercicio3cabeza);
                break;

            }
            case 4: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Superiores");
                subtitulo.setText("Ejercicio1");
                descripcion.setText(R.string.esEx1);
                imagenppal.setImageResource(R.drawable.ejercicio1es);
                break;

            }
            case 5: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Superiores");
                subtitulo.setText("Ejercicio2");
                descripcion.setText(R.string.esEx2);
                imagenppal.setImageResource(R.drawable.ejercicio2es);
                break;

            }
            case 6: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Superiores");
                subtitulo.setText("Ejercicio3");
                descripcion.setText(R.string.esEx3);
                imagenppal.setImageResource(R.drawable.ejercicio3es);
                break;

            }
            case 7: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Superiores");
                subtitulo.setText("Ejercicio4");
                descripcion.setText(R.string.esEx4);
                imagenppal.setImageResource(R.drawable.ejercicio4es);
                break;

            }
            case 8: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Superiores");
                subtitulo.setText("Ejercicio5");
                descripcion.setText(R.string.esEx5);
                imagenppal.setImageResource(R.drawable.ejercicio5es);
                break;

            }
            case 9: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Espalda");
                subtitulo.setText("Ejercicio1");
                descripcion.setText(R.string.bEx1);
                imagenppal.setImageResource(R.drawable.ejercicio1b);
                break;

            }
            case 10: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Espalda");
                subtitulo.setText("Ejercicio2");
                descripcion.setText(R.string.bEx2);
                imagenppal.setImageResource(R.drawable.ejercicio2b);
                break;

            }
            case 11: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios de Extremidades Inferiores");
                subtitulo.setText("Ejercicio1");
                descripcion.setText(R.string.eiEx1);
                imagenppal.setImageResource(R.drawable.ejercicio3b);
                break;

            }
            case 12: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();
                cronometro.start();
                titulo.setText("Ejercicios Oculares");
                subtitulo.setText("Ejercicio1");
                descripcion.setText(R.string.oEx1);
                imagenppal.setImageResource(R.drawable.ejercicio1o);
                break;

            }
            case 13: {
                tiempo[estado - 2] = (SystemClock.elapsedRealtime() - cronometro.getBase()) / 1000;

                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.stop();

                titulo.setText("");
                subtitulo.setText("¡Has terminado!");
                descripcion.setText("");
                imagenppal.setImageDrawable(null);
                break;
            }
            case 14: {
                //cambia de a la actividad ejercicios
                Intent activity2 = new Intent(Ejercicios.this, Tiempo.class);


                activity2.putExtra("tiempos", tiempo);
                startActivityForResult(activity2, 0);


                break;
            }
            default: {
                finish();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ejercicios, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_ejercicios, container, false);
            return rootView;
        }
    }

}
