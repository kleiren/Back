package com.ejerciciosdeespalda.tiempos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ejerciciosdeespalda.R;

public class Tab4 extends Fragment {

    private TextView time1ei;
    private float[] tiempos;
    public Tab4(float[] tiempos){
        this.tiempos=tiempos;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time4, container, false);
        time1ei=(TextView) view.findViewById(R.id.time1ei);


        time1ei.setText("Ejercicio 1: "+(int) tiempos[10]+" sec");



        return view;
    }
}