package com.example.android.equipeiro.Modelo;

public class Equipo {
    private String mNombre;
    private Jugador[] listaJugadores;

    public Equipo(){}

    public Equipo(String nombre,  Jugador[] jugadores)
    {
        this.mNombre = nombre;
        if (jugadores.length != 0)
        {
            this.listaJugadores = jugadores;
        }
    }

    public Equipo(String nombre, int cantJugadores){

        this.mNombre = nombre;
        if (cantJugadores != 0)
        {
            this.listaJugadores = new Jugador[cantJugadores] ;
        }
    }

    public Equipo(int cantJugadores){
        if (cantJugadores != 0)
        {
            this.listaJugadores = new Jugador[cantJugadores] ;
        }
    }
}
