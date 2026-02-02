package ventanas;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Controlador.AppMusic;
import Controlador.DatosTabla;

public class AppTabla extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> ids = new ArrayList<Integer>();

	private String rutaCancionSeleccionada;

	public AppTabla() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Titulo", "Interprete", "Estilo", "Favorita" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		model.removeRow(0);
		setModel(model);
	}

	public AppTabla(DatosTabla datos) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Titulo", "Interprete", "Estilo", "Favorita" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Boolean.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		// Listener para mantener actualizada la ruta de la cancion seleccionada
		getSelectionModel().addListSelectionListener(ev -> {
			int id = cancionId();
			if (id != -1) {
				rutaCancionSeleccionada = AppMusic.getUnicaInstancia().buscarRutaCancion(id);
			}
		});

		// Listener para detectar cuando ha cambiado la columna "favorita"
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent evento) {
				changeFavorita();
			}
		});
		
		// Eliminar el elemento vacio inicial
		model.removeRow(0);

		// Rellenar la tabla y recoger los ids
		for (int i = 0; i < datos.getTitulos().size(); i++) {
			model.addRow(new Object[] { datos.getTitulos().get(i), datos.getInterpretes().get(i),
					datos.getEstilos().get(i), datos.getFavoritas().get(i) });
			ids.add(datos.getIds().get(i));
		}
		setModel(model);
	}

	/**
	 * Devuelve el id de la siguiente cancion en la lista
	 * 
	 * @return int, id de la cancion
	 */
	public int nextCancionId() {
		if (getSelectedRow() != -1) {
			int index = (getSelectedRow() + 1) % getRowCount();
			return ids.get(index);
		}
		return -1;
	}

	/**
	 * Devuelve el id de la cancion anterior en la lista
	 * 
	 * @return int, id de la cancion
	 */
	public int previousCancionId() {
		if (getSelectedRow() != -1) {
			int index = (getSelectedRow() - 1) % getRowCount();
			return ids.get(index);
		}
		return -1;
	}

	/**
	 * Devuelve el id de la cancion actualmente seleccionada
	 * 
	 * @return int, id de la cancion
	 */
	public int cancionId() {
		if (getSelectedRow() != -1) {
			return ids.get(getSelectedRow());
		}
		return -1;
	}

	/**
	 * Devuelve la ruta de la cancion actualmente seleccionada
	 * 
	 * @return String, ruta de la cancion
	 */
	public String getRutaCancionSeleccionada() {
		return rutaCancionSeleccionada;
	}

	/**
	 * Añade o elimina una cancion de la playlist "favoritas"
	 * 
	 * @return boolean, true si se ha completado exitosamente la operacion
	 */
	public boolean changeFavorita() {
		if (getSelectedRow() != -1) {
			if ((boolean) getValueAt(getSelectedRow(), 3)) {
				AppMusic.getUnicaInstancia().addCancionFavorita(ids.get(getSelectedRow()));
			} else {
				AppMusic.getUnicaInstancia().eliminarCancionFavorita(ids.get(getSelectedRow()));
			}
		}
		return false;
	}

	public int nextCancionId(Integer id) {
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i).intValue() == id.intValue()) {
				return ids.get((i + 1) % getRowCount());
			}
		}
		return -1;
	}
	
	public int previousCancionId(Integer id) {
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i).intValue() == id.intValue()) {
				return ids.get((i - 1) % getRowCount());
			}
		}
		return -1;
	}
}
