package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

import Controlador.AppMusic;
import Controlador.DatosLista;
import Controlador.DatosTabla;
import Utilidades.Constantes;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JToggleButton;
import pulsador.Luz;
import ventanas.services.PaymentObserver;

import javax.swing.border.TitledBorder;

public class Principal extends JFrame implements PaymentObserver {

	private static final long serialVersionUID = 1L;
	protected static Principal unicaInstancia = null;
	private static final int X = 100;
	private static final int Y = 100;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 450;
	private GridBagLayout gbl_Columna;
	private JButton Botón_Premium;
	private JLabel Premium;
	private PanelMisListas panelLista;
	private PanelBuscar panelBuscar;
	private static JPanel Columna;
	private static JPanel principal;

	// Singleton
	public static Principal getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Principal();
		}
		return unicaInstancia;
	}

	public void removeInstancia() {
		unicaInstancia.setVisible(false);
		unicaInstancia.removeAll();
		unicaInstancia.dispose();
		unicaInstancia = null;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/recursos/Singletune_16.png")));
		setTitle("Single Tune");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X, Y, WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Columna = new JPanel();
		Columna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(Columna, BorderLayout.WEST);
		gbl_Columna = new GridBagLayout();
		gbl_Columna.columnWidths = new int[] { 2, 32, 60, 1, 0 };
		gbl_Columna.rowHeights = new int[] { 10, 32, 10, 32, 10, 32, 10, 32, 10, 32, 10, 32, 10, 0, 0, 0, 32, 10, 0 };
		gbl_Columna.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Columna.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		Columna.setLayout(gbl_Columna);

		principal = new JPanel();
		contentPane.add(principal, BorderLayout.CENTER);
		principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));

		JLabel Lupa = new JLabel("");
		Lupa.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lupa.png")));
		GridBagConstraints gbc_Lupa = new GridBagConstraints();
		gbc_Lupa.insets = new Insets(0, 0, 5, 5);
		gbc_Lupa.gridx = 1;
		gbc_Lupa.gridy = 1;
		Columna.add(Lupa, gbc_Lupa);

		JToggleButton Botón_Buscar = new JToggleButton("Buscar");
		panelBuscar = new PanelBuscar();
		GridBagConstraints gbc_Botón_Buscar = new GridBagConstraints();
		gbc_Botón_Buscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Buscar.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Buscar.gridx = 2;
		gbc_Botón_Buscar.gridy = 1;
		Botón_Buscar.addActionListener(ev -> {
			updatePanelBuscar(Botón_Buscar);
		});
		Columna.add(Botón_Buscar, gbc_Botón_Buscar);

		JLabel Gestión = new JLabel("");
		Gestión.setIcon(new ImageIcon(Principal.class.getResource("/recursos/editar_2.png")));
		GridBagConstraints gbc_Gestión = new GridBagConstraints();
		gbc_Gestión.insets = new Insets(0, 0, 5, 5);
		gbc_Gestión.gridx = 1;
		gbc_Gestión.gridy = 3;
		Columna.add(Gestión, gbc_Gestión);

		PanelGestion panelGestion = new PanelGestion();
		JToggleButton Botón_Gestión = new JToggleButton("Gestion Playlist");
		GridBagConstraints gbc_Botón_Gestión = new GridBagConstraints();
		gbc_Botón_Gestión.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Gestión.gridx = 2;
		gbc_Botón_Gestión.gridy = 3;
		Botón_Gestión.addActionListener(ev -> {
			updatePanelGestion(panelGestion, Botón_Gestión);
		});
		Columna.add(Botón_Gestión, gbc_Botón_Gestión);

		JLabel Recientes = new JLabel("");
		Recientes.setIcon(new ImageIcon(Principal.class.getResource("/recursos/reciente.png")));
		GridBagConstraints gbc_Recientes = new GridBagConstraints();
		gbc_Recientes.insets = new Insets(0, 0, 5, 5);
		gbc_Recientes.gridx = 1;
		gbc_Recientes.gridy = 5;
		Columna.add(Recientes, gbc_Recientes);

		JToggleButton Botón_Recientes = new JToggleButton("Recientes");
		PanelRecientes panelRecientes = new PanelRecientes();
		GridBagConstraints gbc_Botón_Recientes = new GridBagConstraints();
		gbc_Botón_Recientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Recientes.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Recientes.gridx = 2;
		gbc_Botón_Recientes.gridy = 5;
		Botón_Recientes.addActionListener(ev -> {
			updatePanelRecientes(Botón_Recientes, panelRecientes);
		});
		Columna.add(Botón_Recientes, gbc_Botón_Recientes);

		JLabel Playlists = new JLabel("");
		Playlists.setIcon(new ImageIcon(Principal.class.getResource("/recursos/lista.png")));
		GridBagConstraints gbc_Playlists = new GridBagConstraints();
		gbc_Playlists.insets = new Insets(0, 0, 5, 5);
		gbc_Playlists.gridx = 1;
		gbc_Playlists.gridy = 7;
		Columna.add(Playlists, gbc_Playlists);

		JToggleButton Botón_Playlists = new JToggleButton("Mis Playlists");
		panelLista = new PanelMisListas();
		GridBagConstraints gbc_Botón_Playlists = new GridBagConstraints();
		gbc_Botón_Playlists.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Playlists.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Playlists.gridx = 2;
		gbc_Botón_Playlists.gridy = 7;
		Botón_Playlists.addActionListener(ev -> {
			updatePanelPlaylists(Botón_Playlists);
		});
		Columna.add(Botón_Playlists, gbc_Botón_Playlists);

		JButton Botón_Logout = new JButton("Logout");
		GridBagConstraints gbc_Botón_Logout = new GridBagConstraints();
		gbc_Botón_Logout.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Logout.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Logout.gridx = 2;
		gbc_Botón_Logout.gridy = 16;
		Botón_Logout.addActionListener(ev -> {
			AppMusic.getUnicaInstancia().mostrarVentanaSelector(this);
		});

		JLabel URL = new JLabel("");
		URL.setIcon(new ImageIcon(Principal.class.getResource("/recursos/enlace.png")));
		GridBagConstraints gbc_URL = new GridBagConstraints();
		gbc_URL.insets = new Insets(0, 0, 5, 5);
		gbc_URL.gridx = 1;
		gbc_URL.gridy = 9;
		Columna.add(URL, gbc_URL);

		JToggleButton Boton_URL = new JToggleButton("URL");
		GridBagConstraints gbc_Boton_URL = new GridBagConstraints();
		gbc_Boton_URL.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_URL.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_URL.gridx = 2;
		gbc_Boton_URL.gridy = 9;
		PanelURL panelURL = new PanelURL();
		Boton_URL.addActionListener(e -> {
			updatePanelURL(Boton_URL, panelURL);
		});
		Columna.add(Boton_URL, gbc_Boton_URL);

		comprobarPremium();

		JLabel Logout = new JLabel("");
		Logout.setIcon(new ImageIcon(Principal.class.getResource("/recursos/cerrar-sesion.png")));
		GridBagConstraints gbc_Logout = new GridBagConstraints();
		gbc_Logout.insets = new Insets(0, 0, 5, 5);
		gbc_Logout.gridx = 1;
		gbc_Logout.gridy = 16;
		Columna.add(Logout, gbc_Logout);
		Columna.add(Botón_Logout, gbc_Botón_Logout);

		JPanel Layout = new JPanel();
		principal.add(Layout);
		Layout.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		PanelEstilos panelEstilos = new PanelEstilos();
		Layout.add(panelEstilos);

		Luz luz = new Luz();
		luz.setEncendido(true);
		luz.setColor(new Color(0, 128, 0));
		luz.addEncendidoListener(ev -> {
			luz.setEncendido(true);
			AppMusic.getUnicaInstancia().añadirCancionNueva();
		});
		Layout.add(luz);

	}

	private void comprobarPremium() {
		if (!AppMusic.getUnicaInstancia().isUsuarioActivoPremium()) {
			añadirBotonPremium();
		} else {
			updatePremium();
		}
	}

	private void añadirBotonPremium() {
		Premium = new JLabel("");
		Premium.setIcon(new ImageIcon(Principal.class.getResource("/recursos/calidad-premium.png")));
		GridBagConstraints gbc_Premium = new GridBagConstraints();
		gbc_Premium.insets = new Insets(0, 0, 5, 5);
		gbc_Premium.gridx = 1;
		gbc_Premium.gridy = 11;
		Columna.add(Premium, gbc_Premium);

		Botón_Premium = new JButton("Premium");
		GridBagConstraints gbc_Botón_Premium = new GridBagConstraints();
		gbc_Botón_Premium.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Premium.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Premium.gridx = 2;
		gbc_Botón_Premium.gridy = 11;
		Columna.add(Botón_Premium, gbc_Botón_Premium);
		Botón_Premium.addActionListener(ev -> {
			VentanaPago.getInstancia().setVisible(true);
			AppMusic.getUnicaInstancia().setVentanaActual(Principal.getInstancia());
		});
	}

	private void updatePanelURL(JToggleButton Boton_URL, PanelURL panelURL) {
		if (!Boton_URL.isSelected()) {
			principal.remove(panelURL);
			principal.revalidate();
			principal.repaint();
		} else {
			principal.add(panelURL);
			principal.revalidate();
			principal.repaint();
		}
	}

	private void updatePanelPlaylists(JToggleButton Botón_Playlists) {
		if (!Botón_Playlists.isSelected()) {
			principal.remove(panelLista);
			principal.revalidate();
			principal.repaint();
		} else {
			DatosLista datos = AppMusic.getUnicaInstancia().getMisPlaylists();
			if (datos != null) {
				datos.getNombres().add(Utilidades.Constantes.NOMBRE_FAVORITAS);
				panelLista.setLista(datos.getNombres());
				principal.add(panelLista);
				principal.revalidate();
				principal.repaint();
			} else {
				AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_LISTA_VACIA_MENSAJE);
			}
		}
	}

	private void updatePanelRecientes(JToggleButton Botón_Recientes, PanelRecientes panelRecientes) {
		if (!Botón_Recientes.isSelected()) {
			principal.remove(panelRecientes);
			principal.revalidate();
			principal.repaint();
		} else {
			DatosTabla datos = AppMusic.getUnicaInstancia().buscarRecientes();
			if (datos != null) {
				panelRecientes.setTable(datos);
				principal.add(panelRecientes);
				principal.revalidate();
				principal.repaint();
			} else {
				AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_TABLA_VACIA_MENSAJE);
			}
		}
	}

	private void updatePanelGestion(PanelGestion panelGestion, JToggleButton Botón_Gestión) {
		if (!Botón_Gestión.isSelected()) {
			principal.remove(panelGestion);
			principal.revalidate();
			principal.repaint();
		} else {
			principal.add(panelGestion, BorderLayout.NORTH);
			principal.revalidate();
			principal.repaint();
		}
	}

	private void updatePanelBuscar(JToggleButton Botón_Buscar) {
		if (!Botón_Buscar.isSelected()) {
			principal.remove(panelBuscar);
			principal.revalidate();
			principal.repaint();
		} else {
			panelBuscar.updateSelector();
			principal.add(panelBuscar);
			principal.revalidate();
			principal.repaint();
		}
	}

	@Override
	public void updatePremium() {
		gbl_Columna.columnWidths = new int[] { 2, 32, 60, 1, 0 };
		gbl_Columna.rowHeights = new int[] { 10, 32, 10, 32, 10, 32, 10, 32, 10, 32, 10, 32, 10, 32, 10, 0, 32, 10, 0 };
		gbl_Columna.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Columna.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };

		if (!AppMusic.getUnicaInstancia().isUsuarioActivoPremium()) {
			Columna.remove(Botón_Premium);
			Columna.remove(Premium);
		}

		JLabel PDF = new JLabel("");
		PDF.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo-pdf.png")));
		GridBagConstraints gbc_PDF = new GridBagConstraints();
		gbc_PDF.insets = new Insets(0, 0, 5, 5);
		gbc_PDF.gridx = 1;
		gbc_PDF.gridy = 11;
		Columna.add(PDF, gbc_PDF);

		JButton Botón_PDF = new JButton("Generar PDF");
		GridBagConstraints gbc_Botón_PDF = new GridBagConstraints();
		gbc_Botón_PDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_PDF.gridx = 2;
		gbc_Botón_PDF.gridy = 11;
		gbc_Botón_PDF.gridwidth = 1;
		Columna.setLayout(gbl_Columna);
		Botón_PDF.addActionListener(ev2 -> {
			crearPDF();
		});
		Columna.add(Botón_PDF, gbc_Botón_PDF);

		JLabel Tendencias = new JLabel("");
		Tendencias.setIcon(new ImageIcon(Principal.class.getResource("/recursos/fuego.png")));
		GridBagConstraints gbc_Tendencias = new GridBagConstraints();
		gbc_Tendencias.insets = new Insets(0, 0, 5, 5);
		gbc_Tendencias.gridx = 1;
		gbc_Tendencias.gridy = 13;
		Columna.add(Tendencias, gbc_Tendencias);

		JToggleButton Botón_Tendencias = new JToggleButton("Tendencias");
		PanelTendencias panelTendencias = new PanelTendencias();
		GridBagConstraints gbc_Botón_Tendencias = new GridBagConstraints();
		gbc_Botón_Tendencias.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Tendencias.gridx = 2;
		gbc_Botón_Tendencias.gridy = 13;
		gbc_Botón_Tendencias.gridwidth = 1;
		Columna.setLayout(gbl_Columna);
		Botón_Tendencias.addActionListener(ev2 -> {
			updatePanelTendencias(Botón_Tendencias, panelTendencias);
		});
		Columna.add(Botón_Tendencias, gbc_Botón_Tendencias);

		AppMusic.getUnicaInstancia().setUsuarioActivoPremium();

		Columna.revalidate();
		Columna.repaint();
	}

	private void updatePanelTendencias(JToggleButton Botón_Tendencias, PanelTendencias panelTendencias) {
		if (!Botón_Tendencias.isSelected()) {
			principal.remove(panelTendencias);
			principal.revalidate();
			principal.repaint();
		} else {
			DatosTabla datos = AppMusic.getUnicaInstancia().buscarTendencias();
			if (datos != null) {
				panelTendencias.setTable(datos);
				principal.add(panelTendencias);
				principal.revalidate();
				principal.repaint();
			} else {
				AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_TABLA_VACIA_MENSAJE);
			}
		}
	}

	private void crearPDF() {
		if (AppMusic.getUnicaInstancia().crearPDF()) {
			AppMusic.getUnicaInstancia().showPopup(Constantes.EXITO_CREAR_PDF_MENSAJE);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_CREAR_PDF_MENSAJE);
		}
	}

	public PanelMisListas getPanelLista() {
		return panelLista;
	}

}
