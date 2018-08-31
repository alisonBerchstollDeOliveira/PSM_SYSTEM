package py.edu.facitec.psmsystem.app;

import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import py.edu.facitec.psmsystem.abm.VentanaCliente;
import py.edu.facitec.psmsystem.abm.VentanaConfiguracion;
import py.edu.facitec.psmsystem.abm.VentanaProducto;
import py.edu.facitec.psmsystem.componente.BotonIconoPrincipal;
import py.edu.facitec.psmsystem.componente.PanelFondo;
import py.edu.facitec.psmsystem.controlador.VentanaClienteControlador;
import py.edu.facitec.psmsystem.controlador.VentanaEmpenoControlador;
import py.edu.facitec.psmsystem.controlador.VentanaProductoControlador;
import py.edu.facitec.psmsystem.dao.ConfiguracionDao;
import py.edu.facitec.psmsystem.entidad.Configuracion;
import py.edu.facitec.psmsystem.transaccion.VentanaEmpeno;

public class VentanaPrincipal extends JFrame implements KeyEventDispatcher{

	private PanelFondo contentPane;
	public static JLabel lblNombre;
	public static JLabel lblDireccion;
	public static JLabel lblTelefono;
	public static JLabel lblEmail;
	private List<Configuracion> configuracion;
	private ConfiguracionDao configuracionDao;
	private JPanel jPanelConfig;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(GraphiteLookAndFeel.class.getName());
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icono.png")));
		DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this); 

		setTitle("PSMSystem v1.4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		setLocationRelativeTo(this);
		setExtendedState(MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProceso = new JMenu("Proceso");
		menuBar.add(mnProceso);

		JMenuItem mntmEmpeño = new JMenuItem("Empe\u00F1o");
		mntmEmpeño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioEmpeno();
			}
		});
		mnProceso.add(mntmEmpeño);

		JMenuItem mntmCobranza = new JMenuItem("Cobranza");
		mntmCobranza.setEnabled(false);
		mnProceso.add(mntmCobranza);

		JMenu mnTablas = new JMenu("Tablas");
		menuBar.add(mnTablas);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setEnabled(true);
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioCliente();
			}
		});
		mnTablas.add(mntmCliente);

		JMenuItem mntmProducto = new JMenuItem("Producto");
		mntmProducto.setEnabled(true);
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioProducto();
			}
		});
		mnTablas.add(mntmProducto);

		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);

		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mntmListadoDeClientes.setEnabled(false);
		mnInformes.add(mntmListadoDeClientes);

		JMenuItem mntmListadoDeProductos = new JMenuItem("Listado de Productos");
		mntmListadoDeProductos.setEnabled(false);
		mnInformes.add(mntmListadoDeProductos);

		JMenuItem mntmInformeDeEmpeos = new JMenuItem("Informe de Empe\u00F1os");
		mntmInformeDeEmpeos.setEnabled(false);
		mnInformes.add(mntmInformeDeEmpeos);

		JMenuItem mntmInformeDeCobranzas = new JMenuItem("Informe de Cobranzas");
		mntmInformeDeCobranzas.setEnabled(false);
		mnInformes.add(mntmInformeDeCobranzas);

		JMenuItem mntmInformeDeDeudas = new JMenuItem("Informe de Deudas");
		mntmInformeDeDeudas.setEnabled(false);
		mnInformes.add(mntmInformeDeDeudas);

		JMenu mnUtilidades = new JMenu("Utilidades");
		menuBar.add(mnUtilidades);

		JMenuItem mntmInicializacinDeDatos = new JMenuItem("Inicializaci\u00F3n de Datos");
		mntmInicializacinDeDatos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Desea restablecer datos?\nSerán eliminado permanentemente todos los datos almacenados", "Atención!!", JOptionPane.YES_NO_OPTION);
				if (respuesta==JOptionPane.YES_OPTION) {
					inicializarBaseDeDatos();
					JOptionPane.showMessageDialog(null, "Base de datos restablecida\nDebe reiniciar el sistema para aplicar los cambios");
					System.exit(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "Operación cancelada");
				}
			}
		});
		mnUtilidades.add(mntmInicializacinDeDatos);

		JMenuItem mntmConfiguraciones = new JMenuItem("Configuraciones");
		mntmConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioConfiguracion();
			}
		});
		mnUtilidades.add(mntmConfiguraciones);

		contentPane = new PanelFondo();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(5, 5, 998, 102);
		toolBar.setBorderPainted(false);
		toolBar.setForeground(new Color(240, 240, 240));
		toolBar.setEnabled(false);
		toolBar.setOpaque(false);
		toolBar.setOpaque(false);//transparente
		toolBar.setFloatable(false);//fixar el toolbar
		contentPane.add(toolBar);

		BotonIconoPrincipal btncnEmpeo = new BotonIconoPrincipal();
		btncnEmpeo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/empe\u00F1o.png")));
		btncnEmpeo.setEnabled(false);
		btncnEmpeo.setText("Empe\u00F1o");
		toolBar.add(btncnEmpeo);

		BotonIconoPrincipal btncnCobranza = new BotonIconoPrincipal();
		btncnCobranza.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/cobranza.png")));
		btncnCobranza.setEnabled(false);
		btncnCobranza.setText("Cobranza");
		toolBar.add(btncnCobranza);

		BotonIconoPrincipal btncnCliente = new BotonIconoPrincipal();
		btncnCliente.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/cliente.png")));
		btncnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioCliente();
			}
		});
		btncnCliente.setText("Cliente");
		toolBar.add(btncnCliente);

		BotonIconoPrincipal btncnSalir = new BotonIconoPrincipal();
		btncnSalir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/salir.png")));
		btncnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btncnSalir.setText("Salir");
		toolBar.add(btncnSalir);

		jPanelConfig = new JPanel();
		jPanelConfig.setBounds(5, 107, 600, 508);
		jPanelConfig.setOpaque(false);
		contentPane.add(jPanelConfig);
		GridBagLayout gbl_jPanelConfig = new GridBagLayout();
		gbl_jPanelConfig.columnWidths = new int[]{0, 0, 0};
		gbl_jPanelConfig.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_jPanelConfig.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanelConfig.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanelConfig.setLayout(gbl_jPanelConfig);

		lblNombre = new JLabel("");
		lblNombre.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNombre.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 33));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridheight = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 8, 0);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		jPanelConfig.add(lblNombre, gbc_lblNombre);

		lblDireccion = new JLabel("");
		lblDireccion.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.WEST;
		gbc_lblDireccion.gridheight = 2;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_lblDireccion.gridx = 1;
		gbc_lblDireccion.gridy = 4;
		jPanelConfig.add(lblDireccion, gbc_lblDireccion);

		lblTelefono = new JLabel("");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.gridheight = 2;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 6;
		jPanelConfig.add(lblTelefono, gbc_lblTelefono);

		lblEmail = new JLabel("");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridheight = 2;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 8;
		jPanelConfig.add(lblEmail, gbc_lblEmail);

		cargarConfiguracion();

	}

