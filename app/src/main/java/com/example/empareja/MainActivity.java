package com.example.empareja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int ACTIV_JUEGO = 0;
    private static final int SOLICITUD_PERMISO_WRITE_EXTERNAL =0;
    public static AlmacenPuntuaciones almacen= new AlmacenPuntuacionesList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ANIMACIONDES DE TEXT VIEW Y BOTONES
        TextView texto = findViewById(R.id.textView);
        Animation animacion_empareja = AnimationUtils.loadAnimation(this,R.anim.anima_logo);
        texto.startAnimation(animacion_empareja);
        Button btn_jugar = findViewById(R.id.button01);
        Animation animacion_btn_jugar = AnimationUtils.loadAnimation(this,R.anim.anima_boton_jugar);
        btn_jugar.startAnimation(animacion_btn_jugar);
        Button btn_configura = findViewById(R.id.button02);
        Button btn_acerca = findViewById(R.id.button03);
        Button btn_puntua = findViewById(R.id.button04);
        Animation animacion_btns = AnimationUtils.loadAnimation(this,R.anim.anima_);
        btn_configura.startAnimation(animacion_btns);
        btn_acerca.startAnimation(animacion_btns);
        btn_puntua.startAnimation(animacion_btns);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if      (pref.getString("preferencias", "1").equals("0")){almacen = new AlmacenPuntuacionesPreferencias(this);}
        else if (pref.getString("preferencias", "1").equals("1")){almacen = new AlmacenPuntuacionesFicheroExterno(this);}
        else if (pref.getString("preferencias", "1").equals("2")){almacen = new AlmacenPuntuacionesSW_PHP_hosting_propio();}

        permiso();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    }
// permiso de almacenamiento externo android 6
     void permiso (){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        pref.getString("preferencias", "1").equals("1");
            almacen = new AlmacenPuntuacionesFicheroExterno(this);

        }else {
            solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE,R.string.permiso2,SOLICITUD_PERMISO_WRITE_EXTERNAL,this);
        }
     }

    public static void solicitarPermiso(final String permiso, int justificacion, final int requestCode, final Activity actividad){
        if(ActivityCompat.shouldShowRequestPermissionRationale(actividad,permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle(R.string.permiso)
                    .setMessage(justificacion)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichbuton){
                            ActivityCompat.requestPermissions(actividad,new String[]{permiso},requestCode);
                        }}).show();
        }else{
            ActivityCompat.requestPermissions(actividad,new String[]{permiso},requestCode);
        }
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    public void lanzarPuntuaciones(View view) {
        Intent i = new Intent(this, Puntuaciones.class);
        startActivityForResult(i,ACTIV_JUEGO);
    }
    public  void lanzarPrferencias(View view){
    Intent abre= new Intent(this, PreferenciasActivity.class);
        startActivityForResult(abre,ACTIV_JUEGO);
    }

    public void lanzarJuego(View view) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if (pref.getString("juego", "0").equals("0")) {
            Intent i = new Intent(this, Juego1.class);
            startActivityForResult(i,ACTIV_JUEGO);

        }
        if (pref.getString("juego", "0").equals("1")) {
            Intent i = new Intent(this, Juego1.class);
            startActivityForResult(i,ACTIV_JUEGO);

        }

    }

    public  void salir(View view){
             finish();
       }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //aquí pongo los if para guardar almacenamiento y en el oncreate tambien
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        if      (pref.getString("preferencias", "1").equals("0")){almacen = new AlmacenPuntuacionesPreferencias(this);}// AlmacenPuntuacionesPreferencias(this);}
        else if (pref.getString("preferencias", "1").equals("1")){almacen = new AlmacenPuntuacionesFicheroExterno(this);}
        else if (pref.getString("preferencias", "1").equals("2")){almacen = new AlmacenPuntuacionesSW_PHP_hosting_propio();}


        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==ACTIV_JUEGO && resultCode==RESULT_OK && data!=null){
            int puntuacion = data.getExtras().getInt("puntuacion");
            int tiempo = data.getExtras().getInt("tiempo");
            String nombre = pref.getString("nombre","?");
            almacen.guardarPuntuacion(puntuacion,nombre,System.currentTimeMillis(),tiempo);
            lanzarPuntuaciones(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;/**->el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        if(id==R.id.action_settings){
            lanzarPrferencias(null);
            return true;
        }
        if (id==R.id.acercaDe){
            lanzarAcercaDe(null);
            return true;
        }
        if (id==R.id.sali){
            salir(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
