package com.example.android.equipeiro.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Jugador implements Parcelable{
    private String mNombre;

    public Jugador(){
        super();
    }

    public Jugador(String nombreJugador)
    {
        this.mNombre = nombreJugador;
    }

    /*
    This method is the constructor, called on the receiving activity, where you will be collecting values.
    When the secondary activity calls the getParcelableExtra method of the intent object to start the process
    This constructor is where you collect the values and set up the properties of the object

    At this point you’ve populated the object with data.
     */
    public Jugador(Parcel in) {
        mNombre = in.readString();
    }


    //creator - used when un-parceling our parcle (creating the object)

    /*
    Parcelable requires this method to bind everything together. There’s little you need to
    o here as the createFromParcel method will return your newly populated object.
     */
    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {

        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    //return hashcode of object
    @Override
    public int describeContents() {
        return 0;
    }

    //write object values to parcel for storage
    /*
    In this method you add all your class properties to the parcel in preparation for transfer.
    You use each of the write methods to add each of your properties.
    -The order in which you write these values is important. When collecting these values later you
    will need to collect them in the same order.
    -If you’re going to send boolean values (for example the featured property). You will have to
    use writeValue and then force cast it to a boolean on the other side as there is no native method for adding booleans.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNombre);
    }

    public String getNombre()
    {
        return mNombre;
    }
}
