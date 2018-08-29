package py.edu.facitec.psmsystem.transaccion;

import java.awt.EventQueue;
import java.awt.Font;

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

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.psmsystem.componente.VentanaGenerica;
import py.edu.facitec.psmsystem.controlador.VentanaEmpenoControlador;
import py.edu.facitec.psmsystem.util.FechaUtil;

public class VentanaEmpeno extends VentanaGenerica{
	private JComboBox cbEstado;
	private JFormattedTextField tfFechaRegistro;
	private JFormattedTextField tfFechaVencimiento;
	private JTextField tfCliente;
	private JButton btnBuscarCliente;
	private NumberTextField tfValorTotal;
	private JTextPane tfObs;
	private JTextField tfDescripcion;
	private JTextPane tfDetalle;
	private NumberTextField tfValorEmpeno;
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
		getMiToolBar().btncnEliminar.setText("Anular");
		getPanelFormulario().setBounds(10, 81, 400, 369);
		gettBuscador().setToolTipText("Buscar por n\u00FAmero o cliente");
		setTitle("Formulario de empe\u00F1o");
		setBounds(100, 100, 800, 663);

		setLocationRelativeTo(this);
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 461, 764, 8);
		getContentPane().add(separator);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumero.setBounds(18, 33, 73, 20);
		getPanelFormulario().add(lblNumero);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(218, 33, 73, 20);
		getPanelFormulario().add(lblEstado);

		JLabel lblFechaDia = new JLabel("Fecha dia:");
		lblFechaDia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDia.setBounds(18, 86, 73, 20);
		getPanelFormulario().add(lblFechaDia);

		JLabel lblVencimiento = new JLabel("Vencimiento:");
		lblVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVencimiento.setBounds(198, 86, 96, 20);
		getPanelFormulario().add(lblVencimiento);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(18, 139, 73, 20);
		getPanelFormulario().add(lblCliente);

		JLabel lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorTotal.setBounds(10, 192, 81, 20);
		getPanelFormulario().add(lblValorTotal);

		JLabel lblCuotas = new JLabel("Cuotas:");
		lblCuotas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCuotas.setBounds(237, 192, 54, 20);
		getPanelFormulario().add(lblCuotas);

		JLabel lblGs = new JLabel("Gs.");
		lblGs.setBounds(214, 200, 25, 14);
		getPanelFormulario().add(lblGs);

		JLabel lblObs = new JLabel("Obs.:");
		lblObs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObs.setBounds(37, 233, 54, 20);
		getPanelFormulario().add(lblObs);

		tfNumero = new JTextField();
		tfNumero.setEditable(false);
		tfNumero.setEnabled(false);
		tfNumero.setBounds(101, 35, 73, 20);
		getPanelFormulario().add(tfNumero);
		tfNumero.setColumns(10);

		cbEstado = new JComboBox();
		cbEstado.setEnabled(false);
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Vencido", "Cobrado", "Anulado"}));
		cbEstado.setToolTipText("");
		cbEstado.setBounds(301, 36, 74, 19);
		getPanelFormulario().add(cbEstado);

		tfFechaRegistro = new JFormattedTextField(FechaUtil.getMascara());
		tfFechaRegistro.setEditable(false);
		tfFechaRegistro.setEnabled(false);
		tfFechaRegistro.setBounds(101, 86, 73, 19);
		getPanelFormulario().add(tfFechaRegistro);

		tfFechaVencimiento = new JFormattedTextField(FechaUtil.getMascara());
		tfFechaVencimiento.setEnabled(false);
		tfFechaVencimiento.setEditable(false);
		tfFechaVencimiento.setBounds(301, 88, 71, 19);
		getPanelFormulario().add(tfFechaVencimiento);

		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setEnabled(false);
		tfCliente.setBounds(101, 142, 237, 18);
		getPanelFormulario().add(tfCliente);
		tfCliente.setColumns(10);

		btnBuscarCliente = new JButton(". . .");
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.setToolTipText("Buscar cliente");
		btnBuscarCliente.setActionCommand("BuscarCliente");
		btnBuscarCliente.setBounds(337, 141, 38, 20);
		getPanelFormulario().add(btnBuscarCliente);

		tfValorTotal = new NumberTextField();
		tfValorTotal.setEditable(false);
		tfValorTotal.setEnabled(false);
		tfValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfValorTotal.setBounds(101, 194, 109, 20);
		getPanelFormulario().add(tfValorTotal);

		tfObs = new JTextPane();
		tfObs.setEditable(false);
		tfObs.setEnabled(false);
		tfObs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfObs.setBounds(101, 233, 272, 100);
		getPanelFormulario().add(tfObs);

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

		JLabel lblValorEmpeno = new JLabel("Valor empe\u00F1o:");
		lblValorEmpeno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorEmpeno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorEmpeno.setBounds(479, 512, 121, 20);
		getContentPane().add(lblValorEmpeno);

		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setEnabled(false);
		tfDescripcion.setBounds(118, 512, 366, 20);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		tfValorEmpeno = new NumberTextField();
		tfValorEmpeno.setEditable(false);
		tfValorEmpeno.setEnabled(false);
		tfValorEmpeno.setHorizontalAlignment(SwingConstants.RIGHT);
		tfValorEmpeno.setBounds(610, 514, 129, 20);
		getContentPane().add(tfValorEmpeno);

		tfDetalle = new JTextPane();
		tfDetalle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfDetalle.setEnabled(false);
		tfDetalle.setEditable(false);
		tfDetalle.setBounds(118, 543, 463, 59);
		tfObs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		tfCuota = new JTextField();
		tfCuota.setEditable(false);
		tfCuota.setEnabled(false);
		tfCuota.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCuota.setBounds(301, 194, 74, 20);
		getPanelFormulario().add(tfCuota);
		tfCuota.setColumns(10);
		getContentPane().add(tfDetalle);

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
		return tfObs;
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
