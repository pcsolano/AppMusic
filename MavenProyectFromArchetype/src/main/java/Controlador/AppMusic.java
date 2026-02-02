package Controlador;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import Utilidades.Constantes;
import Utilidades.CargadorCanciones;
import dominio.Cancion;
import dominio.Playlist;
import dominio.Reproductor;
import dominio.SinDescuento;
import dominio.CatalogoCanciones;
import dominio.CatalogoUsuarios;
import dominio.CreadorPDF;
import dominio.Descuento;
import dominio.DescuentoFijo;
import dominio.DescuentoJovenes;
import dominio.Usuario;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;
import ventanas.services.PlayNotificationService;
import persistencia.IAdaptadorCancionDAO;
import persistencia.IAdaptadorPlaylistDAO;

public class AppMusic {

	private static AppMusic unicaInstancia;

	// Atributos compartidos
	private IAdaptadorUsuarioDAO adaptadorUsuario; // No se utiliza actualmente
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorPlaylistDAO adaptadorPlaylist;

	private static String estilo = Constantes.ESTILO_POR_DEFECTO;

	private JFrame ventanaActual = new JFrame();

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	private Usuario usuarioActivo;

	private PlayNotificationService playService = new PlayNotificationService();

	public AppMusic() {
		// Debe ser la primera linea para evitar error de sincronización
		inicializarAdaptadores();
		inicializarCatalogos();
		try {
			CargadorCanciones.INSTANCE.cargarCanciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AppMusic();
		}
		return unicaInstancia;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorPlaylist = factoria.getPlaylistDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	public Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	public JFrame getVentanaActual() {
		return ventanaActual;
	}

	public void setVentanaActual(JFrame ventanaActual) {
		this.ventanaActual = ventanaActual;
	}

	public List<Cancion> getCanciones() {
		return catalogoCanciones.getCanciones();
	}

	public List<Usuario> getUsuarios() {
		return catalogoUsuarios.getUsuarios();
	}

	public PlayNotificationService getPlayService() {
		return playService;
	}

	public Descuento getDescuentoUsuario() {
		return usuarioActivo.getDesc();
	}

	public void setDescuentoUsuario(Usuario usuario, Descuento desc) {
		usuario.setDesc(desc);
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {

		// Si el estilo eslegido es el acutal, no hacer nada
		if (estilo.equals(AppMusic.estilo)) {
		} else {
			// Se eliminan todas las instancias de las ventanas
			limpiarVentanas();

			if (estilo.equals("Devil")) { // Estilos especiales
				// TODO
			} else { // Estilos normales
				AppMusic.estilo = estilo;
				estilo = "com.jtattoo.plaf." + estilo.toLowerCase() + "." + estilo + "LookAndFeel";
				try {
					UIManager.setLookAndFeel(estilo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Y se vuelve a crear la instancia, pero ahora con el nuevo estilo seleccionado
			mostrarVentanaPrincipal();
		}
	}

	public Cancion getCancion(int id) {
		// return adaptadorCancion.recuperarCancion(id);
		return CatalogoCanciones.getUnicaInstancia().getCancion(id);
	}

	public Cancion getCancion(String ruta) {
		List<Cancion> cancion = catalogoCanciones.getCanciones().stream().filter(c -> c.getrutaFichero().equals(ruta))
				.toList();
		return cancion.get(0);
	}

	public String buscarRutaCancion(int id) {
		return catalogoCanciones.getCancion(id).getrutaFichero();
	}

	public void registrarCancion(Cancion Cancion) {
		adaptadorCancion.registrarCancion(Cancion);
		catalogoCanciones.addCancion(Cancion);
	}

	public void registrarPlaylist(Playlist Playlist) {
		adaptadorPlaylist.registrarPlaylist(Playlist);
	}

	public void limpiarVentanas() {
		// Se incluyen manualmente debido a problemas de herencia
		ventanas.Principal.getInstancia().removeInstancia();
		ventanas.Inicio.Selector.getInstancia().removeInstancia();
		ventanas.Inicio.Login.getInstancia().removeInstancia();
		ventanas.Inicio.LoginGit.getInstancia().removeInstancia();
		ventanas.Inicio.Registro.getInstancia().removeInstancia();
	}

	public void mostrarVentanaPrincipal() {
		limpiarVentanas();
		ventanas.Principal.getInstancia().setVisible(true);
		AppMusic.getUnicaInstancia().setVentanaActual(ventanas.Principal.getInstancia());
	}

	public void mostrarVentanaSelector(Container container) {
		container.setVisible(false);
		ventanas.Inicio.Selector.getInstancia().setVisible(true);
		AppMusic.getUnicaInstancia().setVentanaActual(ventanas.Inicio.Selector.getInstancia());
	}

	public boolean verficarUsuario(String usuario, String contraseña) {
		usuarioActivo = catalogoUsuarios.exists(usuario, contraseña);
		return usuarioActivo != null;
	}

	public boolean verficarUsuarioGit(String usuario, String contraseña, String rutaCertificado) {
		try {
			GitHub github = GitHubBuilder.fromPropertyFile(rutaCertificado).build();

			if (github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();
				System.out.println("Validado! " + ghuser.getLogin());
				if (ghuser.getLogin().equals(usuario) && github.isCredentialValid()) {
					usuarioActivo = new Usuario(usuario, null, contraseña, null);
					CatalogoUsuarios.getUnicaInstancia().addUsuario(usuarioActivo);
					adaptadorUsuario.registrarUsuario(usuarioActivo);
					return usuarioActivo != null;
				}

				return false;
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int registrarUsuario(String usuario, String email, String contraseña, Date fecha, String nombre_completo,
			String desc) {
		String s_fecha = fecha.toString();
		if (usuario.isEmpty() || email.isEmpty() || contraseña.isEmpty() || s_fecha.isEmpty()
				|| nombre_completo.isEmpty()) {
			return Constantes.ERROR_REGISTRO_CAMPOS;
		}
		if (usuario.equals("Usuario") || email.equals("Email") || contraseña.equals("Contraseña")
				|| fecha.equals("d MMM y") || nombre_completo.equals("Nombre completo")) {
			return Constantes.ERROR_REGISTRO_CAMPOS;
		}
		if (catalogoUsuarios.emailEnUso(email)) {
			return Constantes.ERROR_REGISTRO_CORREO;
		}
		if (fecha.after(new Date())) {
			return Constantes.ERROR_REGISTRO_FECHA;
		}
		Descuento descuento = determinarDescuento(desc);
		if (descuento == null) {
			return Constantes.ERROR_REGISTRO_DESCUENTO;
		}
		Usuario usuario2 = catalogoUsuarios.addUsuario(usuario, email, contraseña, s_fecha);
		setDescuentoUsuario(usuario2, descuento);
		return Constantes.OKAY;
	}

	private Descuento determinarDescuento(String desc) {
		Descuento descuento = null;
		if (desc == Utilidades.Constantes.DESCUENTOS[0]) {
			descuento = new SinDescuento();
		} else if (desc == Utilidades.Constantes.DESCUENTOS[1]) {
			descuento = new DescuentoFijo();
		} else if (desc == Utilidades.Constantes.DESCUENTOS[2]) {
			descuento = new DescuentoJovenes();
		}
		return descuento;
	}

	public void showPopup(String mensaje) {
		JOptionPane.showMessageDialog(ventanaActual, mensaje, Constantes.NOMBRE_APLICACION,
				JOptionPane.INFORMATION_MESSAGE, null);
	}

	public boolean existeUsuario(String nombre) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(nombre) != null;
	}

	/**
	 * Checks if the active user has the specified playlist.
	 * 
	 * @param playlist The playlist to check.
	 * @return true if the active user has the playlist, false otherwise.
	 */
	public boolean existePlaylist(String playlist) {
		for (Playlist p : usuarioActivo.getPlaylists()) {
			if (p.getNombre().equals(playlist)) {
				return true;
			}
		}
		return false;
	}

	public DatosTabla buscarCanciones(String titulo, String interprete, String estilo, boolean favorita) {
		// La idea es devolver los datos dentro de la estructura de datos
		DatosTabla nuevos_datos = new DatosTabla();
		List<Cancion> canciones;
		if (favorita) {
			canciones = getfavoritas(usuarioActivo).getCanciones();
		} else {
			canciones = catalogoCanciones.getCanciones();
		}
		canciones.forEach(c -> {
			if (c.getTitulo().contains(titulo) && c.getInterprete().contains(interprete)) {
				if (estilo.isEmpty() || c.getEstilomusical().equals(estilo)) {
					añadirDatosTabla(c, nuevos_datos);
				}
			}
		});
		nuevos_datos.setFavoritas(getListaCheckFavoritas(canciones));
		return nuevos_datos;
	}

	public DatosTabla buscarCanciones(String playlist) {
		DatosTabla p = new DatosTabla();
		if (playlist.equals(Utilidades.Constantes.NOMBRE_FAVORITAS)) {
			añadirDatosTabla(usuarioActivo.getFavoritas(), p);
		} else {
			p = getPlaylist(playlist);
		}
		return p;
	}

	public DatosTabla buscarTendencias() {
		DatosTabla nuevos_datos = new DatosTabla();
		List<Cancion> cancionesOrdenadas = catalogoCanciones.cancionesOrdenadas();
		cancionesOrdenadas.stream()
			.sorted(Comparator.comparingInt(c -> ((Cancion) c).getnumReproducciones()).reversed())
			.limit(Utilidades.Constantes.LIMITE_PLAYLIST_ESTANDAR)
			.forEach(c -> {
				añadirDatosTabla(c, nuevos_datos);
			});
		nuevos_datos.setFavoritas(getListaCheckFavoritas(cancionesOrdenadas));
		return nuevos_datos;
	}

	public DatosTabla buscarRecientes() {
		DatosTabla nuevos_datos = new DatosTabla();
		añadirDatosTabla(usuarioActivo.getRecientes(), nuevos_datos);
		nuevos_datos.setFavoritas(getListaCheckFavoritas(usuarioActivo.getRecientes()));
		return nuevos_datos;
	}

	/**
	 * @param favoritas
	 * @return
	 */
	public DatosLista getMisPlaylists() {
		// La idea es devolver los nombres y los identificadores
		DatosLista nuevos_datos = new DatosLista();
		for (Playlist p : usuarioActivo.getPlaylists()) {
			nuevos_datos.getNombres().add(p.getNombre());
			nuevos_datos.getIdentificadores().add(Integer.valueOf(p.getId()).toString());
		}
		return nuevos_datos;
	}

	public DatosTabla getPlaylist(String selectedValue) {
		// La idea es devolver los datos dentro de la estructura de datos
		DatosTabla nuevos_datos = new DatosTabla();
		for (Playlist p : usuarioActivo.getPlaylists()) {
			if (p.getNombre().equals(selectedValue)) {
				añadirDatosTabla(p, nuevos_datos);
				nuevos_datos.setFavoritas(getListaCheckFavoritas(p));
				break;
			}
		}
		return nuevos_datos;
	}

	/**
	 * Creates a PDF document.
	 * 
	 * @return true if the PDF document is created successfully, false otherwise.
	 */
	public boolean crearPDF() {
		return CreadorPDF.getUnicaInstancia().crearPDF(usuarioActivo);
	}

	/**
	 * Adds a new playlist with the specified title to the active user's playlist
	 * list.
	 * 
	 * @param titulo The title of the playlist to add.
	 * @return true if the playlist is added successfully, false otherwise.
	 */
	public boolean añadirPlaylist(String titulo) {
		Playlist nuevaPlaylist = new Playlist(titulo);
		usuarioActivo.addPlaylist(nuevaPlaylist);
		adaptadorPlaylist.registrarPlaylist(nuevaPlaylist);
		adaptadorUsuario.modificarUsuario(usuarioActivo);
		return true;
	}

	public boolean eliminarPlaylist(String playlist) {
		// Se quiere eliminar la playlist con el titulo recibido de la lista de
		// playlists del usuario activo
		Iterator<Playlist> iterator = usuarioActivo.getPlaylists().iterator();
		while (iterator.hasNext()) {
			Playlist p = iterator.next();
			if (p.getNombre().equals(playlist)) {
				iterator.remove();
				adaptadorUsuario.modificarUsuario(usuarioActivo);
				adaptadorPlaylist.borrarPlaylist(p);
				return true;
			}
		}
		return false;
	}

	public boolean eliminarCancionPlaylist(String titulo, String playlist) {
		// Se quiere eliminar la cancion con el titulo recivido de la playlist recivida
		// del usuario activo
		for (Playlist p : usuarioActivo.getPlaylists()) {
			if (p.getNombre().equals(playlist)) {
				Iterator<Cancion> iterator = p.getCanciones().iterator();
				while (iterator.hasNext()) {
					Cancion c = iterator.next();
					if (c.getTitulo().equals(titulo)) {
						iterator.remove();
						adaptadorPlaylist.modificarPlaylist(p);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean actualizarPlaylist(String playlists, DatosTabla datos) {
		try {
			for (Playlist p : usuarioActivo.getPlaylists()) {
				if (p.getNombre().equals(playlists)) {
					// Borrar los datos de la playlist
					p.borrarCanciones();
					// Extraer datos
					ArrayList<Integer> f = datos.getIds();
					// Añadir datos extraidos a la playlist
					// Modificar playlist
					for (int a : f) {
						p.addCancion(adaptadorCancion.recuperarCancion(a));
					}
					adaptadorPlaylist.modificarPlaylist(p);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean añadirCancionPlaylist(String playlist, int idCancion) {
		try {
			for (Playlist p : usuarioActivo.getPlaylists()) {
				if (p.getNombre().equals(playlist)) {
					p.addCancion(getCancion(idCancion));
					adaptadorPlaylist.modificarPlaylist(p);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean añadirCancion(String rutaFichero, String interprete, String titulo) {
		try {

			// Crear la nueva canción con el intérprete y el título
			Cancion nuevaCancion = new Cancion(titulo, rutaFichero);
			nuevaCancion.setInterprete(interprete);
			nuevaCancion.setEstilomusical("OTRO");

			// Registrar la canción y añadirla al catálogo
			adaptadorCancion.registrarCancion(nuevaCancion);
			catalogoCanciones.addCancion(nuevaCancion);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Reproductor getReproductor() {
		return Reproductor.getUnicaInstancia();
	}

	public boolean reproducircancion(String rutaFichero) {
		try {
			for (Cancion c : CatalogoCanciones.getUnicaInstancia().getCanciones()) {
				if (c.getrutaFichero().equals(rutaFichero)) {
					c.addView();
					addRecientes(c);
					Reproductor.getUnicaInstancia().playCancionFich("./src/main/resources/canciones/",rutaFichero);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addRecientes(Cancion cancion) {
		try {
			usuarioActivo.añadirRecientes(cancion);
			adaptadorUsuario.modificarUsuario(usuarioActivo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean reproducircancionURL(String i) {
		return Reproductor.getUnicaInstancia().playCancion(i);
	}

	public boolean stopCancion() {
		return Reproductor.getUnicaInstancia().stopCancion();
	}

	public boolean pauseCancion() {
		return Reproductor.getUnicaInstancia().pauseCancion();
	}

	public boolean resumeCancion() {
		return Reproductor.getUnicaInstancia().reanudarCancion();
	}

	public boolean isCancionMidway() {
		return Reproductor.getUnicaInstancia().isCancionMidway();
	}

	public boolean añadirDatosTabla(Cancion c, DatosTabla datos) {
		try {
			datos.getTitulos().add(c.getTitulo());
			datos.getInterpretes().add(c.getInterprete());
			datos.getEstilos().add(c.getEstilomusical());
			datos.getIds().add(c.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean añadirDatosTabla(Playlist p, DatosTabla datos) {
		try {
			datos.setFavoritas(p);
			p.getCanciones().stream().forEach(c -> añadirDatosTabla(c, datos));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isUsuarioActivoPremium() {
		return usuarioActivo.isPremium();
	}

	public boolean setUsuarioActivoPremium() {
		usuarioActivo.setPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActivo);
		return true;
	}

	public Playlist getfavoritas(Usuario usuario) {
		return usuario.getFavoritas();
	}

	public boolean isCancionFavourite(int idCancion) {
		return usuarioActivo.isCancionFavourite(idCancion);
	}

	public ArrayList<Boolean> getListaCheckFavoritas(List<Cancion> canciones) {
		ArrayList<Boolean> favoritas = new ArrayList<Boolean>();
		for (Cancion c : canciones) {
			favoritas.add(AppMusic.getUnicaInstancia().isCancionFavourite(c.getId()));
		}
		return favoritas;
	}

	public ArrayList<Boolean> getListaCheckFavoritas(Playlist playlist) {
		return getListaCheckFavoritas(playlist.getCanciones());
	}

	public boolean addView(String ruta) {
		return getCancion(ruta).addView();
	}

	public void addCancionFavorita(int id) {
		usuarioActivo.añadirFavorita(getCancion(id));
		adaptadorUsuario.modificarUsuario(usuarioActivo);
	}

	public void eliminarCancionFavorita(int id) {
		usuarioActivo.eliminarFavorita(getCancion(id));
		adaptadorUsuario.modificarUsuario(usuarioActivo);
	}

	public void añadirCancionNueva() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Audios", "mp3");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(ventanaActual);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String ruta = chooser.getSelectedFile().getPath();
			String nombreFichero = new java.io.File(ruta).getName();
			String[] parte = nombreFichero.split("/");

			int f = parte.length;

			String nombreFichero2 = parte[f - 1];

			String[] partes = nombreFichero2.split("-");
			if (partes.length < 2) {
				throw new IllegalArgumentException("El nombre del fichero debe estar en formato interprete-titulo");
			}
			String interprete = partes[0].trim();
			String titulo = partes[1].trim();
			Path origenPath = Paths.get(ruta);
			Path destinoPath = Paths.get("./src/main/resources/canciones/OTRO/" + interprete + " - " + titulo);
			try {
				Files.copy(origenPath, destinoPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AppMusic.getUnicaInstancia().añadirCancion("OTRO/" + interprete + " - " + titulo, interprete, titulo);
			System.out.println(interprete);
		}
	}

}
