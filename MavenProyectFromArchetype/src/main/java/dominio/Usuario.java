package dominio;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	private int id;
	private String nombre;
	private String email;
	private String password;
	private String fechaNacimiento;
	private boolean premium;
	private LinkedList<Playlist> playlists;
	private Descuento desc;
	private Playlist recientes;
	private Playlist favoritas;

	public Usuario(String nombre, String email, String contraseña, String fechanac) {
		this.nombre = nombre;
		this.email = email;
		this.password = contraseña;
		this.fechaNacimiento = fechanac;
		premium = false;
		playlists = new LinkedList<Playlist>();
		recientes = new Playlist(Utilidades.Constantes.NOMBRE_RECIENTES);
		favoritas = new Playlist(Utilidades.Constantes.NOMBRE_FAVORITAS);
		desc = new DescuentoFijo();
	}

	public void realizarPago() {
		premium = true;
	}

	public void addPlaylist(Playlist p) {
		playlists.add(p);
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public LinkedList<Playlist> getPlaylists() {
		return playlists;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean b) {
		premium = b;
	}

	public void setPlaylists(List<Playlist> playlists2) {
		playlists = (LinkedList<Playlist>) playlists2;
	}

	public void añadirRecientes(Cancion c) {
		recientes.addCancion(c);
		if (recientes.getCanciones().size() > Utilidades.Constantes.LIMITE_PLAYLIST_ESTANDAR) {
			recientes.eliminarUltimaCancion();
		}
	}

	public void setRecientes(Playlist recientes) {
		this.recientes = recientes;
	}

	public Playlist getRecientes() {
		return recientes;
	}

	public Descuento getDesc() {
		return desc;
	}

	public void setDesc(Descuento desc) {
		this.desc = desc;
	}
	
	public void añadirFavorita(Cancion c) {
		this.favoritas.addCancion(c);
	}
	
	public void eliminarFavorita(Cancion c) {
		this.favoritas.eliminarCancion(c);
	}

	public void setFavoritas(Playlist favoritas) {
		this.favoritas = favoritas;
	}

	public Playlist getFavoritas() {
		return favoritas;
	}

	public void setPremium2(String b) {
		premium = b.equals("true");
	}

	public String getPremium() {
		if (premium) {
			return "true";
		} else {
			return "false";
		}
	}

	public boolean isCancionFavourite(int idCancion) {
		return favoritas.contains(idCancion);
	}

	public boolean isCancionFavourite(Cancion cancion) {
		return favoritas.contains(cancion);
	}

}
