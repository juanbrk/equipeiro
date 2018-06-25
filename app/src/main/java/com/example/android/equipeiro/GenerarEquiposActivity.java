package com.example.android.equipeiro;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.equipeiro.Modelo.Jugador;

import java.util.Arrays;
import java.util.Random;

public class GenerarEquiposActivity extends AppCompatActivity {
    private TextView mNombreJugadorEquipo1;
    private TextView mNombreJugadorEquipo2;
    Jugador[] mNombresJugadoresArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_equipos);

        //Obtener los nombres pasados de la actividad anterior
        Intent nuevoIntent = getIntent();
        if (nuevoIntent.hasExtra("nombresJugadores"))
        {
//          Obtengo los elementos pasados en el intent y los convierto a la clase Jugador
            Bundle bundle = getIntent().getExtras();
            Parcelable[] parcelables = bundle.getParcelableArray("nombresJugadores");
            mNombresJugadoresArray = toMyObjects(parcelables);

        }

        mNombreJugadorEquipo1 = (TextView) findViewById(R.id.txt_nombre_jugador_equipo1);
        mNombreJugadorEquipo2 = (TextView) findViewById(R.id.txt_nombre_jugador_equipo2);

        mNombreJugadorEquipo1.setText("");
        mNombreJugadorEquipo2.setText("");

        generarEquiposAlAzar(mNombresJugadoresArray);
    }

    public static Jugador[] toMyObjects(Parcelable[] parcelables) {
        if (parcelables == null)
            return null;
        return Arrays.copyOf(parcelables, parcelables.length, Jugador[].class);
    }

    /**
     * Genera los equipos al azar mezclando los elementos del array de jugadores utilizando el algoritmo
     * Fisher–Yates para permutar elementos de una secuencia. Los jugadores se incluyen en sus respectivos
     * equipos según si son pares o impares.
     * @param listaJugadores es el vector que será permutado y del cual se sacan los jugadores para cada equipo
     */
    public void generarEquiposAlAzar(Jugador[] listaJugadores){
        if (listaJugadores != null)
        {
            shuffleArray(mNombresJugadoresArray);
            for (int i = 0; i < listaJugadores.length;i++)
            {
                if (i % 2 == 0)
                {
                    agregarAEquipo1(listaJugadores[i]);
                }
                else{
                    agregarAEquipo2(listaJugadores[i]);
                }
            }
        }
        //Terminar
    }

//  Fisher–Yates shuffle algorithm implementation.
//  is an algorithm for generating a random permutation of a finite sequence
    static void shuffleArray(Jugador[] listaJugadores)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = listaJugadores.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Jugador a = listaJugadores[index];
            listaJugadores[index] = listaJugadores[i];
            listaJugadores[i] = a;
        }
    }

    public void agregarAEquipo1(Jugador jugador)
    {
        mNombreJugadorEquipo1.append(jugador.getNombre() + "\n");
    }

    public void agregarAEquipo2(Jugador jugador)
    {
        mNombreJugadorEquipo2.append(jugador.getNombre() + "\n");
    }

    //Todo: permitir el refresh de la lista de jugadores, para un nuevo shuffle y un nuevo equipo
}
