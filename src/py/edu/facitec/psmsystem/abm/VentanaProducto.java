package py.edu.facitec.psmsystem.abm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import py.com.cs.xnumberfield.component.NumberTextField;
import py.edu.facitec.psmsystem.componente.VentanaGenerica;
import py.edu.facitec.psmsystem.controlador.VentanaProductoControlador;

public class VentanaProducto extends VentanaGenerica {
	private JTextField tfDescripcion;
	private NumberTextField tfPrecioVenta;
	private JLabel lblDetalle;
	private JLabel lblEstado;
	private JTextPane tfDetalle;
	private NumberTextField tfPrecioCompra;
	private JComboBox cbEstado;
	private JLabel lblVerificarPrecio;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProducto dialog = new VentanaProducto();
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
			new VentanaProductoControlador(this);
	}

	public VentanaProducto() {
		gettBuscador().setToolTipText("Buscar por ID o descripci\u00F3n");
		setTitle("Registro  Producto");
		gettBuscador().setSize(353, 20);
		getMiToolBar().setBounds(new Rectangle(10, 10, 400, 65));
		getMiToolBar().setMinimumSize(new Dimension(365, 65));
		getMiToolBar().setMaximumSize(new Dimension(365, 65));
		getMiToolBar().setSize(new Dimension(365, 65));
		getMiToolBar().setPreferredSize(new Dimension(365, 65));
		setMinimumSize(new Dimension(700, 500));
		setMaximumSize(new Dimension(800, 600));
		setSize(new Dimension(800, 501));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProducto.class.getResource("/img/icono.png")));
		gettBuscador().setLocation(421, 55);
		getPanelFormulario().setBounds(10, 80, 400, 370);
		getMiToolBar().setBounds(10, 10, 400, 65);
		
		JLabel lblDescripcion = new JLabel("Descripci�n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 31, 89, 19);
		getPanelFormulario().add(lblDescripcion);
		
		JLabel lblPCompra = new JLabel("P. Compra:");
		lblPCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCompra.setBounds(10, 76, 89, 19);
		getPanelFormulario().add(lblPCompra);
		
		JLabel lblPVenta = new JLabel("P. Venta:");
		lblPVenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPVenta.setBounds(10, 121, 89, 19);
		getPanelFormulario().add(lblPVenta);
		
		lblDetalle = new JLabel("Detalle:");
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDetalle.setBounds(33, 166, 66, 19);
		getPanelFormulario().add(lblDetalle);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(33, 263, 66, 20);
		getPanelFormulario().add(lblEstado);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setEnabled(false);
		tfDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfPrecioCompra.requestFocus();
					tfPrecioCompra.selectAll();
				}
			}
		});
		tfDescripcion.setBounds(104, 30, 251, 20);
		getPanelFormulario().add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		tfPrecioCompra = new NumberTextField();
		tfPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPrecioCompra.setEditable(false);
		tfPrecioCompra.setEnabled(false);
		tfPrecioCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfPrecioVenta.requestFocus();
					tfPrecioVenta.selectAll();
				}
			}
		});
		tfPrecioCompra.setColumns(10);
		tfPrecioCompra.setBounds(104, 75, 146, 20);
		getPanelFormulario().add(tfPrecioCompra);
		
		tfPrecioVenta = new NumberTextField();
		tfPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPrecioVenta.setEditable(false);
		tfPrecioVenta.setEnabled(false);
		tfPrecioVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfDetalle.requestFocus();
					tfDetalle.selectAll();
				}
			}
		});
		tfPrecioVenta.setColumns(10);
		tfPrecioVenta.setBounds(104, 120, 146, 19);
		getPanelFormulario().add(tfPrecioVenta);
		
		tfDetalle = new JTextPane();
		tfDetalle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfDetalle.setEditable(false);
		tfDetalle.setEnabled(false);
		tfDetalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_TAB) {
					cbEstado.requestFocus();
				}
			}
		});
		tfDetalle.setBounds(104, 166, 252, 80);
		getPanelFormulario().add(tfDetalle);
		
		cbEstado = new JComboBox();
		cbEstado.setEnabled(false);
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "En venta", "Anulado", "Vendido"}));
		cbEstado.setToolTipText("");
		cbEstado.setBounds(104, 265, 146, 19);
		getPanelFormulario().add(cbEstado);
		
		lblVerificarPrecio = new JLabel("*Precio de venta menor que de compra");
		lblVerificarPrecio.setVisible(false);
		lblVerificarPrecio.setForeground(Color.RED);
		lblVerificarPrecio.setBounds(99, 141, 291, 14);
		getPanelFormulario().add(lblVerificarPrecio);
		
		JLabel lblGs = new JLabel("Gs.");
		lblGs.setBounds(253, 80, 24, 14);
		getPanelFormulario().add(lblGs);
		
		JLabel label = new JLabel("Gs.");
		label.setBounds(253, 125, 24, 14);
		getPanelFormulario().add(label);
		
	}

	public JTextField gettfDescripcion() {
		return tfDescripcion;
	}
	public NumberTextField gettfPrecioCompra() {
		return tfPrecioCompra;
	}
	public NumberTextField gettfPrecioVenta() {
		return tfPrecioVenta;
	}
	public JTextPane gettfDetalle() {
		return tfDetalle;
	}
	public JComboBox getCbEstado() {
		return cbEstado;
	}

	public JLabel getLblVerificarPrecio() {
		return lblVerificarPrecio;
	}
}
