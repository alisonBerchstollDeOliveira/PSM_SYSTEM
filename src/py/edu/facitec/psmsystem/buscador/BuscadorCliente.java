package py.edu.facitec.psmsystem.buscador;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.facitec.psmsystem.componente.BuscadorGenerico;
import py.edu.facitec.psmsystem.controlador.BuscadorClienteControlador;

public class BuscadorCliente extends BuscadorGenerico {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorCliente dialog = new BuscadorCliente();
					dialog.setUpController();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private BuscadorClienteControlador controlador;
	
	public void setUpController() {
		controlador = new BuscadorClienteControlador(this);
	}
	
	public BuscadorClienteControlador getControlador( ){
		return controlador;
	}

	public BuscadorCliente(){
		
	}
	
}
