package com.ejerciciosdeespalda;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class OpcionesActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);
    }
}