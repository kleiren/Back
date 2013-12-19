package com.ejerciciosdeespalda.tiempos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ejerciciosdeespalda.R;

public class Tab3 extends Fragment {

    private TextView timeb1,timeb2;
    private float[] tiempos;
    public Tab3(float[] tiempos){
        this.tiempos=tiempos;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time3, container, false);

        timeb1=(TextView) view.findViewById(R.id.timeb1);
        timeb2=(TextView) view.findViewById(R.id.timeb2);

        timeb1.setText("Ejercicio 1: "+(int) tiempos[8]+" sec");
        timeb2.setText("Ejercicio 2: "+(int) tiempos[9]+" sec");


        return view;
    }
}