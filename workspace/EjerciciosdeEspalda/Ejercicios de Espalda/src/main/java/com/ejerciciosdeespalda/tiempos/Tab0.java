package com.ejerciciosdeespalda.tiempos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ejerciciosdeespalda.R;

public class Tab0 extends Fragment {
    private TextView timeT;
    private float[] tiempos;

    public Tab0(float[] tiempos) {
        this.tiempos = tiempos;

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time0, container, false);
        timeT = (TextView) view.findViewById(R.id.timeT);

        timeT.setText((int) suma(tiempos));



        return view;

    }

    float suma(float[] tiempos)
    {
        int suma=0;
        for (int i=0; i< 12 ; i++)
            suma+=tiempos[i];
        return suma;
    }

}
