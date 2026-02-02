package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import ventanas.services.NextPreviousObserver;
import ventanas.services.RutaObserver;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class PanelURL extends JPanel implements NextPreviousObserver, RutaObserver {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private PanelReproduccion Panel_Reproducción;
	private JLabel URL;
	private HintTextField Texto_URL;
	private JButton Boton_Buscar;
	private JButton Boton_Borrar;
	private JLabel Nombre;

	public PanelURL() {
		super();

		this.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 10, 35, 139, 139, 10, 0 };
		gbl_panel_3.rowHeights = new int[] { 10, 0, 35, 35, 5, 35, 10, 35, 0, 10, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel_3);

		Nombre = new JLabel("URL Actual");
		Nombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_Nombre = new GridBagConstraints();
		gbc_Nombre.gridwidth = 2;
		gbc_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_Nombre.gridx = 2;
		gbc_Nombre.gridy = 2;
		add(Nombre, gbc_Nombre);

		URL = new JLabel("URL:");
		GridBagConstraints gbc_URL = new GridBagConstraints();
		gbc_URL.anchor = GridBagConstraints.EAST;
		gbc_URL.insets = new Insets(0, 0, 5, 5);
		gbc_URL.gridx = 1;
		gbc_URL.gridy = 3;
		add(URL, gbc_URL);

		Texto_URL = new HintTextField("URL");
		GridBagConstraints gbc_Texto_URL = new GridBagConstraints();
		gbc_Texto_URL.gridwidth = 2;
		gbc_Texto_URL.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_URL.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_URL.gridx = 2;
		gbc_Texto_URL.gridy = 3;
		add(Texto_URL, gbc_Texto_URL);

		Boton_Buscar = new JButton("Buscar");
		GridBagConstraints gbc_Boton_Buscar = new GridBagConstraints();
		gbc_Boton_Buscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Buscar.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Buscar.gridx = 2;
		gbc_Boton_Buscar.gridy = 5;
		Boton_Buscar.addActionListener(ev -> {
			playCancion();
		});
		add(Boton_Buscar, gbc_Boton_Buscar);

		Boton_Borrar = new JButton("Borrar");
		GridBagConstraints gbc_Boton_Borrar = new GridBagConstraints();
		gbc_Boton_Borrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Borrar.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Borrar.gridx = 3;
		gbc_Boton_Borrar.gridy = 5;
		Boton_Borrar.addActionListener(ev -> {
			Texto_URL.setText("");
		});
		add(Boton_Borrar, gbc_Boton_Borrar);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 7;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		Panel_Reproducción = new PanelReproduccionURL(this, this);
		panel.add(Panel_Reproducción, BorderLayout.NORTH);

		this.setVisible(true);

	}

	private void playCancion() {
		if (Panel_Reproducción.playCancion(Texto_URL.getText())) {
			Nombre.setText(Texto_URL.getText());
		}
	}

	@Override
	public void nextUpdate() {
		AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_NEXT_MENSAJE);
	}

	@Override
	public void previousUpdate() {
		AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_PREVIOUS_MENSAJE);
	}

	@Override
	public String updateRuta() {
		return Texto_URL.getText();
	}
}
