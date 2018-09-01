package py.edu.facitec.psmsystem.abm;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.psmsystem.app.VentanaPrincipal;
import py.edu.facitec.psmsystem.dao.ConfiguracionDao;
import py.edu.facitec.psmsystem.entidad.Configuracion;

public class VentanaConfiguracion extends JDialog {
	private JTextField tfNombre;
	private JTextField tfRuc;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private Configuracion configuracion;
	private ConfiguracionDao dao;
	private List<Configuracion> configuraciones;
	private JButton btnActualizar;
	private NumberTextField tfInteres;
	private JButton btnCancelar;
	private JButton btnBorrar;

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
		setBounds(100, 100, 396, 276);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		setModal(true);

		JLabel lblNombre = new JLabel("Nombre o raz\u00F3n social:");
		lblNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNombre.setBounds(10, 14, 165, 20);
		getContentPane().add(lblNombre);

		JLabel lblRuc = new JLabel("R.U.C.                      :");
		lblRuc.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblRuc.setBounds(10, 48, 165, 20);
		getContentPane().add(lblRuc);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono                   :");
		lblTelefono.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblTelefono.setBounds(10, 82, 165, 20);
		getContentPane().add(lblTelefono);

		JLabel lblEmail = new JLabel("Email                        :");
		lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblEmail.setBounds(10, 116, 165, 20);
		getContentPane().add(lblEmail);
		
		JLabel lblTazaInteres = new JLabel("Taza de  inter\u00E9s         :");
		lblTazaInteres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTazaInteres.setBounds(10, 150, 165, 20);
		getContentPane().add(lblTazaInteres);
		
		JLabel label = new JLabel("%");
		label.setBounds(226, 155, 46, 14);
		getContentPane().add(label);
		
		tfNombre = new JTextField();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfRuc.requestFocus();
					tfRuc.selectAll();
				}
			}
		});
		tfNombre.setBounds(179, 14, 181, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		tfRuc = new JTextField();
		tfRuc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfTelefono.requestFocus();
					tfTelefono.selectAll();
				}
			}
		});
		tfRuc.setBounds(179, 48, 181, 20);
		getContentPane().add(tfRuc);
		tfRuc.setColumns(10);

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
		tfTelefono.setBounds(179, 82, 181, 20);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfInteres.requestFocus();
					tfInteres.selectAll();
				}
			}
		});
		tfEmail.setText("");
		tfEmail.setBounds(179, 116, 181, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfInteres = new NumberTextField();
		tfInteres.setHorizontalAlignment(SwingConstants.RIGHT);
		tfInteres.setBounds(179, 150, 44, 20);
		tfInteres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					btnActualizar.requestFocus();
				}
			}
		});
		getContentPane().add(tfInteres);
		tfInteres.setColumns(10);
		
	
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		btnActualizar.setBounds(24, 194, 97, 34);
		getContentPane().add(btnActualizar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarFormulario();
			}
		});
			btnBorrar.setBounds(145, 194, 97, 34);
		getContentPane().add(btnBorrar);
	

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(266, 194, 97, 34);
		getContentPane().add(btnCancelar);

	
	
		datosActuales();
	}

//-------------------------FIN DEL CONSTRUCTOR--------------------------------

	private void cargarDatos() {
		configuracion = new Configuracion();
		configuracion.setId(1);
		configuracion.setNombre(tfNombre.getText());
		configuracion.setRuc(tfRuc.getText());
		configuracion.setTelefono(tfTelefono.getText());
		configuracion.setEmail(tfEmail.getText());
//		configuracion.setInteres(Double.parseDouble(tfInteres.getText()));
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
		VentanaPrincipal.lblRuc.setText(configuracion.getRuc());
		VentanaPrincipal.lblTelefono.setText(configuracion.getTelefono());
		VentanaPrincipal.lblEmail.setText(configuracion.getEmail());
	}

	private void datosActuales() {
		dao = new ConfiguracionDao();
		configuraciones = dao.recuperarTodo();
		if (configuraciones.size()==0) return;
		tfNombre.setText(configuraciones.get(0).getNombre());
		tfRuc.setText(configuraciones.get(0).getRuc());
		tfTelefono.setText(configuraciones.get(0).getTelefono());
		tfEmail.setText(configuraciones.get(0).getEmail());
//		tfInteres.setText(configuraciones.get(0).getInteres()+"");
	}

	private void vaciarFormulario() {
		dao = new ConfiguracionDao();
		tfNombre.setText("");
		tfRuc.setText("");
		tfTelefono.setText("");
		tfEmail.setText("");
//		tfInteres.setText("");
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
		
		VentanaPrincipal.lblNombre.setText("");
		VentanaPrincipal.lblRuc.setText("");
		VentanaPrincipal.lblTelefono.setText("");
		VentanaPrincipal.lblEmail.setText("");

	}


	public JTextField gettfNombre() {
		return tfNombre;
	}
	public JTextField gettfRuc() {
		return tfRuc;
	}
	public JTextField gettfTelefono() {
		return tfTelefono;
	}
	public JTextField gettfEmail() {
		return tfEmail;
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public JTextField getTfRuc() {
		return tfRuc;
	}
	public JTextField getTfTelefono() {
		return tfTelefono;
	}
	public JTextField getTfEmail() {
		return tfEmail;
	}
	public Configuracion getConfiguracion() {
		return configuracion;
	}
	public ConfiguracionDao getDao() {
		return dao;
	}
	public List<Configuracion> getConfiguraciones() {
		return configuraciones;
	}
	public JButton getBtnActualizar() {
		return btnActualizar;
	}
	public NumberTextField getTfInteres() {
		return tfInteres;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public JButton getBtnBorrar() {
		return btnBorrar;
	}


}
