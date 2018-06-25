package com.example.android.equipeiro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SeleccionTipoFutbolActivity extends AppCompatActivity {

    private int cantJugadores = 0;
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_tipo_futbol);
    }

    public void onButtonClicked(View view) {
        // Is the button now checked?
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupTipoFutbol);

        if (rg.getCheckedRadioButtonId() == -1)
        {
            String text = "SELECCIONA UNA OPCION";
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            // one of the radio buttons is checked
            // Check which radio button was clicked
            switch(rg.getCheckedRadioButtonId())
            {
                case R.id.rbtnF5:
                    // se apreto f5
                    cantJugadores = 10;
                    break;
                case R.id.rbtnF7:
                    // Apreto f7
                    cantJugadores = 14;
                    break;
                case R.id.rbtnF11:
                    //Apreto f11
                    cantJugadores = 22;
                    break;
            }
            Intent intent = new Intent(this, IngresoNombresJugadoresActivity.class);
            intent.putExtra("CANTIDAD_JUGADORES", cantJugadores);
            startActivity(intent);
        }
    }
}
