package ventanas.Inicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import Controlador.AppMusic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Component;
import java.awt.Toolkit;

public class Selector extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static Selector unicaInstancia = null;

	// Singleton
	public static Selector getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Selector();
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
					Selector frame = new Selector();
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
	public Selector() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selector.class.getResource("/recursos/Singletune_16.png")));
		setTitle("Single Tune");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel Title_panel = new JPanel();
		Title_panel.setBorder(null);
		contentPane.add(Title_panel, BorderLayout.NORTH);
		GridBagLayout gbl_Title_panel = new GridBagLayout();
		gbl_Title_panel.columnWidths = new int[] { 0, 175, 0, 0 };
		gbl_Title_panel.rowHeights = new int[] { 51, 0, 0 };
		gbl_Title_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_Title_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		Title_panel.setLayout(gbl_Title_panel);

		JPanel Space_panel_1 = new JPanel();
		GridBagConstraints gbc_Space_panel_1 = new GridBagConstraints();
		gbc_Space_panel_1.fill = GridBagConstraints.BOTH;
		gbc_Space_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_Space_panel_1.gridx = 0;
		gbc_Space_panel_1.gridy = 0;
		Title_panel.add(Space_panel_1, gbc_Space_panel_1);
		Space_panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(Selector.class.getResource("/recursos/floral_I_64.png")));
		Space_panel_1.add(lblNewLabel_2);

		JPanel Name_panel = new JPanel();
		GridBagConstraints gbc_Name_panel = new GridBagConstraints();
		gbc_Name_panel.fill = GridBagConstraints.BOTH;
		gbc_Name_panel.insets = new Insets(0, 0, 5, 5);
		gbc_Name_panel.gridx = 1;
		gbc_Name_panel.gridy = 0;
		Title_panel.add(Name_panel, gbc_Name_panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Selector.class.getResource("/recursos/Singletune_128.png")));
		lblNewLabel.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 40));
		Name_panel.add(lblNewLabel);

		JPanel Space_panel_2 = new JPanel();
		GridBagConstraints gbc_Space_panel_2 = new GridBagConstraints();
		gbc_Space_panel_2.fill = GridBagConstraints.BOTH;
		gbc_Space_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_Space_panel_2.gridx = 2;
		gbc_Space_panel_2.gridy = 0;
		Title_panel.add(Space_panel_2, gbc_Space_panel_2);
		Space_panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Selector.class.getResource("/recursos/floral_D_64.png")));
		Space_panel_2.add(lblNewLabel_3);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 15, 0, 0, 15, 0 };
		gbl_panel.rowHeights = new int[] { 5, 35, 35, 15, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido de nuevo, usuario");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setIcon(new ImageIcon(Selector.class.getResource("/recursos/usuario.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		btnNewButton.addActionListener(ev -> {
			setVisible(false);
			Login.getInstancia().setVisible(true);
			AppMusic.getUnicaInstancia().setVentanaActual(Login.getInstancia());
		});
		panel.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Login con GitHub");
		btnNewButton_1.setIcon(new ImageIcon(Selector.class.getResource("/recursos/github.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		btnNewButton_1.addActionListener(ev -> {
			setVisible(false);
			LoginGit.getInstancia().setVisible(true);
			AppMusic.getUnicaInstancia().setVentanaActual(LoginGit.getInstancia());
		});
		panel.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 262, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 20, 0, 33, 10, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 20.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Si no tiene una cuenta todavia, registrese");
		lblNewLabel_1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 1;
		panel_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

		JButton btnNewButton_2 = new JButton("Registro");
		btnNewButton_2.setIcon(new ImageIcon(Selector.class.getResource("/recursos/editar.png")));
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(ev -> {
			setVisible(false);
			Registro.getInstancia().setVisible(true);
			AppMusic.getUnicaInstancia().setVentanaActual(Registro.getInstancia());
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
	}

}
