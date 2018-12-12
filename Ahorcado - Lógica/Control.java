package inc.alfaleon.ahorcado;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import inc.alfaleon.ahorcado.R;

public class Control {

    final String[] lista = new String[]{"Amalgama", "Cthulhu", "Nyarlathotep", "Azathoth", "Resina", "Experimento",
            "Redoble", "Tempo", "Caducifolio", "Emergente", "Reminiscencia", "Cauterizar", "Manticora", "Voragine"};

   public String palabra;
    StringBuilder s = new StringBuilder();


    public String ocultarPalabra(String palabra2){
        int contador=0;
        s.append(palabra2);
        for(int i=0; i<palabra2.length(); i++){
            s.replace(contador, palabra2.length(), "-");
            contador++;
        }
        return palabra2;
    }


    public String elegir(){
        int aleatorio=(int)((Math.random()*14)*14)/14;
        palabra=lista[aleatorio].toLowerCase();
        return palabra;

    }













}
