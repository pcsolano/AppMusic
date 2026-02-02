package ventanas.Inicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;

import Controlador.AppMusic;
import Utilidades.Constantes;
import ventanas.HintTextField;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static Registro unicaInstancia = null;
	private static final int X = 100;
	private static final int Y = 100;
	private static final int WIDTH = 550;
	private static final int HEIGHT = 275;
	private JDateChooser Seleccionador_Fecha;
	private HintTextField Texto_Email;
	private HintTextField Texto_Nombre;
	private HintTextField Texto_Contraseña;
	private HintTextField Texto_Usuario;
	private JComboBox<String> Grupo_elegido;

	// Singleton
	public static Registro getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Registro();
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
					Registro frame = new Registro();
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
	public Registro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/recursos/Singletune_16.png")));
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X, Y, WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton Botón_Registro = new JButton("Registro");
		Botón_Registro.setIcon(new ImageIcon(Registro.class.getResource("/recursos/anadir.png")));
		Botón_Registro.addActionListener(ev -> {
			register();
		});
		panel.add(Botón_Registro);

		JButton Botón_Volver = new JButton("Volver");
		Botón_Volver.setIcon(new ImageIcon(Registro.class.getResource("/recursos/flecha-hacia-atras.png")));
		Botón_Volver.addActionListener(ev -> {
			AppMusic.getUnicaInstancia().mostrarVentanaSelector(this);
		});
		panel.add(Botón_Volver);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 15, 100, 100, 100, 100, 15, 0 };
		gbl_panel_1.rowHeights = new int[] { 15, 15, 35, 0, 15, 35, 15, 35, 15, 35, 15, 15, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel Usuario = new JLabel("Usuario:");
		Usuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Usuario = new GridBagConstraints();
		gbc_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Usuario.anchor = GridBagConstraints.EAST;
		gbc_Usuario.gridx = 1;
		gbc_Usuario.gridy = 2;
		panel_1.add(Usuario, gbc_Usuario);

		Texto_Usuario = new HintTextField("Usuario");
		GridBagConstraints gbc_Texto_Usuario = new GridBagConstraints();
		gbc_Texto_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Usuario.gridx = 2;
		gbc_Texto_Usuario.gridy = 2;
		panel_1.add(Texto_Usuario, gbc_Texto_Usuario);
		Texto_Usuario.setColumns(10);

		JLabel Contraseña = new JLabel("Contraseña:");
		Contraseña.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Contraseña = new GridBagConstraints();
		gbc_Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_Contraseña.anchor = GridBagConstraints.EAST;
		gbc_Contraseña.gridx = 3;
		gbc_Contraseña.gridy = 2;
		panel_1.add(Contraseña, gbc_Contraseña);

		Texto_Contraseña = new HintTextField("Contraseña");
		GridBagConstraints gbc_Texto_Contraseña = new GridBagConstraints();
		gbc_Texto_Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Contraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Contraseña.gridx = 4;
		gbc_Texto_Contraseña.gridy = 2;
		panel_1.add(Texto_Contraseña, gbc_Texto_Contraseña);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Nombre = new GridBagConstraints();
		gbc_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_Nombre.anchor = GridBagConstraints.EAST;
		gbc_Nombre.gridx = 1;
		gbc_Nombre.gridy = 5;
		panel_1.add(Nombre, gbc_Nombre);

		Texto_Nombre = new HintTextField("Nombre completo");
		Texto_Nombre.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_Texto_Nombre = new GridBagConstraints();
		gbc_Texto_Nombre.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Nombre.gridwidth = 3;
		gbc_Texto_Nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Nombre.gridx = 2;
		gbc_Texto_Nombre.gridy = 5;
		panel_1.add(Texto_Nombre, gbc_Texto_Nombre);
		Texto_Nombre.setColumns(10);

		JLabel Email = new JLabel("Email:");
		Email.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.anchor = GridBagConstraints.EAST;
		gbc_Email.insets = new Insets(0, 0, 5, 5);
		gbc_Email.gridx = 1;
		gbc_Email.gridy = 7;
		panel_1.add(Email, gbc_Email);

		Texto_Email = new HintTextField("Email");
		GridBagConstraints gbc_Texto_Email = new GridBagConstraints();
		gbc_Texto_Email.gridwidth = 3;
		gbc_Texto_Email.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Email.gridx = 2;
		gbc_Texto_Email.gridy = 7;
		panel_1.add(Texto_Email, gbc_Texto_Email);

		JLabel Fecha_Nacimiento = new JLabel("Fecha Nacimiento:");
		Fecha_Nacimiento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Fecha_Nacimiento = new GridBagConstraints();
		gbc_Fecha_Nacimiento.anchor = GridBagConstraints.EAST;
		gbc_Fecha_Nacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_Fecha_Nacimiento.gridx = 1;
		gbc_Fecha_Nacimiento.gridy = 9;
		panel_1.add(Fecha_Nacimiento, gbc_Fecha_Nacimiento);

		Seleccionador_Fecha = new JDateChooser();
		GridBagConstraints gbc_Seleccionador_Fecha = new GridBagConstraints();
		gbc_Seleccionador_Fecha.insets = new Insets(0, 0, 5, 5);
		gbc_Seleccionador_Fecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_Seleccionador_Fecha.gridx = 2;
		gbc_Seleccionador_Fecha.gridy = 9;
		panel_1.add(Seleccionador_Fecha, gbc_Seleccionador_Fecha);

		JLabel Grupo = new JLabel("Grupo:");
		Grupo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Grupo = new GridBagConstraints();
		gbc_Grupo.anchor = GridBagConstraints.EAST;
		gbc_Grupo.insets = new Insets(0, 0, 5, 5);
		gbc_Grupo.gridx = 3;
		gbc_Grupo.gridy = 9;
		panel_1.add(Grupo, gbc_Grupo);

		Grupo_elegido = new JComboBox<String>();
		GridBagConstraints gbc_Grupo_elegido = new GridBagConstraints();
		gbc_Grupo_elegido.insets = new Insets(0, 0, 5, 5);
		gbc_Grupo_elegido.fill = GridBagConstraints.HORIZONTAL;
		gbc_Grupo_elegido.gridx = 4;
		gbc_Grupo_elegido.gridy = 9;
		for (String s : Utilidades.Constantes.DESCUENTOS) {
			Grupo_elegido.addItem(s);
		}
		panel_1.add(Grupo_elegido, gbc_Grupo_elegido);
	}

	private void register() {
		int resultado = AppMusic.getUnicaInstancia().registrarUsuario(Texto_Usuario.getText(), Texto_Email.getText(),
				Texto_Contraseña.getText(), Seleccionador_Fecha.getDate(), Texto_Nombre.getText(),
				(String) Grupo_elegido.getSelectedItem());

		switch (resultado) {
		case Constantes.ERROR_REGISTRO_CAMPOS:
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_REGISTRO_CAMPOS_MENSAJE);
			break;
		case Constantes.ERROR_REGISTRO_CORREO:
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_REGISTRO_CORREO_MENSAJE);
			break;
		case Constantes.ERROR_REGISTRO_FECHA:
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_REGISTRO_FECHA_MENSAJE);
			break;
		case Constantes.ERROR_REGISTRO_DESCUENTO:
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_REGISTRO_GRUPO_MENSAJE);
			break;
		case Constantes.OKAY:
			Login.getInstancia().setVisible(true);
			AppMusic.getUnicaInstancia().setVentanaActual(Login.getInstancia());
			removeInstancia();
		}
	}

}
