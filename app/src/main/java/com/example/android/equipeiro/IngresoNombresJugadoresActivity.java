package com.example.android.equipeiro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.equipeiro.Modelo.Jugador;

public class IngresoNombresJugadoresActivity extends AppCompatActivity {

    private int cantJugadoresRestantes = 0;
    private int cantJugadoresIngresados = 0;
    private Button btnIngresarJugador;
    private TextView lblCantJugadoresRestantes;
    private Jugador[] jugadores;
    private EditText editTextNombreJugador;
    private View.OnClickListener botonIngresoJugadoresOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onButonIngresarJugadorClicked();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_nombres_jugadores);
        //Obtengo el valor pasado en el intent
        Intent nuevoIntent = getIntent();

        if (nuevoIntent.hasExtra("CANTIDAD_JUGADORES"))
        {
            cantJugadoresRestantes = nuevoIntent.getIntExtra("CANTIDAD_JUGADORES",-1);
            jugadores = new Jugador[cantJugadoresRestantes];
        }

        lblCantJugadoresRestantes = (TextView) findViewById(R.id.lblCantJugadoresRestantes);
        btnIngresarJugador = (Button) findViewById(R.id.btnIngresarNombre);
        lblCantJugadoresRestantes.setText(String.valueOf(cantJugadoresRestantes));
        editTextNombreJugador = (EditText) findViewById(R.id.txtNombreJugador);
        btnIngresarJugador.setOnClickListener(botonIngresoJugadoresOnClickListener);

        lblCantJugadoresRestantes.setText(String.valueOf(cantJugadoresRestantes));
    }

    public void guardarNombreJugador(String nombre)
    {
        Jugador jugador = new Jugador(nombre);
        jugadores[cantJugadoresIngresados] = jugador;
        cantJugadoresIngresados++;

    }

    public void onButonIngresarJugadorClicked()
    {
        //Si todavia quedan jugadores para ingresar
        if (!(cantJugadoresRestantes == 0)){
            if (cantJugadoresRestantes >= 1)
            {
                if (editTextNombreJugador.getText() != null && !String.valueOf(editTextNombreJugador.getText()).isEmpty())
                {
                    guardarJugadorYActualizarCantidadRestante();
                }
                else{
                    mostrarToast("Debes ingresar  un nombre");
                }
            }
            if (cantJugadoresRestantes == 0){
                //Cuando se ingresa el ultimo jugador, el contador pasa a ser 0, se actualiza el boton
                cambiarTextoVista(btnIngresarJugador, "GENERAR EQUIPOS");
            }
        }
        else{
//          Si ya no quedan más jugadores para ingresar, se apreta generar equipos y se le pide confirmacion
            AlertDialog confirmarEquipos = pedirConfirmacionGeneracionEquipos();
            confirmarEquipos.show();
//
        }


    }

    private AlertDialog pedirConfirmacionGeneracionEquipos() {
//      1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//          2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message_confirmar_generacion_equipos);
        builder.setPositiveButton(
                "CONFIRMAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Iniciar nueva actividad pasando los jugadores.
                        Intent intent = parcelearJugadoresParaGenerarEquipos();
                        startActivity(intent);
                    }
                });

        builder.setNegativeButton(
                "CANCELAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dialog.cancel();
                        mostrarToast("Cancelaste la generacion");
                    }
                });

//      3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        return dialog;

    }

    private void guardarJugadorYActualizarCantidadRestante() {
        guardarJugador();
        cantJugadoresRestantes--;
        mostrarToast("Jugador ingresado");
        lblCantJugadoresRestantes.setText(String.valueOf(cantJugadoresRestantes));
    }

    /**
     * Crea un intent con un array de jugadores pasados como parceleables. Indica que actividad
     * iniciar y devuelve el intent creado
     * @return Un intent con una lista de tipo Jugador parceleados listo para iniciar actividad
     */
    @NonNull
    private Intent parcelearJugadoresParaGenerarEquipos() {
        Bundle b = new Bundle();

        b.putParcelableArray("nombresJugadores", jugadores);
        Intent intent = new Intent(this, GenerarEquiposActivity.class);
        intent.putExtras(b);
        return intent;
    }

    private void guardarJugador() {
        String nombreJugador = String.valueOf(editTextNombreJugador.getText());
        editTextNombreJugador.setText(null);
        guardarNombreJugador(nombreJugador);
    }

    public void mostrarToast(String texto)
    {
        String text = texto;
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP , 0, 650);
        toast.show();
    }

    public void cambiarTextoVista(View view, String nuevoTexto)
    {
        if (view instanceof Button)
        {
            Button boton = (Button) view;
            boton.setText(nuevoTexto);
        }
    }



}
