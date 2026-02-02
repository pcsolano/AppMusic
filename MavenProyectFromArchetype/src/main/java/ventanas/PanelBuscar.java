package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import Controlador.DatosTabla;
import Utilidades.Constantes;
import ventanas.services.PlaylistNameObserver;

public class PanelBuscar extends JPanel implements PlaylistNameObserver {

	private static final long serialVersionUID = 1L;
	private static PanelBuscar unicaInstancia;
	private JCheckBox Botón_Favoritas;
	private JButton Botón_Buscar;
	private HintTextField Texto_Titulo;
	private HintTextField Texto_Interprete;
	private JComboBox<String> Estilo;
	private JComboBox<String> Selección_Playlist = new JComboBox<String>();
	private PanelResultado panelResultado;

	// Singleton
	public static PanelBuscar getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new PanelBuscar();
		}
		return unicaInstancia;
	}

	public PanelBuscar() {
		super();

		this.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelBuscar = new GridBagLayout();
		gbl_panelBuscar.columnWidths = new int[] { 20, 10, 198, 0, 30, 50, 10, 20, 0 };
		gbl_panelBuscar.rowHeights = new int[] { 10, 20, 10, 20, 10, 20, 10, 0, 00, 0 };
		gbl_panelBuscar.columnWeights = new double[] { 0.0, 0.0, 0.0, 2.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelBuscar.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panelBuscar);

		Texto_Interprete = new HintTextField("Interprete");
		GridBagConstraints gbc_Texto_Interprete = new GridBagConstraints();
		gbc_Texto_Interprete.gridwidth = 2;
		gbc_Texto_Interprete.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Interprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Interprete.gridx = 2;
		gbc_Texto_Interprete.gridy = 1;
		this.add(Texto_Interprete, gbc_Texto_Interprete);
		Texto_Interprete.setColumns(10);

		Botón_Favoritas = new JCheckBox("Favoritas");
		GridBagConstraints gbc_Botón_Favoritas = new GridBagConstraints();
		gbc_Botón_Favoritas.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Favoritas.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Favoritas.gridx = 5;
		gbc_Botón_Favoritas.gridy = 1;
		this.add(Botón_Favoritas, gbc_Botón_Favoritas);

		Texto_Titulo = new HintTextField("Titulo");
		GridBagConstraints gbc_Texto_Titulo = new GridBagConstraints();
		gbc_Texto_Titulo.gridwidth = 2;
		gbc_Texto_Titulo.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Titulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Titulo.gridx = 2;
		gbc_Texto_Titulo.gridy = 3;
		this.add(Texto_Titulo, gbc_Texto_Titulo);
		Texto_Titulo.setColumns(10);

		Estilo = new JComboBox<String>();
		GridBagConstraints gbc_Estilo = new GridBagConstraints();
		gbc_Estilo.gridwidth = 2;
		gbc_Estilo.insets = new Insets(0, 0, 5, 5);
		gbc_Estilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Estilo.gridx = 2;
		gbc_Estilo.gridy = 5;
		añadirEstilos();
		this.add(Estilo, gbc_Estilo);

		Botón_Buscar = new JButton("Buscar");
		panelResultado = new PanelResultado(this);
		GridBagConstraints gbc_Botón_Buscar = new GridBagConstraints();
		gbc_Botón_Buscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Botón_Buscar.insets = new Insets(0, 0, 5, 5);
		gbc_Botón_Buscar.gridx = 5;
		gbc_Botón_Buscar.gridy = 3;
		Botón_Buscar.addActionListener(ev -> {
			buscarCanciones(gbl_panelBuscar);
		});
		this.add(Botón_Buscar, gbc_Botón_Buscar);
	}

	private void buscarCanciones(GridBagLayout gbl_panelBuscar) {
		String titulo = Texto_Titulo.getText();
		if (titulo.equals("Titulo")) {
			titulo = "";
		}
		String interprete = Texto_Interprete.getText();
		if (interprete.equals("Interprete")) {
			interprete = "";
		}

		DatosTabla datos = AppMusic.getUnicaInstancia().buscarCanciones(titulo, interprete,
				(String) Estilo.getSelectedItem(), Botón_Favoritas.isSelected());
		if (datos != null) {
			gbl_panelBuscar.columnWidths = new int[] { 20, 10, 198, 0, 30, 50, 10, 20, 0 };
			gbl_panelBuscar.rowHeights = new int[] { 10, 20, 10, 20, 10, 20, 10, 0, 10, 0 };
			gbl_panelBuscar.columnWeights = new double[] { 0.0, 0.0, 0.0, 2.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panelBuscar.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
			GridBagConstraints gbc_panelResultado = new GridBagConstraints();
			gbc_panelResultado.fill = GridBagConstraints.BOTH;
			gbc_panelResultado.gridx = 1;
			gbc_panelResultado.gridy = 7;
			gbc_panelResultado.gridwidth = 6;
			panelResultado.setTable(datos);
			this.setLayout(gbl_panelBuscar);
			this.add(panelResultado, gbc_panelResultado);
			panelResultado.setVisible(true);

			// Recuperamos los nombres de las playlists del usuario y las añadimos al
			// comboBox
			updateSelector();
			GridBagConstraints gbc_Selección_Playlist = new GridBagConstraints();
			gbc_Selección_Playlist.fill = GridBagConstraints.HORIZONTAL;
			gbc_Selección_Playlist.insets = new Insets(0, 0, 5, 5);
			gbc_Selección_Playlist.gridx = 5;
			gbc_Selección_Playlist.gridy = 5;
			this.add(Selección_Playlist, gbc_Selección_Playlist);

			this.revalidate();
			this.repaint();
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_TABLA_VACIA_MENSAJE);
		}
	}

	private void añadirEstilos() {
		Estilo.addItem("");
		for (String s : Constantes.ESTILOS_MUSICALES) {
			Estilo.addItem(s);
		}
	}

	public void updateSelector() {
		List<String> new_values = AppMusic.getUnicaInstancia().getMisPlaylists().getNombres();
		Selección_Playlist.removeAllItems();
		for (String s : new_values) {
			Selección_Playlist.addItem(s);
		}
	}

	@Override
	public String updateName() {
		return (String) Selección_Playlist.getSelectedItem();
	}

}
