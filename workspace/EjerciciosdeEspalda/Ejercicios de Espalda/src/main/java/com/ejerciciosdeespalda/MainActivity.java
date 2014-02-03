package com.ejerciciosdeespalda;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Button boton, botonstart, botonbase;
    private ProgressBar barra;

    private int tiempomin, tiempomilis, tiempomilisaux;
    private float timepased;
    private SQLiteDatabase db;

    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 20;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        obtenerPreferencias();
        setup();
        boton = (Button) findViewById(R.id.botontiempo);
        botonstart = (Button) findViewById(R.id.botonstart);
        botonbase = (Button) findViewById(R.id.botonbase);

        barra = (ProgressBar) findViewById (R.id.progressBar);



        boton.setText(((tiempomin / 60) * 1000) + "min");
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(
                        MainActivity.this);
        tiempomin = Integer.parseInt(pref.getString("opcion2", "")); //coge el valor de las preferencias
        tiempomilis = (tiempomin * 1000) * 60;
tiempomilisaux=tiempomilis;
        //barra de progreso
         CountDownTimer cdt;
final int a=10*1000;


        barra.setProgress(a);
        cdt = new CountDownTimer(a, 1000) {
int b=a;
            public void onTick(long millisUntilFinished) {

                b = b-(int) ((a/ 60) * 100);
                barra.setProgress(b);

            }
            public void onFinish() {

            }
        };

        cdt.start();


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setea el tiempo a x segundos
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences(
                                MainActivity.this);
                tiempomin = Integer.parseInt(pref.getString("opcion2", "")); //coge el valor de las preferencias
                tiempomilis = (tiempomin * 1000) * 60;



                am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +
                        tiempomilis, pi);
                Toast.makeText(getApplicationContext(), "Te avisaremos en " + tiempomin + " minutos", Toast.LENGTH_LONG).show();
tiempomilisaux=tiempomilis;






            }

        });



        botonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambia de a la actividad ejercicios
                Intent activity1 = new Intent(MainActivity.this, Ejercicios.class);
                startActivityForResult(activity1, 0);
            }
        });

        botonbase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent activity2 = new Intent(MainActivity.this, BaseDatos.class);

                startActivityForResult(activity2, 0);
            }
        });





    }


    private void obtenerPreferencias() {

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(
                        MainActivity.this);

        Log.i("", "Opción 1: " + pref.getBoolean("opcion1", false));
        Log.i("", "Opción 2: " + pref.getString("opcion2", ""));
        Log.i("", "Opción 3: " + pref.getString("opcion3", ""));
    }


    //inicializa el alarm manager
    private void setup() {


        //Codido de notificación----------------------------------------------------------
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_drawer)
                        .setContentTitle("Ejercicios de Espalda")
                        .setContentText("Han pasado " + tiempomin + " minutos");

        Intent resultIntent = new Intent(this, Ejercicios.class);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Ejercicios.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        final NotificationManager mNotificationManager =  //cambiado a final
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


//----------------------------------------------------------


        br = new BroadcastReceiver() {
            //define que hacer al pasar el tiempo
            @Override
            public void onReceive(Context c, Intent i) {
                Toast.makeText(c, "Han pasado " +tiempomin +" minutos", Toast.LENGTH_LONG).show();
                mNotificationManager.notify(1, mBuilder.build());  //incluido el notify para que solo actue tras este evento


                Intent activity1 = new Intent(MainActivity.this, Ejercicios.class);
                startActivityForResult(activity1, 0);
            }
        };
        registerReceiver(br, new IntentFilter("com.ejerciciosdeespalda"));
        pi = PendingIntent.getBroadcast(this, 0, new Intent("com.ejerciciosdeespalda"),
                0);
        am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));


    }


    protected void onResume() {
        super.onResume();


        //setea el tiempo a x segundos
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(
                        MainActivity.this);
        tiempomin = Integer.parseInt(pref.getString("opcion2", "")); //coge el valor de las preferencias


        boton.setText(tiempomin + "min");


    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,
                    OpcionesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //  TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

    //destruye el alarm manager (timers)
    @Override
    protected void onDestroy() {
        am.cancel(pi);
        unregisterReceiver(br);
        super.onDestroy();
    }


}


