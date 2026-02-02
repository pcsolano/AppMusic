package ventanas;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controlador.AppMusic;
import Controlador.DatosTabla;

import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

public class PanelListas extends JPanel {

	private static final long serialVersionUID = 1L;
	private AppTabla table;
	private JList<String> lista;
	private JScrollPane leftScrollPane;
	private JScrollPane rightScrollPane;
	private JSplitPane splitPane;

	/**
	 * Create the panel.
	 */
	public PanelListas() {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		add(splitPane);

		crearLista();

		leftScrollPane = new JScrollPane(lista);
		splitPane.setLeftComponent(leftScrollPane);

		table = new AppTabla();
		rightScrollPane = new JScrollPane(table);
		splitPane.setRightComponent(rightScrollPane);

		setVisible(true);
	}

	private void crearLista() {
		lista = new JList<String>();
		lista.setValueIsAdjusting(true);
		lista.setVisibleRowCount(4);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		lista.getSelectionModel().addListSelectionListener(ev -> {
			actualizarTabla();
		});
	}

	private void actualizarTabla() {
		if (lista.getSelectedValue() != null) {
			DatosTabla datos = AppMusic.getUnicaInstancia().buscarCanciones(lista.getSelectedValue());
			setTable(datos);
			revalidate();
			repaint();
		}
	}

	public JList<String> getLista() {
		return lista;
	}

	public void setLista(List<String> new_values) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (String s : new_values) {
			model.addElement(s);
		}
		this.lista.setModel(model);
	}

	public AppTabla getTable() {
		return table;
	}

	public void setTable(DatosTabla datos) {
		splitPane.remove(rightScrollPane);
		table = new AppTabla(datos);
		rightScrollPane = new JScrollPane(table);
		splitPane.setRightComponent(rightScrollPane);
	}

}
