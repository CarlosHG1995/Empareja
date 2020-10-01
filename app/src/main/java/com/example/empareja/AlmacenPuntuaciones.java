package com.example.empareja;

import java.util.List;

public interface AlmacenPuntuaciones {

    public void guardarPuntuacion(int puntos,String nombre,long fecha, int tiempo);
    public List<String> listaPuntuaciones(int cantidad);
}
