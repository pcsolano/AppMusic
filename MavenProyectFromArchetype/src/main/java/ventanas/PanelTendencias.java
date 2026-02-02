package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class PanelTendencias extends JPanel implements NextPreviousObserver, RutaObserver {

	private static final long serialVersionUID = 1L;
	private AppTabla table;
	private JPanel panel;
	private PanelReproduccionMP3 Panel_Reproducción;
	private JScrollPane scrollPane;

	public PanelTendencias() {
		super();

		this.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tendencias", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 10, 0, 278, 0, 10, 0 };
		gbl_panel_3.rowHeights = new int[] { 10, 0, 10, 0, 10, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel_3);

		table = new AppTabla();
		scrollPane = new JScrollPane(table);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		this.add(scrollPane, gbc_table);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		Panel_Reproducción = new PanelReproduccionMP3(this, this);
		panel.add(Panel_Reproducción, BorderLayout.NORTH);

		this.setVisible(true);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(DatosTabla datos) {
		this.remove(scrollPane);
		table = new AppTabla(datos);
		scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		this.add(scrollPane, gbc_table);
	}

	@Override
	public void nextUpdate() {
		String ruta = Panel_Reproducción.getRutaCancionReproduciendo();
		int id = table.nextCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
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
		int id = table.previousCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
		if (id != -1) {
			ruta = AppMusic.getUnicaInstancia().buscarRutaCancion(id);
			Panel_Reproducción.playCancion(ruta);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_PREVIOUS_MENSAJE);
		}
	}

	@Override
	public String updateRuta() {
		return table.getRutaCancionSeleccionada();
	}

}
