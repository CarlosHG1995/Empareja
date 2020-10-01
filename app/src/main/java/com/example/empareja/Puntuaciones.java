package com.example.empareja;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Puntuaciones extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiAdaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuaciones);
        recyclerView = findViewById(R.id.recyclerView);
        adaptador = new MiAdaptador(this, MainActivity.almacen.listaPuntuaciones(10));
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptador.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                int pos = recyclerView.getChildAdapterPosition(v);
                int pos1=pos+1;
                String s= MainActivity.almacen.listaPuntuaciones(10).get(pos);
                Toast.makeText(Puntuaciones.this, "Jugador en la posición: " + pos1 + " y con puntos " + s, Toast.LENGTH_LONG ).show();
            }

        });
    }
}
