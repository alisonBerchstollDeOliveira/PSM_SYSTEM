package py.edu.facitec.psmsystem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.facitec.psmsystem.buscador.BuscadorCliente;
import py.edu.facitec.psmsystem.dao.EmpenoDao;
import py.edu.facitec.psmsystem.entidad.Cliente;
import py.edu.facitec.psmsystem.entidad.Empeno;
import py.edu.facitec.psmsystem.interfaz.AccionesABM;
import py.edu.facitec.psmsystem.interfaz.InterfazBuscadorCliente;
import py.edu.facitec.psmsystem.tabla.TablaEmpeno;
import py.edu.facitec.psmsystem.transaccion.VentanaEmpeno;
import py.edu.facitec.psmsystem.util.FechaUtil;

public class VentanaEmpenoControlador implements AccionesABM, KeyListener, ActionListener, InterfazBuscadorCliente{

	private VentanaEmpeno vEmpeno;
	private TablaEmpeno mtEmpeno;
	private EmpenoDao dao;
	private List<Empeno> lista;
	private String accion;
	private Empeno empeno;
	private Cliente cliente;

	public VentanaEmpenoControlador(VentanaEmpeno vEmpeno) {
		this.vEmpeno = vEmpeno;

		this.vEmpeno.getMiToolBar().setAcciones(this);

		mtEmpeno = new TablaEmpeno();
		this.vEmpeno.getTable().setModel(mtEmpeno);

		estadoInicialCampos(true);

		dao = new EmpenoDao();

		recuperarTodo();

		setUpEvents();
	}

