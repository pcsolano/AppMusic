package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import Controlador.DatosTabla;
import Utilidades.Constantes;
import ventanas.services.NextPreviousObserver;
import ventanas.services.RutaObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class PanelMisListas extends JPanel implements NextPreviousObserver, RutaObserver {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private PanelReproduccionMP3 Panel_Reproducción;
	private PanelListas panelListas;

	public PanelMisListas() {
		super();

		this.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"MisPlaylists", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 10, 278, 0, 10, 0 };
		gbl_panel_3.rowHeights = new int[] { 10, 0, 10, 0, 10, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel_3);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		panelListas = new PanelListas();
		GridBagConstraints gbc_listas = new GridBagConstraints();
		gbc_listas.gridwidth = 2;
		gbc_listas.fill = GridBagConstraints.BOTH;
		gbc_listas.insets = new Insets(0, 0, 5, 5);
		gbc_listas.gridx = 1;
		gbc_listas.gridy = 1;
		add(panelListas, gbc_listas);

		Panel_Reproducción = new PanelReproduccionMP3(this, this);
		panel.add(Panel_Reproducción, BorderLayout.NORTH);
	}

	public JList<String> getLista() {
		return panelListas.getLista();
	}

	public void setLista(List<String> new_values) {
		panelListas.setLista(new_values);
	}

	public JTable getTable() {
		return panelListas.getTable();
	}

	public void setTable(DatosTabla datos) {
		panelListas.setTable(datos);
	}

	@Override
	public void nextUpdate() {
		String ruta = Panel_Reproducción.getRutaCancionReproduciendo();
		int id = panelListas.getTable().nextCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
		if (id != -1) {
			ruta = AppMusic.getUnicaInstancia().buscarRutaCancion(id);
			Panel_Reproducción.playCancion(ruta);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_NEXT_MENSAJE);
		}
	}

	@Override
	public void previousUpdate() {
		String ruta = Panel_Reproducción.getRutaCancionReproduciendo();
		int id = panelListas.getTable().previousCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
		if (id != -1) {
			ruta = AppMusic.getUnicaInstancia().buscarRutaCancion(id);
			Panel_Reproducción.playCancion(ruta);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_PREVIOUS_MENSAJE);
		}
	}

	@Override
	public String updateRuta() {
		return getRutaCancionSeleccionada();
	}

	public String getRutaCancionSeleccionada() {
		return panelListas.getTable().getRutaCancionSeleccionada();
	}

}
