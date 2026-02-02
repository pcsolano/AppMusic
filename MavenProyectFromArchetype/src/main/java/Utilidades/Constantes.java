package Utilidades;

public class Constantes {

	public static final String NOMBRE_APLICACION = "Singletune";
	public static final String NOMBRE_FAVORITAS = "Favoritas";
	public static final String NOMBRE_RECIENTES = "Recientes";

	public static final String[] DESCUENTOS = { "Normal", "Fijo", "Jovenes" };
	public static final int PRECIO_ESTANDAR = 38;
	public static final int LIMITE_PLAYLIST_ESTANDAR = 10;

	// "com.jtattoo.plaf.texture.TextureLookAndFeel"
	public static final String ESTILO_POR_DEFECTO = "Texture";
	public static final String[] ESTILOS_INTERFAZ = { "Texture", "HiFi", "Acryl", "Aero", "Mint", "Bernstein", "Fast",
			"Graphite", "Luna", "McWin", "Noire", "Smart", "Devil" };
	public static final int NUM_ESTILOS = ESTILOS_INTERFAZ.length;
	public static final String[] ESTILOS_INTERFAZ_OSCUROS = { "Hifi", "Noire", "Devil" };
	public static final String[] ESTILOS_INTERFAZ_CLAROS = { "Texture", "Acryl", "Aero", "Mint", "Bernstein", "Fast",
			"Graphite", "Luna", "McWin", "Smart" };

	public static final String[] ESTILOS_MUSICALES = { "CANTAUTOR", "CLASICA", "FLAMENCO", "JAZZ", "OPERA", "POP",
			"ROCK", "ROMANTICA", "OTRO" };

	public static final int OKAY = 0;
	public static final int ERROR_REGISTRO_CAMPOS = 1;
	public static final int ERROR_REGISTRO_CORREO = 2;
	public static final int ERROR_REGISTRO_FECHA = 3;
	public static final int ERROR_REGISTRO_DESCUENTO = 4;

	public static final String ERROR_INICIO_SESION_MENSAJE = "Los parámetros no son correctos";

	public static final String ERROR_REGISTRO_CAMPOS_MENSAJE = "Hay campos por rellenar o alguno contiene valores invalidos";
	public static final String ERROR_REGISTRO_CORREO_MENSAJE = "El correo ya está asociado a un usuario";
	public static final String ERROR_REGISTRO_FECHA_MENSAJE = "La fecha de nacimiento es incorrecta";
	public static final String ERROR_REGISTRO_GRUPO_MENSAJE = "Ha ocurrido un error asignando el grupo";

	public static final String ERROR_TABLA_VACIA_MENSAJE = "Ha ocurrido algún problema rellenando la tabla";
	public static final String ERROR_LISTA_VACIA_MENSAJE = "Ha ocurrido algún problema rellenando la lista";

	public static final String ERROR_CREAR_PDF_MENSAJE = "Ha ocurrido algún problema al crear el PDF";

	public static final String ERROR_TITULO_VACIO_MENSAJE = "El campo \"titulo\" está vacio";

	public static final String ERROR_ELIMINAR_PLAYLIST_MENSAJE = "Ha ocurrido algún problema al eliminar la playlist";
	public static final String ERROR_CREAR_PLAYLIST_MENSAJE = "Ha ocurrido algún problema al crear la playlist";
	public static final String ERROR_ACTUALIZAR_PLAYLIST_MENSAJE = "Ha ocurrido algún problema actualizando la playlist";
	public static final String ERROR_AÑADIR_CANCION_PLAYLIST_MENSAJE = "Ha ocurrido algún problema añadiendo la canción a la playlist";

	public static final String ERROR_PLAY_URL_MENSAJE = "Ha ocurrido algún problema reconociendo la url";
	public static final String ERROR_PLAY_MP3_MENSAJE = "Ha ocurrido algún problema reconociendo el archivo mp3";
	public static final String ERROR_STOP_MENSAJE = "Ha ocurrido algún problema mientras se detenia la canción";
	public static final String ERROR_NEXT_MENSAJE = "Ha ocurrido algún problema buscando la siguiente canción";
	public static final String ERROR_PREVIOUS_MENSAJE = "Ha ocurrido algún problema buscando la canción anterior";
	public static final String ERROR_RESUME_MENSAJE = "Ha ocurrido algún problema reanudando la canción";
	public static final String ERROR_PAUSE_MENSAJE = "Ha ocurrido algún problema pausando la canción";

	public static final String ERROR_PAGO = "Ha ocurrido algún problema realizando el pago";

	public static final String EXITO_CREAR_PDF_MENSAJE = "El PDF ha sido creado exitosamente";
	
	public static final String EXITO_ELIMINAR_PLAYLIST_MENSAJE = "La playlist ha sido eliminada exitosamente";
	public static final String EXITO_CREAR_PLAYLIST_MENSAJE = "La playlist ha sido creada exitosamente";
	public static final String EXITO_ACTUALIZAR_PLAYLIST_MENSAJE = "La playlist ha sido actualizada exitosamente";
	public static final String EXITO_AÑADIR_CANCION_PLAYLIST_MENSAJE = "La canción ha sido añadida exitosamente";
}
