package com.example.grupo7.comunio.SourceCode;

/**
 * Created by Pablo on 24/11/2015.
 */
public class MensajeInicio {

    private int tipo; //(1): mensajes de compra-venta ; (2): mensajes de usuarios
    private String mensaje;

    public MensajeInicio(int tipo, String mensaje){
        this.tipo = tipo;
        this.mensaje = mensaje;
    }

    public int getTipo(){
        return this.tipo;
    }

    public String getMensaje(){
        return this.mensaje;
    }

}
