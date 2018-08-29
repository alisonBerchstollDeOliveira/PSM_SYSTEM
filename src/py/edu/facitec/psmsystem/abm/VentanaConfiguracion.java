package py.edu.facitec.psmsystem.abm;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import py.edu.facitec.psmsystem.app.VentanaPrincipal;
import py.edu.facitec.psmsystem.dao.ConfiguracionDao;
import py.edu.facitec.psmsystem.entidad.Configuracion;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaConfiguracion extends JDialog {
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private Configuracion configuracion;
	private ConfiguracionDao dao;
	private List<Configuracion> configuraciones;
	private JButton btnActualizar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(GraphiteLookAndFeel.class.getName());
					VentanaConfiguracion dialog = new VentanaConfiguracion();
					dialog.setUpControlador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void setUpControlador() {
	}

	public VentanaConfiguracion() {
		setTitle("Configuración");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfiguracion.class.getResource("/img/icono.png")));
		setBounds(100, 100, 396, 295);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		setModal(true);

		JLabel lblNombre = new JLabel("Nombre o raz\u00F3n social:");
		lblNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNombre.setBounds(10, 20, 165, 14);
		getContentPane().add(lblNombre);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblEmail.setBounds(10, 122, 131, 14);
		getContentPane().add(lblEmail);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblTelefono.setBounds(10, 88, 131, 14);
		getContentPane().add(lblTelefono);

		JLabel lblDireccion = new JLabel("R.U.C.:");
		lblDireccion.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblDireccion.setBounds(10, 49, 131, 14);
		getContentPane().add(lblDireccion);

		tfNombre = new JTextField();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfDireccion.requestFocus();
					tfDireccion.selectAll();
				}
			}
		});
		tfNombre.setBounds(179, 17, 181, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		tfDireccion = new JTextField();
		tfDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfTelefono.requestFocus();
					tfTelefono.selectAll();
				}
			}
		});
		tfDireccion.setBounds(179, 46, 181, 20);
		getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);

		tfTelefono = new JTextField();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfEmail.requestFocus();
					tfEmail.selectAll();
				}
			}
		});
		tfTelefono.setBounds(179, 85, 181, 20);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					btnActualizar.requestFocus();
				}
			}
		});
		tfEmail.setText("");
		tfEmail.setBounds(179, 119, 181, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Estas seguro que deseas actualizar los datos de la empresa?",
						"Atención!",
						JOptionPane.YES_NO_OPTION);
				if (respuesta==JOptionPane.YES_OPTION) {
					guardar();
				}
			}
		});
		btnActualizar.setBounds(24, 209, 97, 34);
		getContentPane().add(btnActualizar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(266, 209, 97, 34);
		getContentPane().add(btnCancelar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarFormulario();
			}
		});
		btnBorrar.setBounds(145, 209, 97, 34);
		getContentPane().add(btnBorrar);
		datosActuales();
	}

	//-------------------------FIN DEL CONSTRUCTOR--------------------------------

	private void cargarDatos() {
		configuracion = new Configuracion();
		configuracion.setId(1);
		configuracion.setNombre(tfNombre.getText());
		configuracion.setDireccion(tfDireccion.getText());
		configuracion.setTelefono(tfTelefono.getText());
		configuracion.setEmail(tfEmail.getText());
	}

	private void guardar(){
		cargarDatos();
		dao = new ConfiguracionDao();
		dao.insertarOModificar(configuracion);
		try {
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();
		}

		actualizarPantalla();
		dispose();
	}

	private void actualizarPantalla(){
		dao = new ConfiguracionDao();
		configuracion = dao.recuperarPorId(1);
		VentanaPrincipal.lblNombre.setText(configuracion.getNombre());
		VentanaPrincipal.lblDireccion.setText(configuracion.getDireccion());
		VentanaPrincipal.lblTelefono.setText(configuracion.getTelefono());
		VentanaPrincipal.lblEmail.setText(configuracion.getEmail());
	}

	private void datosActuales() {
		dao = new ConfiguracionDao();
		configuraciones = dao.recuperarTodo();
		if (configuraciones.size()==0) return;
		tfNombre.setText(configuraciones.get(0).getNombre());
		tfDireccion.setText(configuraciones.get(0).getDireccion());
		tfTelefono.setText(configuraciones.get(0).getTelefono());
		tfEmail.setText(configuraciones.get(0).getEmail());
	}

	private void vaciarFormulario() {
		dao = new ConfiguracionDao();
		tfNombre.setText("");
		tfDireccion.setText("");
		tfTelefono.setText("");
		tfEmail.setText("");
	}

	//-----------------------------------INICIALIZAR BASE DE DATOS-------------------------------------

	public void inicializarConfiguracion() {
		String tabla = "tb_configuracion";
		dao.eliminarTodos(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}


	public JTextField gettfNombre() {
		return tfNombre;
	}
	public JTextField gettfDireccion() {
		return tfDireccion;
	}
	public JTextField gettfTelefono() {
		return tfTelefono;
	}
	public JTextField gettfEmail() {
		return tfEmail;
	}
}
