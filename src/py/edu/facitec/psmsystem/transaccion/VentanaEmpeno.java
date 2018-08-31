package py.edu.facitec.psmsystem.transaccion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.hibernate.cfg.Configuration;

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.psmsystem.componente.VentanaGenerica;
import py.edu.facitec.psmsystem.controlador.VentanaEmpenoControlador;
import py.edu.facitec.psmsystem.dao.ConfiguracionDao;
import py.edu.facitec.psmsystem.entidad.Configuracion;
import py.edu.facitec.psmsystem.util.FechaUtil;

public class VentanaEmpeno extends VentanaGenerica{
	private JComboBox cbEstado;
	private JFormattedTextField tfFechaRegistro;
	private JFormattedTextField tfFechaVencimiento;
	private JTextField tfCliente;
	private JButton btnBuscarCliente;
	private NumberTextField tfValorEmpeno;
	private JTextPane tfObservacion;
	private JTextField tfDescripcion;
	private JTextPane tfDetalle;
	private NumberTextField tfValorTotal;
	private JTextField tfNumero;
	private JTextField tfCuota;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpeno dialog = new VentanaEmpeno();
					dialog.setUpControlador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpControlador() {
		new VentanaEmpenoControlador(this);
	}

	public VentanaEmpeno() {
		getMiToolBar().setBounds(10, 11, 400, 64);
		getMiToolBar().btncnEliminar.setText("Anular");
		getPanelFormulario().setBounds(10, 81, 400, 369);
		gettBuscador().setToolTipText("Buscar por n\u00FAmero o cliente");
		setTitle("Formulario de Empe\u00F1o");
		setBounds(100, 100, 800, 663);

		setLocationRelativeTo(this);
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 461, 764, 8);
		getContentPane().add(separator);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumero.setBounds(32, 33, 73, 20);
		getPanelFormulario().add(lblNumero);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(233, 33, 73, 20);
		getPanelFormulario().add(lblEstado);

		JLabel lblFechaDia = new JLabel("Fecha dia:");
		lblFechaDia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDia.setBounds(32, 86, 73, 20);
		getPanelFormulario().add(lblFechaDia);

		JLabel lblVencimiento = new JLabel("Vencimiento:");
		lblVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVencimiento.setBounds(210, 86, 96, 20);
		getPanelFormulario().add(lblVencimiento);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(32, 139, 73, 20);
		getPanelFormulario().add(lblCliente);

		JLabel lblValorEmpeno = new JLabel("Vlr. empe\u00F1o:");
		lblValorEmpeno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorEmpeno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorEmpeno.setBounds(-1, 192, 106, 20);
		getPanelFormulario().add(lblValorEmpeno);

		JLabel lblCuotas = new JLabel("Cuotas:");
		lblCuotas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCuotas.setBounds(252, 192, 54, 20);
		getPanelFormulario().add(lblCuotas);

		JLabel lblGs = new JLabel("Gs.");
		lblGs.setBounds(227, 200, 25, 14);
		getPanelFormulario().add(lblGs);

		JLabel lblObservacion = new JLabel("Observaci\u00F3n:");
		lblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObservacion.setBounds(-1, 233, 106, 20);
		getPanelFormulario().add(lblObservacion);

		tfNumero = new JTextField();
		tfNumero.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumero.setEditable(false);
		tfNumero.setBounds(115, 35, 73, 20);
		getPanelFormulario().add(tfNumero);
		tfNumero.setColumns(10);

		cbEstado = new JComboBox();
		cbEstado.setEnabled(false);
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Vencido", "Cobrado", "Anulado"}));
		cbEstado.setToolTipText("");
		cbEstado.setBounds(316, 36, 74, 19);
		getPanelFormulario().add(cbEstado);