//---------------FORMULARIOS-------------------------------------------------------------

	private void abrirFormularioConfiguracion() {
		VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
		ventanaConfiguracion.setVisible(true);
	}

	private void abrirFormularioCliente() {
		VentanaCliente ventanaCliente = new VentanaCliente();
		ventanaCliente.setUpControlador();
		ventanaCliente.setVisible(true);
	}

	private void abrirFormularioProducto() {
		VentanaProducto ventanaProducto = new VentanaProducto();
		ventanaProducto.setUpControlador();
		ventanaProducto.setVisible(true);

	}

	private void abrirFormularioEmpeno() {
		VentanaEmpeno ventanaEmpeno = new VentanaEmpeno();
		ventanaEmpeno.setUpControlador();
		ventanaEmpeno.setVisible(true);
	}

//----------------DESACTIVAR FALLA TECLA F10------------------------------------------

	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == e.KEY_PRESSED){
			if(e.getKeyCode()== e.VK_F10 | e.getKeyCode() == e.VK_SPACE){
				e.consume();
			}
		}
		return false;
	}

//-----------------CARGAR FORMULARIO DE LA EMPRESA--------------------------------------

	public void cargarConfiguracion() {
		configuracionDao = new ConfiguracionDao();
		configuracion = configuracionDao.recuperarTodo();
		if (configuracion.size()==0) return;
		lblNombre.setText(configuracion.get(0).getNombre());
		lblDireccion.setText(configuracion.get(0).getDireccion());
		lblTelefono.setText(configuracion.get(0).getTelefono());
		lblEmail.setText(configuracion.get(0).getEmail());
	}

//----------------INICIALIZAR BASE DE DATOS------------------------------------------

	public void inicializarBaseDeDatos() {

		VentanaCliente a = new VentanaCliente();
		VentanaClienteControlador ventanaClienteControlador = new VentanaClienteControlador(a);
		ventanaClienteControlador.inicializarCliente();

		VentanaProducto b = new VentanaProducto();
		VentanaProductoControlador ventanaProductoControlador = new VentanaProductoControlador(b);
		ventanaProductoControlador.inicializarProducto();

		VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
		ventanaConfiguracion.inicializarConfiguracion();

		VentanaEmpeno d = new VentanaEmpeno();
		VentanaEmpenoControlador ventanaEmpenoControlador = new VentanaEmpenoControlador(d);
		ventanaEmpenoControlador.inicializarEmpeno();


	}
}
