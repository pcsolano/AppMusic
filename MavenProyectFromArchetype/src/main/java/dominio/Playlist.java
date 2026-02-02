package dominio;

import java.util.LinkedList;

import Controlador.AppMusic;

public class Playlist {
	private String nombre;
	private int id;
	private LinkedList<Cancion> canciones;

	public Playlist(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}

	public LinkedList<Cancion> getCanciones() {
		return canciones;
	}

	public boolean addCancion(Cancion cancion) {
		if (contains(cancion)) {
			return false;
		}
		return canciones.add(cancion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCanciones(LinkedList<Cancion> canciones) {
		this.canciones = canciones;
	}

	public void eliminarUltimaCancion() {
		canciones.removeLast();
	}

	public void borrarCanciones() {
		canciones.removeAll(canciones);
	}

	public boolean eliminarCancion(int id) {
		for (int i = 0; i < canciones.size(); i++) {
			if (canciones.get(i).getId() == id) {
				canciones.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean eliminarCancion(Cancion cancion) {
		return eliminarCancion(cancion.getId());
	}

	public boolean contains(Cancion cancion) {
		return canciones.stream().anyMatch(c -> c.equals(cancion));
	}

	public boolean contains(int idCancion) {
		return contains(AppMusic.getUnicaInstancia().getCancion(idCancion));
	}

}
