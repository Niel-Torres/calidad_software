package com.example.grupo7.comunio.SourceCode;

import android.content.Context;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Equipo{

	private String nombre;
	private Double balance;
	private Integer posicionTabla;
	private ArrayList<Integer> puntosJornada=new ArrayList<Integer>();
	private ArrayList<Jugador> jugadores=new ArrayList<Jugador>();
	private int valor;

	private Context context;
	
	//falta constructor por defecto, que genere automaticamente un equipo aleatorio.
	
	//constructor que crea un equipo desde lógica, y lo guarda en sistema de ficheros.
	public Equipo(String nombre, Double balance, Integer posicionTabla,ArrayList<Integer> puntosJornada,
			 ArrayList<Jugador> jugadores) {
		super();
		this.nombre = nombre;
		this.balance = balance;
		this.posicionTabla = posicionTabla;
		this.puntosJornada = puntosJornada;
		this.jugadores = jugadores;
		this.valor = this.calcularValorEquipo();  //método auxiliar en la parte inferior.
		//this.alineacion1=alineacion;
		
		//guardamos equipo en el sistema de ficheros
		String path= this.nombre+"\\data.txt";
		this.guardarEquipo(path);
	}
	
//	//constructor que crea un equipo en la capa de lógica cargando desde un fichero.
//	public Equipo(String nombre, Context context){
//		super();
//		this.context = context;
//		//cargamos los datos básicos del equipo desde el sistema de ficheros
//		String path = "data.txt";
//		this.cargarDatosEquipo(path, this.context);
//		//vamos cargando los jugadores para configurar el arraylist de jugadores
//		File directorio=new File(System.getProperty("user.dir")+"\\"+nombre);
//		if(directorio.isDirectory()){
//			String[] ficheros = directorio.list();
//			for(String archivo:ficheros){
//				if(!(archivo.equals("data.txt"))){
//					Jugador miembro=new Jugador(System.getProperty("user.dir")+"\\"+nombre+"\\"+archivo);
//					this.jugadores.add(miembro);
//				}
//			}
//		}
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ArrayList<Integer> getPuntosJornada() {
		return puntosJornada;
	}

	public void setPuntosJornada(ArrayList<Integer> puntosJornada) {
		this.puntosJornada = puntosJornada;
	}

	public Integer getPosicionTabla() {
		return posicionTabla;
	}

	public void setPosicionTabla(Integer posicionTabla) {
		this.posicionTabla = posicionTabla;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString(){
		String informacionBasica=
											"▪  Nombre:  "+this.nombre+"\n"+
											"▪  Balance:  "+this.balance+"\n"+
											"▪  Clasificación:  "+this.posicionTabla+"\n"+
											"▪  Valor:  "+this.valor+"\n";
		String puntosJornada="";
		for(Integer elemento:this.puntosJornada){
			puntosJornada+=elemento+"\n";
		}
		String jugadores="";
		for(Jugador elemento:this.jugadores){
			jugadores+=elemento.toString()+"\n"+"***********"+"\n";
		}
		
		return informacionBasica+puntosJornada+jugadores;
	}




	//método que calula el valor del equipo a partir de el de sus jugadores
	private int calcularValorEquipo(){
		int total=0;
		for(Jugador elemento:jugadores){
			total+=elemento.getPrecio();
		}
		return total;
	}
	
	//metodo que guarda el equipo en un fichero
	private void guardarEquipo(String path){
		try{
			FileWriter equipo=new FileWriter(path,true);
			BufferedWriter buffer=new BufferedWriter(equipo);
			buffer.write(this.nombre);
			buffer.newLine();
			buffer.write(this.balance.toString());
			buffer.newLine();
			buffer.write(this.posicionTabla.toString());
			buffer.newLine();
			buffer.write(this.valor);
			buffer.newLine();
			for(Integer elemento:puntosJornada){
				buffer.write(elemento.toString());
				buffer.newLine();
			}
			
			buffer.flush();
			buffer.close();
			}catch(IOException e){
			System.out.println("Error de E/S al intentar guardar el equipo en el sistema de ficheros");
		}
	}
	
	//metodo que carga el equipo en un fichero
	/*private void cargarDatosEquipo(String path){

		try{
			FileReader equipo = new FileReader(path);
			//InputStreamReader archivo = new InputStreamReader(context.openFileInput(path));
			BufferedReader buffer=new BufferedReader(equipo);
				this.nombre=buffer.readLine();
				this.balance=Double.parseDouble(buffer.readLine());
				this.posicionTabla=Integer.parseInt(buffer.readLine());
				this.valor=Double.parseDouble(buffer.readLine());
				
				String linea=buffer.readLine();
				while(linea!=null){
					this.puntosJornada.add(Integer.parseInt(linea));
					linea=buffer.readLine();
				}
				buffer.close();
			
		}catch(IOException e){
			System.out.println("Error al cargar el fichero " + path);
		}
	}*/

	public static String cargarDatosEquipo(String fileName, Context context) {

		StringBuilder stringBuilder = new StringBuilder();
		String line;
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
			while ((line = in.readLine()) != null) stringBuilder.append(line);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
//	//metodo que carga los jugadores pertenecientes a una posición
//	public ArrayList<Jugador> cargarJugadoresPosicion(String posicion){
//		ArrayList<Jugador> jugadores=new ArrayList<Jugador>();
//		//expresiones regulares
//		String patron="^"+posicion;
//		Pattern patrones= Pattern.compile(patron);
//
//		File directorio=new File(System.getProperty("user.dir")+"\\"+this.nombre);
//		if(directorio.isDirectory()){
//			String[] ficheros=directorio.list();
//			for(String archivo:ficheros){
//				if(patrones.matcher(archivo).find()){
//					Jugador miembro=new Jugador(System.getProperty("user.dir")+"\\"+this.nombre+"\\"+archivo);
//					if(posicion.equals("PORTERO")){
//						this.porteros.add(miembro);
//						 jugadores=this.porteros;
//					}else if(posicion.equals("DEFENSA")){
//						this.defensas.add(miembro);
//						jugadores=this.defensas;
//					}else if(posicion.equals("MEDIO")){
//						this.centros.add(miembro);
//						jugadores=this.centros;
//					}else if(posicion.equals("DELANTERO")){
//						this.delanteros.add(miembro);
//						jugadores= this.delanteros;
//					}
//				}
//			}
//		}
//		return jugadores;
//	}
}
