package ventanas;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import Utilidades.Constantes;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class PanelEstilos extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEstilos() {
		setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"Estilo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(Constantes.ESTILOS_INTERFAZ));
		comboBox.addActionListener(ev -> {
			AppMusic.getUnicaInstancia().setEstilo((String) comboBox.getSelectedItem());
			this.revalidate();
			this.repaint();
		});
		add(comboBox, BorderLayout.CENTER);

	}

}
