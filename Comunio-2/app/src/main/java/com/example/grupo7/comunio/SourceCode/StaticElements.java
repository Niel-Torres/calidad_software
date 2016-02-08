package com.example.grupo7.comunio.SourceCode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Pablo on 29/10/2015.
 * This class is used as a session data container, in order to call the data base just when it is totally necessary
 */
public class StaticElements {

    private static String user;
    private static Integer saldo;
    private static ArrayList<Jugador> arrayMercado = new ArrayList<>();
    private static ArrayList<Jugador> arrayMiEquipo = new ArrayList<>();

    public static final Jugador JUGADORVACIO = new Jugador ("", null, null, 0, null, null);

    private static int currentAlin = 442;
    private static Jugador[] alineacion442 = {JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO};
    private static Jugador[] alineacion433 = {JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO};
    private static Jugador[] alineacion343 = {JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO, JUGADORVACIO};
    private static ShowPlayerButtons spbStatic;
    public static ArrayList<MensajeInicio> mensajesCambiados = new ArrayList<>();
    public static ArrayList<PuntosUsuario> puntosUsuarios = new ArrayList<>();
    public static int puntosUsuario;

    public static boolean cambiadaAlin;


    public static String getUser(){
        return StaticElements.user;
    }

    public static void setUser(String u){
        StaticElements.user = u;
    }

    public static int getSaldo(){
        return StaticElements.saldo;
    }

    public static void setSaldo(int s){
        StaticElements.saldo = s;
    }

    public static ArrayList<Jugador> getArrayMercado(){
        return StaticElements.arrayMercado;
    }

    public static ArrayList<Jugador> getArrayMiEquipo(){
        return StaticElements.arrayMiEquipo;
    }

    public static ShowPlayerButtons getSpbStatic(){
        return StaticElements.spbStatic;
    }

    public static void setSpbStatic(ShowPlayerButtons spb){
        StaticElements.spbStatic = spb;
    }

    //Funciones de tratamiento de caracteres especiales
    private static final String ORIGINAL
            = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT
            = "AaEeIiOoUuNnUu";
    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    public static String customFormat (int i){
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,### €");
        return myFormatter.format(i);
    }

    public static void removeMercado (){
        StaticElements.arrayMercado = new ArrayList<>();
    }
    public static void removeMiEquipo (){
        StaticElements.arrayMiEquipo = new ArrayList<>();
    }

    public static Jugador[] getAlineacion442(){
        return StaticElements.alineacion442;
    }

    public static Jugador[] getAlineacion433(){
        return StaticElements.alineacion433;
    }

    public static Jugador[] getAlineacion343(){
        return StaticElements.alineacion343;
    }


    private static ArrayList<Jugador> removeEquals (Jugador[] alin) {
        ArrayList<Jugador> misJugadoresAux = new ArrayList<>();
        for(Jugador ju: StaticElements.arrayMiEquipo){
            misJugadoresAux.add(ju);
        }
        for (Jugador j : alin) {
            misJugadoresAux.remove(j);
        }
        return misJugadoresAux;
    }


    private static ArrayList<Jugador> removeDiffPosition(ArrayList<Jugador> misJ, Posicion p){
        for (int i = 0; i < misJ.size(); i++){
            if (!(misJ.get(i).getPosicion().equals(p))){
                misJ.remove(i);
                i--;
            }
        }
        return misJ;
    }


    public static ArrayList<Jugador> getDelanterosBanquillo442(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion442);
        return removeDiffPosition(misJugadoresAux, Posicion.DELANTERO);
    }

    public static ArrayList<Jugador> getMediosBanquillo442(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion442);
        return removeDiffPosition(misJugadoresAux, Posicion.MEDIO);
    }
    public static ArrayList<Jugador> getDefensasBanquillo442(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion442);
        return removeDiffPosition(misJugadoresAux, Posicion.DEFENSA);
    }
    public static ArrayList<Jugador> getPorterosBanquillo442(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion442);
        return removeDiffPosition(misJugadoresAux, Posicion.PORTERO);
    }

    public static ArrayList<Jugador> getDelanterosBanquillo433(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion433);
        return removeDiffPosition(misJugadoresAux, Posicion.DELANTERO);
    }

    public static ArrayList<Jugador> getMediosBanquillo433(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion433);
        return removeDiffPosition(misJugadoresAux, Posicion.MEDIO);
    }
    public static ArrayList<Jugador> getDefensasBanquillo433(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion433);
        return removeDiffPosition(misJugadoresAux, Posicion.DEFENSA);
    }
    public static ArrayList<Jugador> getPorterosBanquillo433(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion433);
        return removeDiffPosition(misJugadoresAux, Posicion.PORTERO);
    }

    public static ArrayList<Jugador> getDelanterosBanquillo343(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion343);
        return removeDiffPosition(misJugadoresAux, Posicion.DELANTERO);
    }

    public static ArrayList<Jugador> getMediosBanquillo343(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion343);
        return removeDiffPosition(misJugadoresAux, Posicion.MEDIO);
    }
    public static ArrayList<Jugador> getDefensasBanquillo343(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion343);
        return removeDiffPosition(misJugadoresAux, Posicion.DEFENSA);
    }
    public static ArrayList<Jugador> getPorterosBanquillo343(){
        ArrayList<Jugador> misJugadoresAux = removeEquals(StaticElements.alineacion343);
        return removeDiffPosition(misJugadoresAux, Posicion.PORTERO);
    }

    public static int getCurrentAlin(){
        return StaticElements.currentAlin;
    }

    public static void setCurrentAlin(int i){
        StaticElements.currentAlin = i;
    }
}
