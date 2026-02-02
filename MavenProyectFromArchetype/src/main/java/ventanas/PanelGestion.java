package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.AppMusic;
import Controlador.DatosLista;
import Controlador.DatosTabla;
import Utilidades.Constantes;
import ventanas.services.NextPreviousObserver;
import ventanas.services.RutaObserver;

import java.awt.BorderLayout;

public class PanelGestion extends JPanel implements NextPreviousObserver, RutaObserver {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollPane;
	private PanelListas panelLista;
	private PanelReproduccionMP3 Panel_Reproducción;
	private HintTextField Texto_Título;

	public PanelGestion() {
		super();

		this.setBorder(new TitledBorder(null, "Gesti\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 30, 70, 10, 10, 70, 0, 10, 70, 30, 0 };
		gbl_panel_3.rowHeights = new int[] { 10, 0, 10, 0, 10, 0, 10, 0, 10, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_panel_3);

		Texto_Título = new HintTextField("Título");
		GridBagConstraints gbc_Texto_Título = new GridBagConstraints();
		gbc_Texto_Título.gridwidth = 7;
		gbc_Texto_Título.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Título.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Título.gridx = 1;
		gbc_Texto_Título.gridy = 1;
		this.add(Texto_Título, gbc_Texto_Título);
		Texto_Título.setColumns(10);

		JButton Boton_Crear = new JButton("Crear");
		Boton_Crear.setToolTipText(
				"Si no existe una playlist con el nombre escrito en \"titulo\", pulsando este boton se crea");
		GridBagConstraints gbc_Boton_Crear = new GridBagConstraints();
		gbc_Boton_Crear.gridwidth = 2;
		gbc_Boton_Crear.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Crear.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Crear.gridx = 1;
		gbc_Boton_Crear.gridy = 3;
		Boton_Crear.addActionListener(ev -> {
			crearPlaylist();
		});
		add(Boton_Crear, gbc_Boton_Crear);

		panelLista = new PanelListas();
		GridBagConstraints gbc_panelListas = new GridBagConstraints();
		gbc_panelListas.gridwidth = 7;
		gbc_panelListas.insets = new Insets(0, 0, 5, 5);
		gbc_panelListas.fill = GridBagConstraints.BOTH;
		gbc_panelListas.gridx = 1;
		gbc_panelListas.gridy = 5;
		fillLista();
		add(panelLista, gbc_panelListas);

		JButton Boton_Eliminar = new JButton("Eliminar");
		Boton_Eliminar.setToolTipText("Al pulsar este boton se elimina la playlist seleccionada en la lista");
		GridBagConstraints gbc_Boton_Eliminar = new GridBagConstraints();
		gbc_Boton_Eliminar.gridwidth = 2;
		gbc_Boton_Eliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Eliminar.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Eliminar.gridx = 4;
		gbc_Boton_Eliminar.gridy = 3;
		Boton_Eliminar.addActionListener(ev -> {
			eliminarPlaylist(panelLista.getLista().getSelectedValue());
		});
		add(Boton_Eliminar, gbc_Boton_Eliminar);

		JButton Boton_Guardar = new JButton("Guardar");
		Boton_Guardar
				.setToolTipText("Al pulsar este boton se guardan los cambios realizados en la playlist seleccionada");
		GridBagConstraints gbc_Boton_Guardar = new GridBagConstraints();
		gbc_Boton_Guardar.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Guardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Guardar.gridx = 7;
		gbc_Boton_Guardar.gridy = 3;
		Boton_Guardar.addActionListener(ev -> {
			actualizarPlaylist();
		});
		add(Boton_Guardar, gbc_Boton_Guardar);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		Panel_Reproducción = new PanelReproduccionMP3(this, this);
		panel.add(Panel_Reproducción, BorderLayout.NORTH);

		JButton Boton_Eliminar_Canción = new JButton("Eliminar Canción");
		Boton_Eliminar_Canción
				.setToolTipText("Al presionar este Boton se eliminar la canción seleccionada en la tabla");
		GridBagConstraints gbc_Boton_Eliminar_Canción = new GridBagConstraints();
		gbc_Boton_Eliminar_Canción.fill = GridBagConstraints.HORIZONTAL;
		gbc_Boton_Eliminar_Canción.insets = new Insets(0, 0, 5, 5);
		gbc_Boton_Eliminar_Canción.gridx = 7;
		gbc_Boton_Eliminar_Canción.gridy = 7;
		Boton_Eliminar_Canción.addActionListener(ev -> {
			((DefaultTableModel) panelLista.getTable().getModel()).removeRow(panelLista.getTable().getSelectedRow());
		});
		this.add(Boton_Eliminar_Canción, gbc_Boton_Eliminar_Canción);

		this.setVisible(true);

	}

	private void fillLista() {
		DatosLista datos = AppMusic.getUnicaInstancia().getMisPlaylists();
		if (datos != null) {
			panelLista.setLista(datos.getNombres());
			this.revalidate();
			this.repaint();
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_LISTA_VACIA_MENSAJE);
		}
	}

	private void crearPlaylist() {
		String titulo = Texto_Título.getText();
		if (!titulo.isEmpty()) {
			añadirPlaylist(titulo);
			DatosLista datos = AppMusic.getUnicaInstancia().getMisPlaylists();
			if (datos != null) {
				panelLista.setLista(datos.getNombres());
				this.revalidate();
				this.repaint();
			} else {
				AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_LISTA_VACIA_MENSAJE);
			}
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_TITULO_VACIO_MENSAJE);
		}
	}

	private void actualizarPlaylist() {
		DatosTabla datos = new DatosTabla();
		JTable table = panelLista.getTable();
		for (int i = 0; i < panelLista.getTable().getRowCount(); i++) {
			datos.getTitulos().add((String) table.getValueAt(i, 0));
			datos.getInterpretes().add((String) table.getValueAt(i, 1));
			datos.getEstilos().add((String) table.getValueAt(i, 2));
			datos.getFavoritas().add((boolean) table.getValueAt(i, 3));
		}
		if (AppMusic.getUnicaInstancia().actualizarPlaylist(panelLista.getLista().getSelectedValue(), datos)) {
			AppMusic.getUnicaInstancia().showPopup(Constantes.EXITO_ACTUALIZAR_PLAYLIST_MENSAJE);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_ACTUALIZAR_PLAYLIST_MENSAJE);
		}
	}

	private void añadirPlaylist(String titulo) {
		if (AppMusic.getUnicaInstancia().añadirPlaylist(titulo)) {
			AppMusic.getUnicaInstancia().showPopup(Constantes.EXITO_CREAR_PLAYLIST_MENSAJE);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_CREAR_PLAYLIST_MENSAJE);
		}
	}

	private void eliminarPlaylist(String titulo) {
		if (AppMusic.getUnicaInstancia().eliminarPlaylist(titulo)) {
			DatosLista datos = AppMusic.getUnicaInstancia().getMisPlaylists();
			if (datos != null) {
				panelLista.setLista(datos.getNombres());
				panelLista.setTable(new DatosTabla());
				this.revalidate();
				this.repaint();
			} else {
				AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_LISTA_VACIA_MENSAJE);
			}
			AppMusic.getUnicaInstancia().showPopup(Constantes.EXITO_ELIMINAR_PLAYLIST_MENSAJE);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_ELIMINAR_PLAYLIST_MENSAJE);
		}
	}

	public JTable getTable() {
		return panelLista.getTable();
	}

	public void setTable(DatosTabla datos) {
		this.remove(scrollPane);
		panelLista.setTable(datos);
		scrollPane = new JScrollPane(panelLista.getTable());
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
		int id = panelLista.getTable().nextCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
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
		int id = panelLista.getTable().previousCancionId(AppMusic.getUnicaInstancia().getCancion(ruta).getId());
		if (id != -1) {
			ruta = AppMusic.getUnicaInstancia().buscarRutaCancion(id);
			Panel_Reproducción.playCancion(ruta);
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_PREVIOUS_MENSAJE);
		}
	}

	@Override
	public String updateRuta() {
		return panelLista.getTable().getRutaCancionSeleccionada();
	}
}
