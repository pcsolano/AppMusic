package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import ventanas.services.NextPreviousNotificationService;
import ventanas.services.NextPreviousObserver;
import ventanas.services.PlayNotificationService;
import ventanas.services.PlayObserver;
import ventanas.services.RutaNotificactionService;
import ventanas.services.RutaObserver;

public abstract class PanelReproduccion extends JPanel implements PlayObserver {

	private static final long serialVersionUID = 1L;
	protected String rutaCancionActual = null;
	protected String rutaCancionReproduciendo = null;
	protected JToggleButton Play_Stop;
	protected PlayNotificationService playService = AppMusic.getUnicaInstancia().getPlayService();
	protected RutaNotificactionService rutaService;
	protected NextPreviousNotificationService nextPreviousService;
	
	public String getRutaCancionReproduciendo() {
		return rutaCancionReproduciendo;
	}

	/**
	 * Create the panel.
	 */
	public PanelReproduccion(NextPreviousObserver nextPreviousObserver, RutaObserver rutaObserver) {
		inicializarServicios(nextPreviousObserver, rutaObserver);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GridBagLayout gbl_Panel_Reproducción = new GridBagLayout();
		gbl_Panel_Reproducción.columnWidths = new int[] { 1, 0, 32, 10, 32, 10, 32, 10, 32, 0, 1, 0 };
		gbl_Panel_Reproducción.rowHeights = new int[] { 1, 32, 0 };
		gbl_Panel_Reproducción.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_Panel_Reproducción.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_Panel_Reproducción);

		JButton Choose_previous = new JButton("");
		Choose_previous.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/anterior.png")));
		GridBagConstraints gbc_Choose_previous = new GridBagConstraints();
		gbc_Choose_previous.anchor = GridBagConstraints.NORTHWEST;
		gbc_Choose_previous.insets = new Insets(0, 0, 0, 5);
		gbc_Choose_previous.gridx = 2;
		gbc_Choose_previous.gridy = 1;
		Choose_previous.addActionListener(ev -> {
			nextPreviousService.notifyPrevious();
		});
		this.add(Choose_previous, gbc_Choose_previous);

		JButton Restart = new JButton("");
		Restart.setIcon(
				new ImageIcon(PanelResultado.class.getResource("/recursos/forma-cuadrada-negra-redondeada.png")));
		GridBagConstraints gbc_Restart = new GridBagConstraints();
		gbc_Restart.anchor = GridBagConstraints.NORTHWEST;
		gbc_Restart.insets = new Insets(0, 0, 0, 5);
		gbc_Restart.gridx = 4;
		gbc_Restart.gridy = 1;
		Restart.addActionListener(ev -> {
			stopCancion();
		});
		this.add(Restart, gbc_Restart);

		Play_Stop = new JToggleButton("");
		Play_Stop.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/jugar.png")));
		GridBagConstraints gbc_Play_Stop = new GridBagConstraints();
		gbc_Play_Stop.anchor = GridBagConstraints.NORTHWEST;
		gbc_Play_Stop.insets = new Insets(0, 0, 0, 5);
		gbc_Play_Stop.gridx = 6;
		gbc_Play_Stop.gridy = 1;
		Play_Stop.addActionListener(ev -> {
			updateBotonPlayStop();
		});
		this.add(Play_Stop, gbc_Play_Stop);

		JButton Choose_next = new JButton("");
		Choose_next.setIcon(new ImageIcon(PanelRecientes.class.getResource("/recursos/proximo.png")));
		GridBagConstraints gbc_Choose_next = new GridBagConstraints();
		gbc_Choose_next.anchor = GridBagConstraints.NORTHWEST;
		gbc_Choose_next.insets = new Insets(0, 0, 0, 5);
		gbc_Choose_next.gridx = 8;
		gbc_Choose_next.gridy = 1;
		Choose_next.addActionListener(ev -> {
			nextPreviousService.notifyNext();
		});
		this.add(Choose_next, gbc_Choose_next);
	}

	private void updateBotonPlayStop() {
		if (!Play_Stop.isSelected()) {
			pauseCancion();
		} else {
			if (AppMusic.getUnicaInstancia().isCancionMidway()) {
				resumeCancion();
			} else {
				playCancion();
			}
		}
	}

	private void inicializarServicios(NextPreviousObserver nextPreviousObserver, RutaObserver rutaObserver) {
		this.rutaService = new RutaNotificactionService(rutaObserver);
		this.nextPreviousService = new NextPreviousNotificationService(nextPreviousObserver);
		this.playService.subscribe(this);
	}

	public abstract boolean playCancion();

	public abstract boolean playCancion(String cancion);

	public boolean stopCancion() {
		boolean resultado = AppMusic.getUnicaInstancia().stopCancion();
		if (resultado == false) {
			AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_STOP_MENSAJE);
		} else {
			Play_Stop.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/jugar.png")));
			Play_Stop.setSelected(false);
			revalidate();
			repaint();
		}
		return resultado;
	}

	public boolean pauseCancion() {
		boolean resultado = AppMusic.getUnicaInstancia().pauseCancion();
		if (resultado == false) {
			AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_PAUSE_MENSAJE);
		} else {
			Play_Stop.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/jugar.png")));
			Play_Stop.setSelected(false);
			revalidate();
			repaint();
		}
		return resultado;
	}

	public boolean resumeCancion() {
		rutaCancionActual = rutaService.notifyObserver();
		boolean resultado;
		if (rutaCancionActual == rutaCancionReproduciendo) {
			resultado = AppMusic.getUnicaInstancia().resumeCancion();
		} else {
			resultado = playCancion(rutaCancionActual);
		}
		if (resultado == false) {
			AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_RESUME_MENSAJE);
		} else {
			Play_Stop.setIcon(new ImageIcon(PanelResultado.class.getResource("/recursos/pausa.png")));
			Play_Stop.setSelected(true);
			revalidate();
			repaint();
		}
		return resultado;
	}

	@Override
	public void updateBoton() {
		stopCancion();
	}
}
