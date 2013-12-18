package com.ejerciciosdeespalda;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab2 extends Fragment {

    private TextView timees1,timees2, timees3, timees4, timees5;
    private float[] tiempos;
    public Tab2(float[] tiempos){
        this.tiempos=tiempos;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time2, container, false);

        timees1=(TextView) view.findViewById(R.id.timees1);
        timees2=(TextView) view.findViewById(R.id.timees2);
        timees3=(TextView) view.findViewById(R.id.timees3);
        timees4=(TextView) view.findViewById(R.id.timees4);
        timees5=(TextView) view.findViewById(R.id.timees5);


        timees1.setText("Ejercicio 3: "+(int) tiempos[3]+" sec");
        timees2.setText("Ejercicio 3: "+(int) tiempos[4]+" sec");
        timees3.setText("Ejercicio 3: "+(int) tiempos[5]+" sec");
        timees4.setText("Ejercicio 3: "+(int) tiempos[6]+" sec");
        timees5.setText("Ejercicio 3: "+(int) tiempos[7]+" sec");

        return view;
    }
}