package py.edu.facitec.psmsystem.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.psmsystem.abm.VentanaCliente;
import py.edu.facitec.psmsystem.dao.ClienteDao;
import py.edu.facitec.psmsystem.entidad.Cliente;
import py.edu.facitec.psmsystem.interfaz.AccionesABM;
import py.edu.facitec.psmsystem.tabla.TablaCliente;

public class VentanaClienteControlador implements AccionesABM, KeyListener {
	private VentanaCliente vCliente;
	private String accion;
	private Cliente cliente;
	private ClienteDao dao;
	private List<Cliente> lista;
	private TablaCliente mtCliente;
	
	public VentanaClienteControlador(VentanaCliente vCliente) {
		this.vCliente = vCliente;

		this.vCliente.getMiToolBar().setAcciones(this);

		mtCliente = new TablaCliente();
		this.vCliente.getTable().setModel(mtCliente);

		estadoInicialCampos(true);

		dao = new ClienteDao();

		recuperarTodo();

		setUpEvents();
	}

	private void setUpEvents() {
		vCliente.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarFormulario(vCliente.getTable().getSelectedRow());
			}
		});
		vCliente.gettBuscador().addKeyListener(this);
	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtCliente.setLista(lista);
		mtCliente.fireTableDataChanged();
	}
	
	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(vCliente.gettBuscador().getText());
		mtCliente.setLista(lista);
		mtCliente.fireTableDataChanged();
	}

	private void cargarFormulario(int posicion) {
		if (posicion < 0) {
			return;
		}
		cliente = lista.get(posicion);
		
		vCliente.gettfNombre().setText(cliente.getNombre());
		vCliente.gettfDocumento().setText(cliente.getDocumento());
		vCliente.gettfTelefono().setText(cliente.getTelefono());
		vCliente.gettfDomicilio().setText(cliente.getDomicilio());
		vCliente.gettfEMail().setText(cliente.getEmail());
		
		this.vCliente.getMiToolBar().estadoInicialToolBar(true,3);
		estadoInicialCampos(true);
		estadoInicialCampos2(false);
	}

	private void estadoInicialCampos(boolean b) {
		this.vCliente.gettfNombre().setEnabled(b);
		this.vCliente.gettfDocumento().setEnabled(b);
		this.vCliente.gettfTelefono().setEnabled(b);
		this.vCliente.gettfDomicilio().setEnabled(b);
		this.vCliente.gettfEMail().setEnabled(b);

		this.vCliente.getTable().clearSelection();
	}
	
	private void estadoInicialCampos2(boolean b) {
		this.vCliente.gettfNombre().setEditable(b);
		this.vCliente.gettfDocumento().setEditable(b);
		this.vCliente.gettfTelefono().setEditable(b);
		this.vCliente.gettfDomicilio().setEditable(b);
		this.vCliente.gettfEMail().setEditable(b);
		
		this.vCliente.getTable().clearSelection();
	}

	private void vaciarFormulario() {
		vCliente.gettfNombre().setText("");
		vCliente.gettfDocumento().setText("");
		vCliente.gettfTelefono().setText("");
		vCliente.gettfDomicilio().setText("");
		vCliente.gettfEMail().setText("");
		vCliente.getLblDocumentoDuplicado().setVisible(false);
	}

	@Override
	public void nuevo() {
		vaciarFormulario();
		this.vCliente.getMiToolBar().estadoInicialToolBar(true,1);
		estadoInicialCampos(true);
		estadoInicialCampos2(true);
		accion = "NUEVO";
		vCliente.gettfNombre().requestFocus();
		this.vCliente.getTable().setEnabled(false);
	}

	@Override
	public void modificar() {
		estadoInicialCampos(true);
		estadoInicialCampos2(true);
		this.vCliente.getMiToolBar().estadoInicialToolBar(true,1);
		accion = "MODIFICAR";
		vCliente.gettfNombre().requestFocus();
		vCliente.gettfNombre().selectAll();
		this.vCliente.getTable().setEnabled(false);

	}

	@Override
	public void eliminar() {
		int posicion = vCliente.getTable().getSelectedRow();
		if (cliente == null) {		// Verifica que se seleccione un registro
			JOptionPane.showMessageDialog(null, "Seleccione un registro");
		} else {
			int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el cliente: \n"+ cliente.getNombre(), "ATENCIÓN", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					dao.eliminar(cliente);
					dao.commit();
					recuperarTodo();
					this.vCliente.getMiToolBar().estadoInicialToolBar(true,2);
					vaciarFormulario();
				} catch (Exception e) {
					dao.rollback();
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	@Override
	public void guardar() {
		if(verificarDocumento()) {
			return;
		}
		if (!validarCampos()) {// Si no se validan los campos para la ejecucion
			return;
		}
		if (accion.equals("NUEVO")) {// Si la accion es NUEVO se instancia un nuevo cliente
			cliente = new Cliente();
		}
		// Se cargan los valores obtenidos del formulario al objeto cliente
		cliente.setNombre(vCliente.gettfNombre().getText());
		cliente.setDocumento(vCliente.gettfDocumento().getText());
		cliente.setTelefono(vCliente.gettfTelefono().getText());
		cliente.setDomicilio(vCliente.gettfDomicilio().getText());
		cliente.setEmail(vCliente.gettfEMail().getText());

		try {
			if(accion.equals("NUEVO")){
				dao.insertar(cliente);
			} else {
				dao.modificar(cliente);
			}
			dao.commit();
			vaciarFormulario();
			this.vCliente.getMiToolBar().estadoInicialToolBar(true,2);
			estadoInicialCampos(false);
			estadoInicialCampos2(false);
			recuperarTodo();
			
		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, "Se produjo un error al guardar", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		estadoInicialCampos(false);
		estadoInicialCampos2(false);
		this.vCliente.getTable().setEnabled(true);

	}
	
	@Override
	public void cancelar() {
		this.vCliente.getMiToolBar().estadoInicialToolBar(true,2);
		estadoInicialCampos(false);
		estadoInicialCampos2(false);
		vaciarFormulario();
		this.vCliente.getTable().setEnabled(true);

	}
	
//------------------------METODO PARA VERIFICAR DOCUMENTO---------------------------
	
	private boolean verificarDocumento() {
		if (vCliente.gettfDocumento().getText().isEmpty()) {
			vCliente.getLblDocumentoDuplicado().setVisible(false);
			return false;
		}
		if(lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				boolean mismo = (!accion.equals("NUEVO") && cliente.getId() == lista.get(i).getId());
				if(vCliente.gettfDocumento().getText().equals(lista.get(i).getDocumento()) && !mismo) {
					
					vCliente.getLblDocumentoDuplicado().setVisible(true);
					vCliente.getLblDocumentoDuplicado().setText("*Documento duplicado");
					
//					vCliente.getLblDocumentoDuplicado().setVisible(true);
					return true;
				}
		}
		return false;
		}
		
		return false;
	}
	
//--------------------------------VALIDAR CAMPOS OBLIGATORIOS---------------------------------------

	private boolean validarCampos() {
		if (vCliente.gettfNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo \"Nombre\" es obligatorio");
			vCliente.gettfNombre().requestFocus();
			return false;
		}
		if (vCliente.gettfDocumento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo \"Documento\" es obligatorio");
			vCliente.gettfDocumento().requestFocus();
			return false;
		}
		if (vCliente.gettfTelefono().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo \"Telefono\" es obligatorio");
			vCliente.gettfTelefono().requestFocus();
			return false;
		}
		
		return true;

	}
	
//---------------------------------------------INICIALIZAR BASE DE DATOS-------------------------------------
	
	public void inicializarCliente() {
		String tabla = "tb_cliente";
		dao.eliminarTodos(tabla);
		try {
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
		}
	}
	
//----------------------------------------------------------------------------------------------------------

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == vCliente.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}

}
