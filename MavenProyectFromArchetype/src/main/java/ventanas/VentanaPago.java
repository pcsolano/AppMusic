package ventanas;

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

import Controlador.AppMusic;
import ventanas.services.PaymentNotificationService;

import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;

public class VentanaPago extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static VentanaPago unicaInstancia = null;
	private static final int X = 100;
	private static final int Y = 100;
	private static final int WIDTH = 550;
	private static final int HEIGHT = 275;
	private JPanel contentPane;
	private JPanel panel;
	private JButton Boton_Pago;
	private JPanel panel2;
	private JButton Boton_Volver;
	private JLabel Tarjeta;
	private JLabel Caducidad;
	private JLabel Precio;
	private JLabel CVC;
	private HintTextField Texto_CVC;
	private HintTextField Texto_Caducidad;
	private HintTextField Texto_Tarjeta;
	private JLabel Descuento;
	private JLabel Total;
	private PaymentNotificationService notificationService = new PaymentNotificationService(Principal.getInstancia());
	private JLabel Precio2;
	private JLabel Descuento2;
	private JLabel Total2;

	// Singleton
	public static VentanaPago getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new VentanaPago();
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
					VentanaPago frame = new VentanaPago();
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
	public VentanaPago() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaPago.class.getResource("/recursos/Singletune_16.png")));
		setTitle("Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		Boton_Volver = new JButton("Volver");
		Boton_Volver.setIcon(new ImageIcon(VentanaPago.class.getResource("/recursos/flecha-hacia-atras.png")));
		Boton_Volver.addActionListener(ev -> {
			removeInstancia();
		});
		panel.add(Boton_Volver);

		panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.CENTER);
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[] { 15, 80, 50, 100, 30, 15, 0 };
		gbl_panel2.rowHeights = new int[] { 15, 15, 35, 35, 35, 35, 35, 15, 15, 0 };
		gbl_panel2.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel2.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel2.setLayout(gbl_panel2);

		Tarjeta = new JLabel("Tarjeta:");
		Tarjeta.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Tarjeta = new GridBagConstraints();
		gbc_Tarjeta.insets = new Insets(0, 0, 5, 5);
		gbc_Tarjeta.anchor = GridBagConstraints.EAST;
		gbc_Tarjeta.gridx = 1;
		gbc_Tarjeta.gridy = 2;
		panel2.add(Tarjeta, gbc_Tarjeta);

		Texto_Tarjeta = new HintTextField("Número Tarjeta");
		GridBagConstraints gbc_Texto_Tarjeta = new GridBagConstraints();
		gbc_Texto_Tarjeta.gridwidth = 3;
		gbc_Texto_Tarjeta.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Tarjeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Tarjeta.gridx = 2;
		gbc_Texto_Tarjeta.gridy = 2;
		panel2.add(Texto_Tarjeta, gbc_Texto_Tarjeta);
		Texto_Tarjeta.setColumns(10);

		Caducidad = new JLabel("Caducidad:");
		Caducidad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Caducidad = new GridBagConstraints();
		gbc_Caducidad.insets = new Insets(0, 0, 5, 5);
		gbc_Caducidad.anchor = GridBagConstraints.EAST;
		gbc_Caducidad.gridx = 1;
		gbc_Caducidad.gridy = 4;
		panel2.add(Caducidad, gbc_Caducidad);

		Texto_Caducidad = new HintTextField("Fecha Caducidad");
		Texto_Caducidad.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_Texto_Caducidad = new GridBagConstraints();
		gbc_Texto_Caducidad.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_Caducidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_Caducidad.gridx = 2;
		gbc_Texto_Caducidad.gridy = 4;
		panel2.add(Texto_Caducidad, gbc_Texto_Caducidad);
		Texto_Caducidad.setColumns(10);

		Precio = new JLabel("Precio:");
		Precio.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Precio = new GridBagConstraints();
		gbc_Precio.insets = new Insets(0, 0, 5, 5);
		gbc_Precio.anchor = GridBagConstraints.EAST;
		gbc_Precio.gridx = 3;
		gbc_Precio.gridy = 4;
		panel2.add(Precio, gbc_Precio);

		Precio2 = new JLabel(Integer.toString(Utilidades.Constantes.PRECIO_ESTANDAR));
		GridBagConstraints gbc_Precio2 = new GridBagConstraints();
		gbc_Precio2.insets = new Insets(0, 0, 5, 5);
		gbc_Precio2.gridx = 4;
		gbc_Precio2.gridy = 4;
		panel2.add(Precio2, gbc_Precio2);

		Descuento = new JLabel("Descuento:");
		Descuento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Descuento = new GridBagConstraints();
		gbc_Descuento.anchor = GridBagConstraints.EAST;
		gbc_Descuento.insets = new Insets(0, 0, 5, 5);
		gbc_Descuento.gridx = 3;
		gbc_Descuento.gridy = 5;
		panel2.add(Descuento, gbc_Descuento);

		DecimalFormat numberFormat = mostrarReduccionDescuento();

		CVC = new JLabel("CVC:");
		CVC.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_CVC = new GridBagConstraints();
		gbc_CVC.anchor = GridBagConstraints.EAST;
		gbc_CVC.insets = new Insets(0, 0, 5, 5);
		gbc_CVC.gridx = 1;
		gbc_CVC.gridy = 6;
		panel2.add(CVC, gbc_CVC);

		Texto_CVC = new HintTextField("CVC");
		GridBagConstraints gbc_Texto_CVC = new GridBagConstraints();
		gbc_Texto_CVC.insets = new Insets(0, 0, 5, 5);
		gbc_Texto_CVC.fill = GridBagConstraints.HORIZONTAL;
		gbc_Texto_CVC.gridx = 2;
		gbc_Texto_CVC.gridy = 6;
		panel2.add(Texto_CVC, gbc_Texto_CVC);

		Total = new JLabel("Total:");
		Total.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		GridBagConstraints gbc_Total = new GridBagConstraints();
		gbc_Total.anchor = GridBagConstraints.EAST;
		gbc_Total.insets = new Insets(0, 0, 5, 5);
		gbc_Total.gridx = 3;
		gbc_Total.gridy = 6;
		panel2.add(Total, gbc_Total);

		double total = calcularTotal();

		Total2 = new JLabel(numberFormat.format(total));
		GridBagConstraints gbc_Total2 = new GridBagConstraints();
		gbc_Total2.insets = new Insets(0, 0, 5, 5);
		gbc_Total2.gridx = 4;
		gbc_Total2.gridy = 6;
		panel2.add(Total2, gbc_Total2);

		Boton_Pago = new JButton("Realizar Pago");
		Boton_Pago.setIcon(new ImageIcon(VentanaPago.class.getResource("/recursos/signo-de-dolar.png")));
		Boton_Pago.addActionListener(ev -> {
			realizarPago();
		});
		panel.add(Boton_Pago);
	}

	private void realizarPago() {
		if (comprobacionesPago()) {
			notificationService.notifyObserver();
			AppMusic.getUnicaInstancia().setVentanaActual(Principal.getInstancia());
			removeInstancia();
		} else {
			AppMusic.getUnicaInstancia().showPopup(Utilidades.Constantes.ERROR_PAGO);
		}
	}

	private double calcularTotal() {
		double total = Utilidades.Constantes.PRECIO_ESTANDAR;
		if (AppMusic.getUnicaInstancia().getUsuarioActivo().getDesc() != null) {
			total = AppMusic.getUnicaInstancia().getUsuarioActivo().getDesc().calcDescuento(total);
		}
		return total;
	}

	private DecimalFormat mostrarReduccionDescuento() {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		String stringDescuento;
		if (AppMusic.getUnicaInstancia().getUsuarioActivo().getDesc().getTipoName()
				.equals(Utilidades.Constantes.DESCUENTOS[0])) {
			stringDescuento = "x 1";
		} else {
			double descuento = AppMusic.getUnicaInstancia().getUsuarioActivo().getDesc().getPorcentaje();
			stringDescuento = "x 0" + numberFormat.format(descuento);
		}

		Descuento2 = new JLabel(stringDescuento);
		GridBagConstraints gbc_Descuento2 = new GridBagConstraints();
		gbc_Descuento2.insets = new Insets(0, 0, 5, 5);
		gbc_Descuento2.gridx = 4;
		gbc_Descuento2.gridy = 5;
		panel2.add(Descuento2, gbc_Descuento2);
		return numberFormat;
	}

	private boolean comprobacionesPago() {
		if (Texto_CVC.getText().matches("[0-9]{3}")) {
			if (Texto_Caducidad.getText().matches("([0-9]{2})/([0-9]{2})")) {
				if (Texto_Tarjeta.getText().matches("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}")) {
					return true;
				}
			}
		}
		return false;
	}

}
