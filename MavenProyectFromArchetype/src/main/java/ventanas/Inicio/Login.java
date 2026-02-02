package ventanas.Inicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import Controlador.AppMusic;
import Utilidades.Constantes;
import ventanas.HintTextField;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static Login unicaInstancia = null;
	private static final int X = 100;
	private static final int Y = 100;
	private static final int WIDTH = 450;
	private static final int HEIGHT = 300;

	// Singleton
	public static Login getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Login();
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
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/recursos/Singletune_16.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X, Y, WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Bottons_panel = new JPanel();
		contentPane.add(Bottons_panel, BorderLayout.SOUTH);

		JPanel Text_panel = new JPanel();
		Text_panel.setBorder(null);
		contentPane.add(Text_panel, BorderLayout.WEST);
		GridBagLayout gbl_Text_panel = new GridBagLayout();
		gbl_Text_panel.columnWidths = new int[] { 50, 80, 15, 200, 50, 0 };
		gbl_Text_panel.rowHeights = new int[] { 0, 10, 35, 15, 35, 10, 0, 0 };
		gbl_Text_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_Text_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		Text_panel.setLayout(gbl_Text_panel);

		JLabel Usuario = new JLabel("Usuario:");
		Usuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		GridBagConstraints gbc_Usuario = new GridBagConstraints();
		gbc_Usuario.anchor = GridBagConstraints.EAST;
		gbc_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Usuario.gridx = 1;
		gbc_Usuario.gridy = 2;
		Text_panel.add(Usuario, gbc_Usuario);

		JTextField Texto_Usuario = new HintTextField("Usuario");
		Texto_Usuario.setColumns(4);
		GridBagConstraints gbc_Texto_Usuario = new GridBagConstraints();
		gbc_Texto_Usuario.fill = GridBagConstraints.BOTH;
		gbc_Texto_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Usuario.gridx = 3;
		gbc_Texto_Usuario.gridy = 2;
		Text_panel.add(Texto_Usuario, gbc_Texto_Usuario);

		JLabel Contraseña = new JLabel("Contraseña:");
		Contraseña.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		GridBagConstraints gbc_Contraseña = new GridBagConstraints();
		gbc_Contraseña.anchor = GridBagConstraints.EAST;
		gbc_Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_Contraseña.gridx = 1;
		gbc_Contraseña.gridy = 4;
		Text_panel.add(Contraseña, gbc_Contraseña);

		JTextField Texto_Contraseña = new HintTextField("Contraseña");
		Texto_Contraseña.setSelectedTextColor(Color.WHITE);
		Texto_Contraseña.setColumns(10);
		GridBagConstraints gbc_Texto_Contraseña = new GridBagConstraints();
		gbc_Texto_Contraseña.fill = GridBagConstraints.BOTH;
		gbc_Texto_Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Contraseña.gridx = 3;
		gbc_Texto_Contraseña.gridy = 4;
		Text_panel.add(Texto_Contraseña, gbc_Texto_Contraseña);

		JPanel Title_panel = new JPanel();
		Title_panel.setBorder(null);
		contentPane.add(Title_panel, BorderLayout.NORTH);
		GridBagLayout gbl_Title_panel = new GridBagLayout();
		gbl_Title_panel.columnWidths = new int[] { 0, 175, 0, 0 };
		gbl_Title_panel.rowHeights = new int[] { 51, 0, 0 };
		gbl_Title_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_Title_panel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		Title_panel.setLayout(gbl_Title_panel);

		JPanel Space_panel_1 = new JPanel();
		GridBagConstraints gbc_Space_panel_1 = new GridBagConstraints();
		gbc_Space_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_Space_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_Space_panel_1.gridx = 0;
		gbc_Space_panel_1.gridy = 0;
		Title_panel.add(Space_panel_1, gbc_Space_panel_1);
		Space_panel_1.setLayout(new BorderLayout(0, 0));

		JLabel Decorado_Izquierdo = new JLabel("");
		Decorado_Izquierdo.setHorizontalAlignment(SwingConstants.CENTER);
		Decorado_Izquierdo.setIcon(new ImageIcon(Login.class.getResource("/recursos/floral_I_64.png")));
		Space_panel_1.add(Decorado_Izquierdo, BorderLayout.NORTH);

		JPanel Logo_panel = new JPanel();
		GridBagConstraints gbc_Logo_panel = new GridBagConstraints();
		gbc_Logo_panel.insets = new Insets(0, 0, 5, 5);
		gbc_Logo_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_Logo_panel.gridx = 1;
		gbc_Logo_panel.gridy = 0;
		Title_panel.add(Logo_panel, gbc_Logo_panel);

		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(Login.class.getResource("/recursos/Singletune_128.png")));
		Logo.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 40));
		Logo_panel.add(Logo);

		JPanel Space_panel_2 = new JPanel();
		GridBagConstraints gbc_Space_panel_2 = new GridBagConstraints();
		gbc_Space_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_Space_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_Space_panel_2.gridx = 2;
		gbc_Space_panel_2.gridy = 0;
		Title_panel.add(Space_panel_2, gbc_Space_panel_2);
		Space_panel_2.setLayout(new BorderLayout(0, 0));

		JLabel Decorado_Derecho = new JLabel("");
		Decorado_Derecho.setHorizontalAlignment(SwingConstants.CENTER);
		Decorado_Derecho.setIcon(new ImageIcon(Login.class.getResource("/recursos/floral_D_64.png")));
		Space_panel_2.add(Decorado_Derecho, BorderLayout.NORTH);

		JButton Botón_Volver = new JButton("Volver");
		Botón_Volver.setIcon(new ImageIcon(Login.class.getResource("/recursos/flecha-hacia-atras.png")));
		Botón_Volver.addActionListener(ev -> {
			AppMusic.getUnicaInstancia().mostrarVentanaSelector(this);
		});
		Bottons_panel.add(Botón_Volver);

		JButton Botón_Login = new JButton("Login");
		Botón_Login.setIcon(new ImageIcon(Login.class.getResource("/recursos/usuario.png")));
		Botón_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Botón_Login.addActionListener(ev -> {
			login(Texto_Usuario, Texto_Contraseña);
		});
		Bottons_panel.add(Botón_Login);

	}

	private void login(JTextField Texto_Usuario, JTextField Texto_Contraseña) {
		if (AppMusic.getUnicaInstancia().verficarUsuario(Texto_Usuario.getText(), Texto_Contraseña.getText())) {
			AppMusic.getUnicaInstancia().mostrarVentanaPrincipal();
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_INICIO_SESION_MENSAJE);
		}
	}

}
