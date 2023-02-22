package com.example.cheems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int ubicacion = 0;
    private int counter = 0;
    private int numBtns = 12;
    private TextView textViewResultado;
    private TextView textViewContador;
    private Button btnReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();

        textViewContador = (TextView)findViewById(R.id.textViewContador);
        textViewResultado = (TextView)findViewById(R.id.textViewResultado);
        Button btnReiniciar = (Button)findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(this);

        for (int i = 1; i <= numBtns; i++) {
            ImageButton btnSeleccion = getImageBtn(i);
            btnSeleccion.setOnClickListener(this);
        }
    }

    public void start () {
        for (int i = 1; i <= numBtns; i++) {
        ImageButton btnSeleccion = getImageBtn(i);
            btnSeleccion.setBackgroundResource(R.drawable.icon_pregunta);
        }
        handleButtons(true);
        Random random = new Random();
        ubicacion = random.nextInt(11)+1;
    }

    public void handleButtons(Boolean isEnable){
        for (int i = 1; i <= numBtns; i++) {
            ImageButton btnSeleccion = getImageBtn(i);
            btnSeleccion.setEnabled(isEnable);
        }
    }

    public void destapar(int opcion){
        if(opcion == ubicacion){
            for (int i = 1; i <= numBtns; i++) {
                ImageButton btnSeleccion = getImageBtn(i);
                if (i == opcion) {
                    if(counter >= 11){
                        btnSeleccion.setBackgroundResource(R.drawable.icon_cheems_party);
                        textViewResultado.setText("GANAMSTE!");
                    }else{
                        btnSeleccion.setBackgroundResource(R.drawable.icon_cheems_llora);
                        textViewResultado.setText("PERDIMSTE!");
                    }

                    handleButtons(false);
                    textViewContador.setText("");
                }else{
                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems);
                }
            }

        }else{
            counter += 1;
            textViewContador.setText("Cartas destapadas: " + counter);
            ImageButton btnSeleccion = getImageBtn(opcion);
            btnSeleccion.setBackgroundResource(R.drawable.icon_cheems);
            btnSeleccion.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnReiniciar ) {
            start();
            counter = 0;
            textViewResultado.setText("");
            textViewContador.setText("");
        }else{
            for (int i = 1; i <= numBtns; i++) {
                if(view.getId() == getResources().getIdentifier("opcion"+i, "id",this.getPackageName())){
                    destapar(i);
                }
            }
        }

        if(counter == 11){
            for (int i = 1; i <= numBtns; i++) { destapar(i);}
            textViewContador.setText("");
            handleButtons(false);
        }
    }

    public void getImageBtn(int opcion){
        ImageButton btnSeleccion = (ImageButton)findViewById((
                getResources().getIdentifier("opcion" + opcion, "id",this.getPackageName())));

        return btnSeleccion;
    }
}