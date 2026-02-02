package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import dominio.Playlist;
import dominio.Cancion;

//Usa un pool para evitar problemas doble referencia con Cancions
public class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlaylistTDS unicaInstancia = null;

	public static AdaptadorPlaylistTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorPlaylistTDS();
		return unicaInstancia;
	}

	private AdaptadorPlaylistTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra un Playlist se le asigna un identificador �nico */
	public void registrarPlaylist(Playlist Playlist) {
		Entidad ePlaylist = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			ePlaylist = servPersistencia.recuperarEntidad(Playlist.getId());
		} catch (NullPointerException e) {}
		if (ePlaylist != null) return;

		// registrar primero los atributos que son objetos
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion p : Playlist.getCanciones())
			adaptadorCancion.registrarCancion(p);

		// crear entidad Playlist
		ePlaylist = new Entidad();
		ePlaylist.setNombre("Playlist");
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", Playlist.getNombre()),
						new Propiedad("Canciones", obtenerIdCancion(Playlist.getCanciones())))));

		// registrar entidad Playlist
		ePlaylist = servPersistencia.registrarEntidad(ePlaylist);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		Playlist.setId(ePlaylist.getId());
	}

	public void borrarPlaylist(Playlist Playlist) {
		// No se comprueban restricciones de integridad con Cancion
		Entidad ePlaylist = servPersistencia.recuperarEntidad(Playlist.getId());

		servPersistencia.borrarEntidad(ePlaylist);
	}

	public void modificarPlaylist(Playlist Playlist) {

		Entidad ePlaylist = servPersistencia.recuperarEntidad(Playlist.getId());

		for (Propiedad prop : ePlaylist.getPropiedades()) {
			if (prop.getNombre().equals("Id")) {
				prop.setValor(String.valueOf(Playlist.getId()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(Playlist.getNombre());
			} else if (prop.getNombre().equals("Canciones")) {
				String Canciones = obtenerIdCancion(Playlist.getCanciones());
				prop.setValor(Canciones);
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public Playlist recuperarPlaylist(int Id) {

		// Si la entidad est� en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(Id))
			return (Playlist) PoolDAO.getUnicaInstancia().getObjeto(Id);

		// si no, la recupera de la base de datos
		Entidad ePlaylist;
		List<Cancion> Canciones = new LinkedList<Cancion>();
		String nombre;

		// recuperar entidad
		ePlaylist = servPersistencia.recuperarEntidad(Id);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(ePlaylist, "nombre");

		Playlist Playlist = new Playlist(nombre);
		Playlist.setId(Id);

		// IMPORTANTE:a�adir el Playlist al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(Id, Playlist);

		// recuperar propiedades que son objetos llamando a adaptadores
		// Cancions
		Canciones = obtenerCancionesDesdeIds(servPersistencia.recuperarPropiedadEntidad(ePlaylist, "Canciones"));

		for (Cancion v : Canciones)
			Playlist.addCancion(v);

		return Playlist;
	}

	public List<Playlist> recuperarTodosPlaylists() {

		List<Entidad> ePlaylists = servPersistencia.recuperarEntidades("Playlist");
		List<Playlist> Playlists = new LinkedList<Playlist>();

		for (Entidad ePlaylist : ePlaylists) {
			Playlists.add(recuperarPlaylist(ePlaylist.getId()));
		}
		return Playlists;
	}

	// -------------------Funciones auxiliares-----------------------------
	private String obtenerIdCancion(List<Cancion> listaCancion) {
		String aux = "";
		for (Cancion p : listaCancion) {
			aux += p.getId() + " ";
		}
		return aux.trim();
	}

	private List<Cancion> obtenerCancionesDesdeIds(String Cancions) {

		List<Cancion> listaCancions = new LinkedList<Cancion>();
		StringTokenizer strTok = null;
		if(Cancions!=null) strTok = new StringTokenizer(Cancions, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		if (strTok!=null) {
		while (strTok.hasMoreTokens()) {
			listaCancions.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		}
		return listaCancions;
	}
}