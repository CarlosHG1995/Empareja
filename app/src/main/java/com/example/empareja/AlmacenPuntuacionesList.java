package com.example.empareja;

import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesList implements AlmacenPuntuaciones {

    private List<String> puntuaciones;
    public AlmacenPuntuacionesList() {
        puntuaciones = new ArrayList<String>();
        puntuaciones.add("123 Andrés,90 ");
        puntuaciones.add("111 Juan Mendoza,92");
        puntuaciones.add("10  Yo,120");
    }

    @Override
    public void guardarPuntuacion(int puntos, String nombre, long fecha, int tiempo) {
        puntuaciones.add(0, puntos + " " + nombre +"," + tiempo + " [Seg]");
    }

    @Override
    public List<String> listaPuntuaciones(int cantidad) {
        return puntuaciones;
    }
}
