package com.example.grupo7.comunio.PopUpMenus;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Jugador;
import com.example.grupo7.comunio.SourceCode.StaticElements;

import java.util.ArrayList;

/**
 * Created by Pablo on 13/11/2015.
 * Esta clase se encarga de enlazar los botones de "Alineación" con los 3 arrays de alineaciones que hay, dando y quitando titularidad a los jugadores.
 */
public class ButtonToAlin {


    public void buttonToAlin(int id, String nombre){
        switch (id){
            case R.id.button1:
                StaticElements.getAlineacion442()[0] = getJugador(nombre);
                break;
            case R.id.button2:
                StaticElements.getAlineacion442()[1] = getJugador(nombre);
                break;
            case R.id.button3:
                StaticElements.getAlineacion442()[2] = getJugador(nombre);
                break;
            case R.id.button4:
                StaticElements.getAlineacion442()[4] = getJugador(nombre);
                break;
            case R.id.button5:
                StaticElements.getAlineacion442()[5] = getJugador(nombre);
                break;
            case R.id.button6:
                StaticElements.getAlineacion442()[3] = getJugador(nombre);
                break;
            case R.id.button7:
                StaticElements.getAlineacion442()[6] = getJugador(nombre);
                break;
            case R.id.button8:
                StaticElements.getAlineacion442()[8] = getJugador(nombre);
                break;
            case R.id.button9:
                StaticElements.getAlineacion442()[9] = getJugador(nombre);
                break;
            case R.id.button10:
                StaticElements.getAlineacion442()[7] = getJugador(nombre);
                break;
            case R.id.button11:
                StaticElements.getAlineacion442()[10] = getJugador(nombre);
                break;
            case R.id.button12:
                StaticElements.getAlineacion433()[0] = getJugador(nombre);
                break;
            case R.id.button13:
                StaticElements.getAlineacion433()[1] = getJugador(nombre);
                break;
            case R.id.button14:
                StaticElements.getAlineacion433()[2] = getJugador(nombre);
                break;
            case R.id.button15:
                StaticElements.getAlineacion433()[3] = getJugador(nombre);
                break;
            case R.id.button16:
                StaticElements.getAlineacion433()[4] = getJugador(nombre);
                break;
            case R.id.button17:
                StaticElements.getAlineacion433()[5] = getJugador(nombre);
                break;
            case R.id.button18:
                StaticElements.getAlineacion433()[6] = getJugador(nombre);
                break;
            case R.id.button19:
                StaticElements.getAlineacion433()[7] = getJugador(nombre);
                break;
            case R.id.button20:
                StaticElements.getAlineacion433()[8] = getJugador(nombre);
                break;
            case R.id.button21:
                StaticElements.getAlineacion433()[9] = getJugador(nombre);
                break;
            case R.id.button22:
                StaticElements.getAlineacion433()[10] = getJugador(nombre);
                break;
            case R.id.button23:
                StaticElements.getAlineacion343()[0] = getJugador(nombre);
                break;
            case R.id.button24:
                StaticElements.getAlineacion343()[1] = getJugador(nombre);
                break;
            case R.id.button25:
                StaticElements.getAlineacion343()[2] = getJugador(nombre);
                break;
            case R.id.button26:
                StaticElements.getAlineacion343()[3] = getJugador(nombre);
                break;
            case R.id.button27:
                StaticElements.getAlineacion343()[4] = getJugador(nombre);
                break;
            case R.id.button28:
                StaticElements.getAlineacion343()[5] = getJugador(nombre);
                break;
            case R.id.button29:
                StaticElements.getAlineacion343()[6] = getJugador(nombre);
                break;
            case R.id.button30:
                StaticElements.getAlineacion343()[7] = getJugador(nombre);
                break;
            case R.id.button31:
                StaticElements.getAlineacion343()[8] = getJugador(nombre);
                break;
            case R.id.button32:
                StaticElements.getAlineacion343()[9] = getJugador(nombre);
                break;
            case R.id.button33:
                StaticElements.getAlineacion343()[10] = getJugador(nombre);
                break;
        }
    }

    private Jugador getJugador(String n){
        Jugador j = null;
        ArrayList<Jugador> jugs = new ArrayList<>(StaticElements.getArrayMiEquipo());
        for (int i = 0; i < jugs.size(); i++){
            j = jugs.get(i);
            if (j.getNombre().equals(n)){
                break;
            }
        }
        return j;
    }

}