		tfFechaRegistro = new JFormattedTextField(FechaUtil.getMascara());
		tfFechaRegistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					btnBuscarCliente.requestFocus();
				}
			}
		});
		tfFechaRegistro.setEditable(false);
		tfFechaRegistro.setEnabled(false);
		tfFechaRegistro.setBounds(115, 86, 73, 20);
		getPanelFormulario().add(tfFechaRegistro);

		tfFechaVencimiento = new JFormattedTextField(FechaUtil.getMascara());
		tfFechaVencimiento.setEditable(false);
		tfFechaVencimiento.setBounds(319, 88, 71, 20);
		getPanelFormulario().add(tfFechaVencimiento);

		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setEnabled(false);
		tfCliente.setBounds(115, 141, 237, 20);
		getPanelFormulario().add(tfCliente);
		tfCliente.setColumns(10);

		btnBuscarCliente = new JButton(". . .");
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfValorEmpeno.requestFocus();
					tfValorEmpeno.selectAll();
				}
			}
		});
		btnBuscarCliente.setToolTipText("Buscar cliente");
		btnBuscarCliente.setActionCommand("BuscarCliente");
		btnBuscarCliente.setBounds(352, 141, 38, 21);
		getPanelFormulario().add(btnBuscarCliente);

		tfValorEmpeno = new NumberTextField();
		tfValorEmpeno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfCuota.requestFocus();
					tfCuota.selectAll();
				}
			}
		});
		tfValorEmpeno.setEditable(false);
		tfValorEmpeno.setEnabled(false);
		tfValorEmpeno.setHorizontalAlignment(SwingConstants.RIGHT);
		tfValorEmpeno.setBounds(115, 194, 109, 20);
		getPanelFormulario().add(tfValorEmpeno);
		
		tfCuota = new JTextField();
		tfCuota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfObservacion.requestFocus();
					tfObservacion.selectAll();
					calculos();
				}
			}
		});
		tfCuota.setEditable(false);
		tfCuota.setEnabled(false);
		tfCuota.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCuota.setBounds(316, 194, 74, 20);
		getPanelFormulario().add(tfCuota);
		tfCuota.setColumns(10);

		tfObservacion = new JTextPane();
		tfObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_TAB) {
					tfDescripcion.requestFocus();
					tfDescripcion.selectAll();
				}
			}
		});
		tfObservacion.setEditable(false);
		tfObservacion.setEnabled(false);
		tfObservacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfObservacion.setBounds(118, 233, 272, 100);
		getPanelFormulario().add(tfObservacion);

		JLabel lblDatosDelProducto = new JLabel("Datos del producto");
		lblDatosDelProducto.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDatosDelProducto.setBounds(10, 472, 259, 29);
		getContentPane().add(lblDatosDelProducto);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripcion.setBounds(10, 512, 102, 20);
		getContentPane().add(lblDescripcion);

		JLabel lblDetalle = new JLabel("Detalle:");
		lblDetalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setBounds(10, 543, 102, 20);
		getContentPane().add(lblDetalle);

		JLabel lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorTotal.setBounds(479, 512, 102, 20);
		getContentPane().add(lblValorTotal);

		tfDescripcion = new JTextField();
		tfDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfDetalle.requestFocus();
					tfDetalle.selectAll();
				}
			}
		});
		tfDescripcion.setEditable(false);
		tfDescripcion.setEnabled(false);
		tfDescripcion.setBounds(118, 512, 366, 20);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		tfValorTotal = new NumberTextField();
		tfValorTotal.setEditable(false);
		tfValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfValorTotal.setBounds(591, 514, 129, 20);
		getContentPane().add(tfValorTotal);

		tfDetalle = new JTextPane();
		tfDetalle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfDetalle.setEnabled(false);
		tfDetalle.setEditable(false);
		tfDetalle.setBounds(118, 543, 463, 59);
		tfObservacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(tfDetalle);

	}
	
	private void calculos() {
		tfFechaVencimiento.setValue(FechaUtil.convertirDateUtilAString(FechaUtil.sumarMes(FechaUtil.convertirStringADateUtil(tfFechaRegistro.getText()), Integer.parseInt(tfCuota.getText()) )));
		ConfiguracionDao dao = new ConfiguracionDao();
		Configuracion configuracion = dao.recuperarPorId(1);
		Double interes = ((Double.parseDouble(tfValorEmpeno.getText())*configuracion.getInteres()) / 100) * Integer.parseInt(tfCuota.getText());
		tfValorTotal.setText((interes + Double.parseDouble(tfValorEmpeno.getText()))+"");
	}


	public JTextField gettfNumero() {
		return tfNumero;
	}
	public JComboBox getcbEstado() {
		return cbEstado;
	}
	public JFormattedTextField gettfFechaRegistro() {
		return tfFechaRegistro;
	}
	public JFormattedTextField gettfFechaVencimiento() {
		return tfFechaVencimiento;
	}
	public JTextField gettfCliente() {
		return tfCliente;
	}
	public JButton getbtnBuscarCliente() {
		return btnBuscarCliente;
	}
	public NumberTextField gettfValorTotal() {
		return tfValorTotal;
	}
	public JTextField gettfCuota() {
		return tfCuota;
	}
	public JTextPane gettfObs() {
		return tfObservacion;
	}
	public JTextField gettfDescripcion() {
		return tfDescripcion;
	}
	public JTextPane gettfDetalle() {
		return tfDetalle;
	}
	public NumberTextField gettfValorEmpeno() {
		return tfValorEmpeno;
	}
}
