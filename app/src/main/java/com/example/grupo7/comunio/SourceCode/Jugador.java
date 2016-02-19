package com.example.grupo7.comunio.SourceCode;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Jugador {
	//ATRIBUTOS
	private String nombre;
	private Integer  edad;
	private Posicion posicion;
	private int precio;
	private ArrayList<Integer> puntosJugador=new ArrayList<Integer>();
	private String equipo;
	private String miequipo;

	public static final double  PORCENTAJE_RECUPERAR = 0.1;
	
	
	//CONSTRUCTORES
	public Jugador(String nombre, Integer edad, Posicion posicion, int precio, ArrayList<Integer> puntosJugador, String equipo, String miequipo) {
		this.nombre = nombre;
		this.edad = edad;
		this.posicion = posicion;
		this.precio= precio;
		this.puntosJugador = puntosJugador;
		this.equipo = equipo;
		this.miequipo = miequipo;
	}

	public Jugador(String nombre, Integer edad, Posicion posicion, int precio, ArrayList<Integer> puntosJugador, String equipo) {
		this.nombre = nombre;
		this.edad = edad;
		this.posicion = posicion;
		this.precio= precio;
		this.puntosJugador = puntosJugador;
		this.equipo = equipo;
	}
	
	//MÉTODOS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getPrecio() {
		return precio;
	}

	public ArrayList<Integer> getPuntosJugador() {
		return puntosJugador;
	}

	public void setPuntosJugador(ArrayList<Integer> puntosJugador) {
		this.puntosJugador = puntosJugador;
	}
	
	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getMiequipo() {
		return miequipo;
	}

	public void setMiequipo(String miequipo) {
		this.miequipo = miequipo;
	}

	public String toString(){
		int resultado=0;
		String datosBasicos=	
					"▪  Nombre:			"+ this.nombre+"\n" +
					"▪  Edad:			     "+ this.edad + "\n" +
				    "▪  Posición:		  "+ this.posicion+"\n" +
				    "▪  Precio:			    "+ StaticElements.customFormat(this.precio) + "\n" +
				    "▪  Puntos:			  ";
		int sumaPuntos = 0;
		for (Integer i: this.getPuntosJugador()){
			sumaPuntos += i;
		}
		return datosBasicos + sumaPuntos;
	}

	public String toStringNoPoints(){
		int resultado=0;
		String datosBasicos=
				"▪  Nombre:			"+ this.nombre+"\n" +
						"▪  Edad:			     "+ this.edad + "\n" +
						"▪  Posición:		  "+ this.posicion+"\n" +
						"▪  Precio:			    "+ StaticElements.customFormat(this.precio) + "\n";
		return datosBasicos;
	}

	public String toStringNoPointsLowPrice(){
		int resultado=0;
		String datosBasicos=
				"▪  Nombre:			"+ this.nombre+"\n" +
						"▪  Edad:			     "+ this.edad + "\n" +
						"▪  Posición:		  "+ this.posicion+"\n" +
						"▪  Precio:			    "+ StaticElements.customFormat(getLowPrice(this.precio)) + "\n";
		return datosBasicos;
	}

	public String toStringJornadas (){
		String jornadas = "";
		for (int i = 0; i < this.getPuntosJugador().size(); i ++ ){
			jornadas = jornadas + "Jornada " + (i+1) + ":     " + this.getPuntosJugador().get(i) + " pt";
			if (i < this.getPuntosJugador().size() - 1) jornadas+= "\n";
		}
		return jornadas;
	}

	public static int getLowPrice(int p){
		double precioDouble = (double) p;
		precioDouble = precioDouble*Jugador.PORCENTAJE_RECUPERAR;
		return (int) precioDouble;
	}

}
