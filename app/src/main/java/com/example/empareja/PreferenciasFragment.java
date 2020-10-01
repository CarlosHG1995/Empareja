package com.example.empareja;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import java.util.prefs.Preferences;

public class PreferenciasFragment extends PreferenceFragment {

    @Override public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.preferencias);}
}
