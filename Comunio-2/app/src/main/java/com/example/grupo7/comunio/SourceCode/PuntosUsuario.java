package com.example.grupo7.comunio.SourceCode;

import java.util.ArrayList;

/**
 * Created by Pablo on 26/11/2015.
 */
public class PuntosUsuario {

    private String usuario;
    private ArrayList<Integer> puntosPorJornada;

    public PuntosUsuario (String u, ArrayList<Integer> p){
        this.usuario = u;
        this.puntosPorJornada = p;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public ArrayList<Integer> getPuntosPorJornada(){
        return this.puntosPorJornada;
    }

    public Integer getSumaTotal (){
        Integer i = 0;
        for (int j = 0; j < this.puntosPorJornada.size(); j++){
            Integer in= this.getPuntosPorJornada().get(j);
            if (in != null) i+=in;
        }
        return i;
    }

    @Override
    public boolean equals (Object p2){
        PuntosUsuario puntos = (PuntosUsuario) p2;
        return (this.usuario.equals(puntos.getUsuario()));
    }


}
