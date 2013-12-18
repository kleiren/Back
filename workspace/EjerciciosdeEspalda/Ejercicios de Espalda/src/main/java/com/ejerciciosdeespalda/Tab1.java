package com.ejerciciosdeespalda;



        import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab1 extends Fragment {
    private TextView timec1, timec2, timec3;
    private float[] tiempos;

    public Tab1(float[] tiempos){
       this.tiempos=tiempos;

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time1, container, false);
        timec1=(TextView) view.findViewById(R.id.timec1);
        timec2=(TextView) view.findViewById(R.id.timec2);
        timec3=(TextView) view.findViewById(R.id.timec3);
        timec1.setText("Ejercicio 1: "+(int) tiempos[0]+" sec");
        timec2.setText("Ejercicio 2: "+(int) tiempos[1]+" sec");
        timec3.setText("Ejercicio 3: "+(int) tiempos[2]+" sec");






        return view;

    }




}