package com.ejerciciosdeespalda;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tab3 extends Fragment {

    private TextView timees1,timees2, timees3, timees4, timees5;
    private float[] tiempos;
    public Tab3(float[] tiempos){
        this.tiempos=tiempos;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_time3, container, false);



        return view;
    }
}