	private void setUpEvents() {
		vEmpeno.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarFormulario(vEmpeno.getTable().getSelectedRow());
			}
		});
		//PARA LLAMAR LA ACCION DEL BOTON BUSCAR CLIENTE
		vEmpeno.getbtnBuscarCliente().addActionListener(this);
		vEmpeno.getbtnBuscarCliente().setEnabled(false);

	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtEmpeno.setLista(lista);
		mtEmpeno.fireTableDataChanged();
	}

	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(vEmpeno.gettBuscador().getText());
		mtEmpeno.setLista(lista);
		mtEmpeno.fireTableDataChanged();
	}

		private void cargarFormulario(int posicion) {
			if (posicion < 0) {
				return;
			}
			empeno = lista.get(posicion);
			
			vEmpeno.gettfNumero().setText(empeno.getNumero()+"");
			vEmpeno.gettfFechaRegistro().setValue(FechaUtil.convertirDateUtilAString(empeno.getFechaDia()));
			vEmpeno.gettfFechaVencimiento().setValue(FechaUtil.convertirDateUtilAString(empeno.getFechaVencimiento()));
			vEmpeno.gettfCliente().setText(empeno.getCliente()+"");
			vEmpeno.gettfValorTotal().setValue(empeno.getValorTotal());
			vEmpeno.gettfValorTotal().setValue(empeno.getValorTotal());
			vEmpeno.gettfCuota().setText(empeno.getDeudaClientes().size()+"");
			
			this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,3);
			estadoInicialCampos(true);
			estadoInicialCampos2(false);
		}

	private void estadoInicialCampos(boolean b) {
		this.vEmpeno.gettfFechaRegistro().setEnabled(b);
		this.vEmpeno.gettfFechaVencimiento().setEnabled(b);
		this.vEmpeno.gettfCliente().setEnabled(b);
		this.vEmpeno.getbtnBuscarCliente().setEnabled(b);
		this.vEmpeno.gettfValorTotal().setEnabled(b);
		this.vEmpeno.gettfCuota().setEnabled(b);
		this.vEmpeno.gettfObs().setEnabled(b);
		this.vEmpeno.gettfDescripcion().setEnabled(b);
		this.vEmpeno.gettfValorEmpeno().setEnabled(b);
		this.vEmpeno.gettfDetalle().setEnabled(b);

		this.vEmpeno.getTable().clearSelection();
	}

	private void estadoInicialCampos2(boolean b) {
		this.vEmpeno.gettfFechaRegistro().setEditable(b);
		this.vEmpeno.gettfFechaVencimiento().setEditable(b);
		this.vEmpeno.gettfValorTotal().setEditable(b);
		this.vEmpeno.gettfCuota().setEditable(b);
		this.vEmpeno.gettfObs().setEditable(b);
		this.vEmpeno.gettfDescripcion().setEditable(b);
		this.vEmpeno.gettfValorEmpeno().setEditable(b);
		this.vEmpeno.gettfDetalle().setEditable(b);

		this.vEmpeno.getTable().clearSelection();
	}

	private void vaciarFormulario() {
		vEmpeno.gettfNumero().setText("");
		vEmpeno.gettfFechaRegistro().setValue(null);
		vEmpeno.gettfFechaVencimiento().setValue(null);
		vEmpeno.gettfCliente().setText("");
		vEmpeno.gettfValorTotal().setText("");
		vEmpeno.gettfCuota().setText("");
		vEmpeno.gettfObs().setText("");
		vEmpeno.gettfDescripcion().setText("");
		vEmpeno.gettfValorEmpeno().setText("");
		vEmpeno.gettfDetalle().setText("");

		vEmpeno.getcbEstado().setSelectedIndex(0);
	}

	@Override
	public void nuevo() {
		vaciarFormulario();
		estadoInicialCampos(true);
		estadoInicialCampos2(true);
		accion = "NUEVO";
		vEmpeno.gettfFechaRegistro().requestFocus();
		this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,1);
		this.vEmpeno.getcbEstado().setEnabled(true);
		this.vEmpeno.getTable().setEnabled(false);
		vEmpeno.gettfFechaRegistro().setValue(FechaUtil.convertirDateUtilAString(new Date()));
		vEmpeno.gettfFechaRegistro().selectAll();

		vEmpeno.gettfNumero().setText(dao.recuperarSiguienteId()+"");

	}

	@Override
	public void modificar() {
		estadoInicialCampos(true);
		estadoInicialCampos2(true);
		this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,1);
		accion = "MODIFICAR";
		vEmpeno.gettfFechaRegistro().requestFocus();
		vEmpeno.gettfFechaRegistro().selectAll();
		this.vEmpeno.getcbEstado().setEnabled(true);
		this.vEmpeno.getTable().setEnabled(false);

	}

	@Override
	public void eliminar() {
		int posicion = vEmpeno.getTable().getSelectedRow();
		if (empeno == null) {		//VERIFICA QUE SE SELECCIONO UN REGISTRO
			JOptionPane.showMessageDialog(null, "Seleccione un registro");
		} else {
			int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el empeno: \n" + empeno.getNumero() +" "+ empeno.getCliente(), "ATENCIÓN", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				if (vEmpeno.getcbEstado().getSelectedIndex() != 3){
					try {
						this.vEmpeno.getcbEstado().setSelectedIndex(3);
						//							dao.eliminar(empeno);
						dao.commit();
						recuperarTodo();
						this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,2);
						vaciarFormulario();
					} catch (Exception e) {
						dao.rollback();
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Empeño ya anulado");
					dao.rollback();
				}
			}
		}

	}
	@Override
	public void guardar() {
		if(!validarCampos()) {
			return;
		}
		if (accion.equals("NUEVO")) {
			empeno = new Empeno();
		}










		try {
			if(accion.equals("NUEVO")){
				dao.insertar(empeno);
			} else {
				dao.modificar(empeno);
			}
			dao.commit();
			vaciarFormulario();
			this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,2);
			estadoInicialCampos(false);
			estadoInicialCampos2(false);
			recuperarTodo();

		} catch (Exception e) {
			dao.rollback();
			JOptionPane.showMessageDialog(null, "Se produjo un error al guardar", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		estadoInicialCampos(false);
		estadoInicialCampos2(false);
		this.vEmpeno.getcbEstado().setEnabled(false);
		this.vEmpeno.getTable().setEnabled(true);
	}

	@Override
	public void cancelar() {
		this.vEmpeno.getMiToolBar().estadoInicialToolBar(true,2);
		estadoInicialCampos(false);
		estadoInicialCampos2(false);
		this.vEmpeno.getcbEstado().setEnabled(false);
		vaciarFormulario();
		this.vEmpeno.getTable().setEnabled(true);

	}


//-----------------------------------VALIDAR CAMPOS OBLIGATORIOS------------------------------------------

	private boolean validarCampos() {
		if (vEmpeno.gettfCliente().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debes seleccionar un \"Cliente\" ");
			vEmpeno.gettfCliente().requestFocus();
			return false;
		}
		if (vEmpeno.gettfFechaVencimiento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "\"Fecha de vencimiento\" es obligatorio");
			vEmpeno.gettfFechaVencimiento().requestFocus();
			return false;
		}
		if (vEmpeno.gettfValorTotal().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo \"valor total\" es obligatorio");
			vEmpeno.gettfValorTotal().requestFocus();
			return false;
		}
		//SI LA FECHA NO ES OBLIGATORIA. SI ES OBLIGATORIA SE OBVIA LA VALIDACION:
		//v!Empeno.gettFechaRegistro().getText().equals("__/__/____") &&
		if (!vEmpeno.gettfFechaRegistro().getText().equals("__/__/____") && FechaUtil.convertirStringADateUtil(vEmpeno.gettfFechaRegistro().getText()) == null) {
			JOptionPane.showMessageDialog(null, "Ingrese una fecha valida");
			vEmpeno.gettfFechaRegistro().requestFocus();
			return false;

		}
		return true;

	}

//-----------------------------------INICIALIZAR BASE DE DATOS-------------------------------------

	public void inicializarEmpeno() {
		String tabla = "tb_empeno";
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
		if (e.getSource() == vEmpeno.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}

	}

	public void abrirBuscarCliente() {
		BuscadorCliente buscador = new BuscadorCliente();
		buscador.setUpController();
		buscador.getControlador().setInterfaz(this);
		buscador.setVisible(true);
	}

	@Override
	public void setCliente(Cliente cliente) {
		vEmpeno.gettfCliente().setText(cliente.getNombre());
		this.cliente = cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		System.out.println(e);
		switch (e.getActionCommand()) {
		case "BuscarCliente":
			abrirBuscarCliente();
			break;
		default:
			break;
		}
	}
	


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


}
