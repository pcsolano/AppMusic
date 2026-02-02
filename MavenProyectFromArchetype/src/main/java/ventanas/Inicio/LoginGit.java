package ventanas.Inicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import Controlador.AppMusic;
import Utilidades.Constantes;
import pulsador.Luz;
import ventanas.HintTextField;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class LoginGit extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static LoginGit unicaInstancia = null;
	private static final int X = 100;
	private static final int Y = 100;
	private static final int WIDTH = 450;
	private static final int HEIGHT = 300;

	// Singleton
	public static LoginGit getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new LoginGit();
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
					LoginGit frame = new LoginGit();
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
	public LoginGit() {
		setTitle("Login Git");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGit.class.getResource("/recursos/Singletune_16.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X, Y, WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Bottons_panel = new JPanel();
		contentPane.add(Bottons_panel, BorderLayout.SOUTH);

		JButton Botón_Volver = new JButton("Volver");
		Botón_Volver.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/flecha-hacia-atras.png")));
		Botón_Volver.addActionListener(ev -> {
			AppMusic.getUnicaInstancia().mostrarVentanaSelector(this);
		});
		Bottons_panel.add(Botón_Volver);

		JPanel Text_panel = new JPanel();
		Text_panel.setBorder(null);
		contentPane.add(Text_panel, BorderLayout.WEST);
		GridBagLayout gbl_Text_panel = new GridBagLayout();
		gbl_Text_panel.columnWidths = new int[] { 50, 80, 15, 200, 5, 0, 50, 0 };
		gbl_Text_panel.rowHeights = new int[] { 0, 10, 35, 10, 35, 10, 35, 10, 0, 0 };
		gbl_Text_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_Text_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		Text_panel.setLayout(gbl_Text_panel);

		JLabel Usuario_Git = new JLabel("Usuario Git:");
		Usuario_Git.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		GridBagConstraints gbc_Usuario_Git = new GridBagConstraints();
		gbc_Usuario_Git.anchor = GridBagConstraints.EAST;
		gbc_Usuario_Git.insets = new Insets(0, 0, 5, 5);
		gbc_Usuario_Git.gridx = 1;
		gbc_Usuario_Git.gridy = 2;
		Text_panel.add(Usuario_Git, gbc_Usuario_Git);

		JTextField Texto_Usuario = new HintTextField("Usuario");
		Texto_Usuario.setColumns(4);
		GridBagConstraints gbc_Texto_Usuario = new GridBagConstraints();
		gbc_Texto_Usuario.gridwidth = 3;
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
		gbc_Texto_Contraseña.gridwidth = 3;
		gbc_Texto_Contraseña.fill = GridBagConstraints.BOTH;
		gbc_Texto_Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Contraseña.gridx = 3;
		gbc_Texto_Contraseña.gridy = 4;
		Text_panel.add(Texto_Contraseña, gbc_Texto_Contraseña);

		JLabel Certificado = new JLabel("Certificado:");
		Certificado.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		GridBagConstraints gbc_Certificado = new GridBagConstraints();
		gbc_Certificado.insets = new Insets(0, 0, 5, 5);
		gbc_Certificado.gridx = 1;
		gbc_Certificado.gridy = 6;
		Text_panel.add(Certificado, gbc_Certificado);

		HintTextField Texto_Certificado = new HintTextField("Ruta Certificado");
		Texto_Certificado.setSelectedTextColor(Color.WHITE);
		Texto_Certificado.setColumns(10);
		GridBagConstraints gbc_Texto_Certificado = new GridBagConstraints();
		gbc_Texto_Certificado.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Certificado.fill = GridBagConstraints.BOTH;
		gbc_Texto_Certificado.gridx = 3;
		gbc_Texto_Certificado.gridy = 6;
		Text_panel.add(Texto_Certificado, gbc_Texto_Certificado);

		Luz luz = new Luz();
		luz.setEncendido(true);
		luz.setColor(new Color(0, 0, 255));
		luz.addEncendidoListener(ev -> {
			luz.setEncendido(true);
			extraerRuta(Texto_Certificado);
		});
		GridBagConstraints gbc_Luz = new GridBagConstraints();
		gbc_Luz.insets = new Insets(0, 0, 5, 5);
		gbc_Luz.fill = GridBagConstraints.HORIZONTAL;
		gbc_Luz.gridx = 5;
		gbc_Luz.gridy = 6;
		Text_panel.add(luz, gbc_Luz);

		JPanel Title_panel = new JPanel();
		Title_panel.setBorder(null);
		contentPane.add(Title_panel, BorderLayout.NORTH);
		GridBagLayout gbl_Title_panel = new GridBagLayout();
		gbl_Title_panel.columnWidths = new int[] { 0, 175, 0, 0 };
		gbl_Title_panel.rowHeights = new int[] { 10, 51, 0, 0 };
		gbl_Title_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_Title_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		Title_panel.setLayout(gbl_Title_panel);

		JPanel Space_panel_1 = new JPanel();
		GridBagConstraints gbc_Space_panel_1 = new GridBagConstraints();
		gbc_Space_panel_1.anchor = GridBagConstraints.EAST;
		gbc_Space_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_Space_panel_1.gridx = 0;
		gbc_Space_panel_1.gridy = 1;
		Title_panel.add(Space_panel_1, gbc_Space_panel_1);

		JLabel Decorado_Izquierdo = new JLabel("");
		Decorado_Izquierdo.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/floral_I_32.png")));
		Space_panel_1.add(Decorado_Izquierdo);

		JPanel Name_panel = new JPanel();
		GridBagConstraints gbc_Name_panel = new GridBagConstraints();
		gbc_Name_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_Name_panel.insets = new Insets(0, 0, 5, 5);
		gbc_Name_panel.gridx = 1;
		gbc_Name_panel.gridy = 1;
		Title_panel.add(Name_panel, gbc_Name_panel);
		GridBagLayout gbl_Name_panel = new GridBagLayout();
		gbl_Name_panel.columnWidths = new int[] { 1, 64, 10, 32, 10, 64, 1, 0 };
		gbl_Name_panel.rowHeights = new int[] { 64, 0 };
		gbl_Name_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Name_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		Name_panel.setLayout(gbl_Name_panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 40));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		Name_panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel Logo_Singletune = new JLabel("");
		Logo_Singletune.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/Singletune_64.png")));
		GridBagConstraints gbc_Logo_Singletune = new GridBagConstraints();
		gbc_Logo_Singletune.anchor = GridBagConstraints.NORTHWEST;
		gbc_Logo_Singletune.insets = new Insets(0, 0, 0, 5);
		gbc_Logo_Singletune.gridx = 1;
		gbc_Logo_Singletune.gridy = 0;
		Name_panel.add(Logo_Singletune, gbc_Logo_Singletune);

		JLabel Cadena = new JLabel("");
		Cadena.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/enlace.png")));
		GridBagConstraints gbc_Cadena = new GridBagConstraints();
		gbc_Cadena.anchor = GridBagConstraints.WEST;
		gbc_Cadena.insets = new Insets(0, 0, 0, 5);
		gbc_Cadena.gridx = 3;
		gbc_Cadena.gridy = 0;
		Name_panel.add(Cadena, gbc_Cadena);

		JLabel Logo_Github = new JLabel("");
		GridBagConstraints gbc_Logo_Github = new GridBagConstraints();
		gbc_Logo_Github.anchor = GridBagConstraints.NORTHWEST;
		gbc_Logo_Github.insets = new Insets(0, 0, 0, 5);
		gbc_Logo_Github.gridx = 5;
		gbc_Logo_Github.gridy = 0;
		Name_panel.add(Logo_Github, gbc_Logo_Github);
		Logo_Github.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/github_color_2.png")));

		JLabel lblNewLabel_4 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.gridx = 6;
		gbc_lblNewLabel_4.gridy = 0;
		Name_panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JPanel Space_panel_2 = new JPanel();
		GridBagConstraints gbc_Space_panel_2 = new GridBagConstraints();
		gbc_Space_panel_2.anchor = GridBagConstraints.WEST;
		gbc_Space_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_Space_panel_2.gridx = 2;
		gbc_Space_panel_2.gridy = 1;
		Title_panel.add(Space_panel_2, gbc_Space_panel_2);

		JLabel Decorado_Derecho = new JLabel("");
		Decorado_Derecho.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/floral_D_32.png")));
		Space_panel_2.add(Decorado_Derecho);

		JButton Botón_Login_Git = new JButton("Login con GitHub");
		Botón_Login_Git.setIcon(new ImageIcon(LoginGit.class.getResource("/recursos/github.png")));
		Botón_Login_Git.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Botón_Login_Git.addActionListener(ev -> {
			login(Texto_Usuario, Texto_Contraseña, Texto_Certificado);
		});
		Bottons_panel.add(Botón_Login_Git);
	}

	private void login(JTextField Texto_Usuario, JTextField Texto_Contraseña, JTextField Texto_Certificado) {
		if (AppMusic.getUnicaInstancia().verficarUsuarioGit(Texto_Usuario.getText(), Texto_Contraseña.getText(),
				Texto_Certificado.getText())) {
			AppMusic.getUnicaInstancia().mostrarVentanaPrincipal();
		} else {
			AppMusic.getUnicaInstancia().showPopup(Constantes.ERROR_INICIO_SESION_MENSAJE);
		}
	}

	private void extraerRuta(JTextField Texto_Certificado) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Github Properties", "properties");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(AppMusic.getUnicaInstancia().getVentanaActual());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String ruta = chooser.getSelectedFile().getPath();
			Texto_Certificado.setText(ruta);
		}
	}

}
