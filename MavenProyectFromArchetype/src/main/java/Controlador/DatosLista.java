package Controlador;

import java.util.ArrayList;

public class DatosLista {

	private ArrayList<String> nombres = new ArrayList<String>();
	private ArrayList<String> identificadores = new ArrayList<String>();
	
	public ArrayList<String> getNombres() {
		return nombres;
	}
	public void setNombres(ArrayList<String> nombres) {
		this.nombres = nombres;
	}
	public ArrayList<String> getIdentificadores() {
		return identificadores;
	}
	public void setIdentificadores(ArrayList<String> identificadores) {
		this.identificadores = identificadores;
	}
}
