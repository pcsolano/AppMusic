package ventanas;

import javax.swing.ImageIcon;

import Controlador.AppMusic;
import ventanas.services.NextPreviousObserver;
import ventanas.services.RutaObserver;

public class PanelReproduccionURL extends PanelReproduccion {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelReproduccionURL(NextPreviousObserver nextPreviousObserver, RutaObserver rutaObserver) {
		super(nextPreviousObserver, rutaObserver);
	}

	public boolean playCancion() {
		rutaCancionActual = rutaService.notifyObserver();
		return auxPlayCancion(rutaCancionActual);
	}

	public boolean playCancion(String cancion) {
		boolean resultado = auxPlayCancion(cancion);
		// Solo si se consigue reproducir la canción se establece la canción recibida
		// como la canción a reproducir
		if (resultado) {
			this.rutaCancionActual = cancion;
		}
		return resultado;
	}

	private boolean auxPlayCancion(String cancion){
		playService.notifyPlaylist();
		if (cancion == null)
			return false;
		boolean resultado = AppMusic.getUnicaInstancia().reproducircancionURL(cancion);
		if (resultado == false) {
			AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_PLAY_URL_MENSAJE);
		} else {
			Play_Stop.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/pausa.png")));
			Play_Stop.setSelected(true);
			rutaCancionReproduciendo = cancion;
			revalidate();
			repaint();
		}
		return resultado;
	}
}