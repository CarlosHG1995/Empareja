package com.example.empareja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Juego1 extends Activity {
    ImageButton el0, el1, el2, el3, el4, el5, el6, el7, el8, el9, el10, el11, el12, el13, el14, el15,
    el17, el18,el19,el20,el21,el22,el23,el24,el25,el26,el27,el28,el29,el30,el31,el32,el33,el34,el35,el36,
    el37,el38,el39,el40;

    int imagenes[],imagenes1[];
    int fondo,  numeroPrimero, numeroSegundo,
            aciertos=0, puntuacion=0,puntuacion1=0, ptos=0;

    ImageButton [] botonera = new ImageButton[16];
    ImageButton [] botonera2 = new ImageButton[24];

    ArrayList<Integer> arrayBarajado,arrayBarajado1;
    ImageButton primero;
    boolean bloqueo = false;
    boolean sonido = false;
    int id_sonido_Gana,id_sonido_Pierde, id_sonido_pareja;
    SoundPool soundPool;
    final Handler handler = new Handler();
    private int tiempo;
    private long empieza,termina;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jugar();
    }
    protected synchronized void jugar () {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getString("juego", "0").equals("0")){
            empieza = System.currentTimeMillis();
            setContentView(R.layout.tablon_facil);
            cargarImagenes();
            iniciar();
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Context context = this;
            id_sonido_Gana = soundPool.load(context, R.raw.ganar, 0);
            id_sonido_Pierde= soundPool.load(context, R.raw.no_emparejado, 0);
            id_sonido_pareja= soundPool.load(context, R.raw.emparejado, 0);
        } else if (pref.getString("juego", "1").equals("1")){
            empieza = System.currentTimeMillis();
            setContentView(R.layout.tablon_medio);
            cargarImagenes1();
            iniciar2();
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Context context = this;
            id_sonido_Gana = soundPool.load(context, R.raw.ganar, 0);
            id_sonido_Pierde= soundPool.load(context, R.raw.no_emparejado, 0);
            id_sonido_pareja= soundPool.load(context, R.raw.emparejado, 0);
        }
    }
    public void cargarImagenes1(){
        imagenes1 = new int[]{
                R.drawable.ale,
                R.drawable.arg,
                R.drawable.aus,
                R.drawable.bel,
                R.drawable.bra,
                R.drawable.can,
                R.drawable.chi,
                R.drawable.chile,
                R.drawable.co,
                R.drawable.corea,
                R.drawable.eng,
                R.drawable.es,

        };

        fondo = R.drawable.hide;
    }

    public void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.fra,
                R.drawable.hol,
                R.drawable.ita,
                R.drawable.jap,
                R.drawable.por,
                R.drawable.rus,
                R.drawable.peru,
                R.drawable.usa,
        };
         fondo = R.drawable.hide;
    }

    public ArrayList<Integer> barajar(int longitud) {
        ArrayList resultadoA = new ArrayList<Integer>();
        for(int i=0; i<longitud; i++)
            resultadoA.add(i % longitud/2);
        Collections.shuffle(resultadoA);
        return  resultadoA;
    }

    public void cargarBotones(){
        el0 =  findViewById(R.id.boton00); botonera[0] = el0;
        el1 =  findViewById(R.id.boton01); botonera[1] = el1;
        el2 =  findViewById(R.id.boton02); botonera[2] = el2;
        el3 =  findViewById(R.id.boton03); botonera[3] = el3;
        el4 =  findViewById(R.id.boton04); botonera[4] = el4;
        el5 =  findViewById(R.id.boton05); botonera[5] = el5;
        el6 =  findViewById(R.id.boton06); botonera[6] = el6;
        el7 =  findViewById(R.id.boton07); botonera[7] = el7;
        el8 =  findViewById(R.id.boton08); botonera[8] = el8;
        el9 =  findViewById(R.id.boton09); botonera[9] = el9;
        el10 =  findViewById(R.id.boton10); botonera[10] = el10;
        el11 =  findViewById(R.id.boton11); botonera[11] = el11;
        el12 =  findViewById(R.id.boton12); botonera[12] = el12;
        el13 =  findViewById(R.id.boton13); botonera[13] = el13;
        el14 =  findViewById(R.id.boton14); botonera[14] = el14;
        el15 =  findViewById(R.id.boton15); botonera[15] = el15;
    }

    public void cargarBotones2(){
        el23 =  findViewById(R.id.boton1);        botonera2[0] = el23;
        el24 =  findViewById(R.id.boton2);        botonera2[1] = el24;
        el31 =  findViewById(R.id.boton3);        botonera2[2] = el31;
        el32 =  findViewById(R.id.boton4);        botonera2[3] = el32;
        el33 =  findViewById(R.id.boton5);        botonera2[4] = el33;
        el34 =  findViewById(R.id.boton6);        botonera2[5] = el34;

        el35 =  findViewById(R.id.boton9);        botonera2[6] = el35;
        el36 =  findViewById(R.id.boton010);        botonera2[7] = el36;
        el37 =  findViewById(R.id.boton011);        botonera2[8] = el37;
        el38 =  findViewById(R.id.boton012);        botonera2[9] = el38;
        el39 =  findViewById(R.id.boton013);        botonera2[10] = el39;
        el40 =  findViewById(R.id.boton014);        botonera2[11] = el40;

        el17 =  findViewById(R.id.boton17);        botonera2[12] = el17;
        el18 =  findViewById(R.id.boton18);        botonera2[13] = el18;
        el19 =  findViewById(R.id.boton19);        botonera2[14] = el19;
        el20 =  findViewById(R.id.boton20);        botonera2[15] = el20;
        el21 =  findViewById(R.id.boton21);        botonera2[16] = el21;
        el22 =  findViewById(R.id.boton22);        botonera2[17] = el22;

        el25 =  findViewById(R.id.boton25);        botonera2[18] = el25;
        el26 =  findViewById(R.id.boton26);        botonera2[19] = el26;
        el27 =  findViewById(R.id.boton27);        botonera2[20] = el27;
        el28 =  findViewById(R.id.boton28);        botonera2[21] = el28;
        el29 =  findViewById(R.id.boton29);        botonera2[22] = el29;
        el30 =  findViewById(R.id.boton30);        botonera2[23] = el30;

    }


    public void comprobar(int i, final ImageButton imgb){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

       if (pref.getString("juego", "0").equals("0")){
        if(primero==null){//ningún botón ha sido pulsado
            //el botón primero será el que acabamos de pulsar
            primero = imgb;
        /*le asignamos la imagen del vector imágenes situada
        en la posición arrayBarajado.get(i), con un valor entre 0 y 7*/
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayBarajado.get(i)]);
            //bloqueamos el botón
            primero.setEnabled(false);
            //almacenamos el valor de arrayBarajado[i]
            numeroPrimero=arrayBarajado.get(i);
        }else{//ya hay un botón descubierto bloqueamos todos los demás
            bloqueo=true;
            //el botón segundo será el que acabamos de pulsar
        /*le asignamos la imagen del vector imágenes situada
        en la posición arrayBarajado.get(i), con un valor entre 0 y 7*/
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayBarajado.get(i)]);
            //bloqueamos el botón
            imgb.setEnabled(false);
            //almacenamos el valor de arrayBarajado.get(i)
            numeroSegundo=arrayBarajado.get(i);
            if(numeroPrimero==numeroSegundo){//si coincide el valor los dejamos   destapados
                //reiniciamos
                soundPool.play(id_sonido_pareja,1,1,1,0,1);
                primero=null;
                bloqueo=false;
                //aumentamos los aciertos y la puntuación
                aciertos++;
                puntuacion ++;
                 //al llegar a 8 aciertos se ha ganado el juego
                if(aciertos==8){
                    puntuacion1 = 1000*puntuacion;
                    termina = System.currentTimeMillis();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Has  ganado!! " + puntuacion1 , Toast.LENGTH_LONG);
                    toast.show();
                    soundPool.play(id_sonido_Gana,1,1,1,1,1);
                    tiempo = (int) ((termina - empieza)/1000);
                    ptos = puntuacion1*tiempo;
                    salir();
                }
            }else{//si NO coincide el valor los volvemos a tapar al cabo de un cuarto segundo
                soundPool.play(id_sonido_Pierde,1,1,1,0,1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //les ponemos la imagen de fondo
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(R.drawable.hide);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(R.drawable.hide);
                        //los volvemos a habilitar
                        primero.setEnabled(true);
                        imgb.setEnabled(true);
                        //reiniciamos la variables auxiliares
                        primero = null;
                        bloqueo = false;
                        //restamos uno a la puntuación
                        if (puntuacion > 0) {
                            puntuacion --;
                         }
                    }
                }, 250);//al cabo de un cuarto segundo
            }
        }} else if (pref.getString("juego", "1").equals("1")) {
           if(primero==null){
               primero = imgb;
               primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
               primero.setImageResource(imagenes1[arrayBarajado1.get(i)]);
               //bloqueamos el botón
               primero.setEnabled(false);
               //almacenamos el valor de arrayBarajado[i]
               numeroPrimero=arrayBarajado1.get(i);
           }else{//ya hay un botón descubierto
               //bloqueamos todos los demás
               bloqueo=true;
               imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
               imgb.setImageResource(imagenes1[arrayBarajado1.get(i)]);
               //bloqueamos el botón
               imgb.setEnabled(false);
               numeroSegundo=arrayBarajado1.get(i);
               if(numeroPrimero==numeroSegundo){//si coincide el valor los dejamos   destapados
                   //reiniciamos
                   soundPool.play(id_sonido_pareja,1,1,1,0,1);
                   primero=null;
                   bloqueo=false;
                   aciertos++;
                   puntuacion++;
                   if(aciertos==12){

                       termina = System.currentTimeMillis();
                       soundPool.play(id_sonido_Gana,1,1,1,1,1);
                       tiempo = (int) ((termina - empieza)/1000);
                       puntuacion1 = 2000*puntuacion;
                       Toast toast = Toast.makeText(getApplicationContext(),"Has  ganado!! " + puntuacion1 , Toast.LENGTH_LONG);
                       toast.show();

                       salir();
                   }
               }else{//si NO coincide el valor los volvemos a tapar al cabo de un segundo
                   soundPool.play(id_sonido_Pierde,1,1,1,0,1);
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           //les ponemos la imagen de fondo
                           primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                           primero.setImageResource(R.drawable.hide);
                           imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                           imgb.setImageResource(R.drawable.hide);
                           //los volvemos a habilitar
                           primero.setEnabled(true);
                           imgb.setEnabled(true);
                           primero = null;
                           bloqueo = false;

                           if (puntuacion > 0) {
                               puntuacion--;
                           }
                       }
                   }, 250);
               }
           }
        }

    }

    private void salir() {
        Bundle bundle = new Bundle();
        bundle.putInt("puntuacion", puntuacion1);
        bundle.putInt("tiempo", tiempo);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }


    public void iniciar2(){
        arrayBarajado1 = barajar(imagenes1.length*2);
        cargarBotones2();
        //MOSTRAMOS LA IMAGEN
        for(int i=0; i<botonera2.length; i++) {
            botonera2[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            botonera2[i].setImageResource(imagenes1[arrayBarajado1.get(i)]);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botonera2.length; i++) {
                    botonera2[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    botonera2[i].setImageResource(fondo);
                }
            }
        }, 100);

        //AÑADIMOS LOS EVENTOS A LOS BOTONES DEL JUEGO
        for(int i=0; i <arrayBarajado1.size(); i++){
            final int j=i;
            botonera2[i].setEnabled(true);
            botonera2[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, botonera2[j]);
                }
            });
        }
        aciertos=0;
        puntuacion=0;
    }

    public void iniciar(){
        arrayBarajado = barajar(imagenes.length*2);
        cargarBotones();

        //MOSTRAMOS LA IMAGEN
        for(int i=0; i<botonera.length; i++) {
            botonera[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            botonera[i].setImageResource(imagenes[arrayBarajado.get(i)]);
        }

        //Y EN UNA CENTESIMA SEGUNDO LA OCULTAMOS
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < botonera.length; i++) {
                    botonera[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    botonera[i].setImageResource(fondo);
                }
            }
        }, 100);

        //AÑADIMOS LOS EVENTOS A LOS BOTONES DEL JUEGO
        for(int i=0; i <arrayBarajado.size(); i++){
            final int j=i;
            botonera[i].setEnabled(true);
            botonera[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, botonera[j]);
                }
            });
        }
        aciertos=0;
        puntuacion=0;
    }
}
