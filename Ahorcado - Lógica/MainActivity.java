package inc.alfaleon.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner2;
    List<String> letras = new ArrayList<String>();
    List<String> posiciones = new ArrayList<String>();
    String[] myResArray = null;
    Button botonJugar;
    String[] myResArray2 = null;
    Button iniciar;
    Button finalizar;
    TextView pantalla;
    EditText totalVidas;
    StringBuilder rellena;
    StringBuilder oculta;
    TextView puntos;
    StringBuilder s = new StringBuilder();
    RadioButton facil;
    RadioButton medio;
    RadioButton dificil;
    String palabra;
    int vidas;
    int sumaPuntos;
    TextView descubrir;
    Control control = new Control();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        botonJugar = (Button) findViewById(R.id.button);
        iniciar = (Button) findViewById(R.id.button2);
        finalizar = (Button) findViewById(R.id.button3);
        pantalla = (EditText) findViewById(R.id.editText);
        facil = (RadioButton) findViewById(R.id.radioButton);
        medio = (RadioButton) findViewById(R.id.radioButton3);
        dificil = (RadioButton) findViewById(R.id.radioButton4);
        totalVidas = (EditText) findViewById(R.id.editText5);
        puntos = (EditText) findViewById(R.id.editText4);
        descubrir = (TextView) findViewById(R.id.descubrirPalabra);


        myResArray = getResources().getStringArray(R.array.letras);
        letras = Arrays.asList(myResArray);
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letras);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adaptador);

        myResArray2 = getResources().getStringArray(R.array.posiciones);
        posiciones = Arrays.asList(myResArray2);
        ArrayAdapter adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posiciones);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador2);

        spinner.setEnabled(false);
        spinner2.setEnabled(false);
        botonJugar.setEnabled(false);
        finalizar.setEnabled(false);


        //BOTÓN INICIAR
        iniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iniciar.setEnabled(false);


                spinner.setEnabled(true);
                spinner2.setEnabled(true);
                botonJugar.setEnabled(true);
                finalizar.setEnabled(true);
                totalVidas.setKeyListener(null);
                puntos.setEnabled(false);
                totalVidas.setEnabled(false);
                pantalla.setEnabled(false);


                //Establecer dificultad
                if (facil.isChecked()) {

                    vidas = 15;
                    totalVidas.setText(String.valueOf(String.valueOf(vidas)));
                    facil.setEnabled(false);
                    medio.setEnabled(false);
                    dificil.setEnabled(false);
                } else if (medio.isChecked()) {

                    vidas = 10;
                    totalVidas.setText(String.valueOf(vidas));
                    facil.setEnabled(false);
                    medio.setEnabled(false);
                    dificil.setEnabled(false);
                } else if (dificil.isChecked()) {

                    vidas = 5;
                    totalVidas.setText(String.valueOf(vidas));
                    facil.setEnabled(false);
                    medio.setEnabled(false);
                    dificil.setEnabled(false);

                }


                palabra = control.elegir();
                rellena = new StringBuilder(palabra);
                oculta = new StringBuilder(palabra);
                for (int i = 0; i < oculta.length(); i++) {
                    oculta.setCharAt(i, '-');
                }
                pantalla.setText(oculta);
                descubrir.setText(rellena);

            }
        });


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantalla.setText("");
                botonJugar.setEnabled(false);
                spinner.setEnabled(false);
                iniciar.setEnabled(true);

            }
        });

        botonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = spinner2.getSelectedItem().toString();
                char selection = c.charAt(0);


                    String position = spinner.getSelectedItem().toString();



                    int posicion = Integer.parseInt(position) - 1;


                    for (int i = 0; i < oculta.length(); i++) {
                        if (rellena.charAt(posicion) == selection) {
                            oculta.setCharAt(posicion, selection);
                            pantalla.setText(oculta);


                        }
                    }
                    if (rellena.charAt(posicion) == selection) {
                        sumaPuntos += 5;
                        puntos.setText(String.valueOf("Puntos: " + sumaPuntos));
                    }


                    if (rellena.charAt(posicion) != selection) {
                        vidas--;
                        totalVidas.setText(String.valueOf("Vidas: " + vidas));
                    }

                    if (vidas <= 0) {
                        Toast sinVidas = Toast.makeText(getApplicationContext(), "Te has quedado sin vidas", Toast.LENGTH_SHORT);
                        sinVidas.show();
                        botonJugar.setEnabled(false);
                    }

                    if(rellena.equals(oculta)){
                        Toast ganador = Toast.makeText(getApplicationContext(), "Has ganado", Toast.LENGTH_SHORT);
                        botonJugar.setEnabled(false);
                        ganador.show();
                    }

                }




        });


    }


}