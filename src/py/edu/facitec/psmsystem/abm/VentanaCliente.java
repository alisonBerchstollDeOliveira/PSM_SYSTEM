package py.edu.facitec.psmsystem.abm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.psmsystem.componente.VentanaGenerica;
import py.edu.facitec.psmsystem.controlador.VentanaClienteControlador;

public class VentanaCliente extends VentanaGenerica {
	private JTextField tfNombre;
	private JTextField tfDocumento;
	private JTextField tfTelefono;
	private JTextField tfDomicilo;
	private JTextField tfEmail;
	private JLabel lblValidarNombre;
	private JLabel lblValidarTelefono;
	private JLabel lblDocumentoDuplicado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente dialog = new VentanaCliente();
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
		new VentanaClienteControlador(this);
	}

	public VentanaCliente() {
		gettBuscador().setToolTipText("Buscar por ID, nombre o documento");
		getPanelFormulario().setSize(400, 370);
		getPanelFormulario().setLocation(10, 80);
		setTitle("Registro Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCliente.class.getResource("/img/icono.png")));
		gettBuscador().setLocation(420, 53);
		getMiToolBar().setBounds(new Rectangle(10, 10, 400, 65));
		getMiToolBar().setOpaque(false);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(21, 43, 73, 14);
		getPanelFormulario().add(lblNombre);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDocumento.setBounds(0, 100, 94, 14);
		getPanelFormulario().add(lblDocumento);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(21, 157, 73, 14);
		getPanelFormulario().add(lblTelefono);

		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDomicilio.setBounds(21, 214, 73, 14);
		getPanelFormulario().add(lblDomicilio);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(21, 271, 73, 14);
		getPanelFormulario().add(lblEmail);

		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setEnabled(false);
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c) & c!= e.VK_ENTER) {
					e.consume();
					lblValidarNombre.setVisible(true);
				}else{
					lblValidarNombre.setVisible(false);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfDocumento.requestFocus();
					tfDocumento.selectAll();
				}
			}
		});
		tfNombre.setBounds(100, 37, 290, 20);
		getPanelFormulario().add(tfNombre);
		tfNombre.setColumns(10);

		tfDocumento = new JTextField();
		tfDocumento.setEditable(false);
		tfDocumento.setEnabled(false);
		tfDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) & c!= e.VK_ENTER & c!= e.VK_BACK_SPACE) {
					e.consume();
					lblDocumentoDuplicado.setVisible(true);
				}else{
					lblDocumentoDuplicado.setVisible(false);
				}
				if (tfDocumento.getText().length()==10) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfTelefono.requestFocus();
					tfTelefono.selectAll();
				}
			}
		});
		tfDocumento.setBounds(100, 94, 105, 20);
		getPanelFormulario().add(tfDocumento);
		tfDocumento.setColumns(10);

		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setEnabled(false);
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) & c!= e.VK_ENTER & c!= e.VK_BACK_SPACE) {
					e.consume();
					lblValidarTelefono.setVisible(true);
				}else{
					lblValidarTelefono.setVisible(false);
				}
				if (tfTelefono.getText().length() == 20) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfDomicilo.requestFocus();
					tfDomicilo.selectAll();
				}
			}
		});
		tfTelefono.setBounds(100, 154, 105, 20);
		getPanelFormulario().add(tfTelefono);
		tfTelefono.setColumns(10);

		tfDomicilo = new JTextField();
		tfDomicilo.setEditable(false);
		tfDomicilo.setEnabled(false);
		tfDomicilo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == e.VK_ENTER) {
					tfEmail.requestFocus();
					tfEmail.selectAll();
				}
			}
		});
		tfDomicilo.setColumns(10);
		tfDomicilo.setBounds(100, 212, 290, 20);
		getPanelFormulario().add(tfDomicilo);

		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setEnabled(false);
		tfEmail.setBounds(100, 270, 290, 20);
		getPanelFormulario().add(tfEmail);
		tfEmail.setColumns(10);

		lblValidarNombre = new JLabel("*No se permite numeros");
		lblValidarNombre.setVisible(false);
		lblValidarNombre.setForeground(Color.RED);
		lblValidarNombre.setBounds(93, 57, 290, 14);
		getPanelFormulario().add(lblValidarNombre);

		lblDocumentoDuplicado = new JLabel("*No se permite letras");
		lblDocumentoDuplicado.setBounds(93, 114, 158, 14);
		getPanelFormulario().add(lblDocumentoDuplicado);
		lblDocumentoDuplicado.setVisible(false);
		lblDocumentoDuplicado.setForeground(Color.RED);

		lblValidarTelefono = new JLabel("*No se permite letras");
		lblValidarTelefono.setVisible(false);
		lblValidarTelefono.setForeground(Color.RED);
		lblValidarTelefono.setBounds(93, 173, 151, 14);
		getPanelFormulario().add(lblValidarTelefono);

	}

	public JTextField gettfNombre() {
		return tfNombre;
	}
	public JTextField gettfDocumento() {
		return tfDocumento;
	}
	public JTextField gettfTelefono() {
		return tfTelefono;
	}
	public JTextField gettfDomicilio() {
		return tfDomicilo;
	}
	public JTextField gettfEMail() {
		return tfEmail;
	}

	public JLabel getLblDocumentoDuplicado() {
		return lblDocumentoDuplicado;
	}


}
