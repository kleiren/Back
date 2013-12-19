package com.ejerciciosdeespalda.tiempos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ejerciciosdeespalda.R;
// extremidades inferiores
public class Tab5 extends Fragment {

    private TextView time1o;
    private float[] tiempos;
    public Tab5(float[] tiempos){
        this.tiempos=tiempos;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time5, container, false);

        time1o=(TextView) view.findViewById(R.id.time1o);

        time1o.setText("Ejercicio 1: "+(int) tiempos[11]+" sec");

        return view;
    }
}