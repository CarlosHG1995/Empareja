package com.example.empareja;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesSW_PHP_hosting_propio implements AlmacenPuntuaciones {

    public void guardarPuntuacion(int puntos, String nombre, long fecha, int tiempo) {
        HttpURLConnection conexion = null;
        try {
            URL url = new URL("http://asteroides-carlos.000webhostapp.com/puntuaciones1/nueva.php?"+"puntos="+puntos+"&nombre="+ URLEncoder.encode(nombre, "UTF-8")+"&fecha="+fecha+"&tiempo="+tiempo);
            conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (!linea.equals("OK")) {
                    Log.e("Empareja", "Error en servicio Web nueva");
                }
            } else {
                Log.e("Empareja", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Empareja", e.getMessage(), e);
        } finally {

            if (conexion != null) conexion.disconnect();
        }
    }

    public List<String> listaPuntuaciones(int cantidad) {
        List<String> result = new ArrayList<String>();
        HttpURLConnection conexion = null;
        try {
            URL url = new URL("http://asteroides-carlos.000webhostapp.com/puntuaciones1/lista.php"+"?max=20");
            conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (!linea.equals("")) {
                    result.add(linea);
                    linea = reader.readLine();
                }
                reader.close();
            } else {
                Log.e("Empareja", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Empareja", e.getMessage(), e);
        } finally {
            if (conexion != null) conexion.disconnect();
            return result;
        }
    }
}
