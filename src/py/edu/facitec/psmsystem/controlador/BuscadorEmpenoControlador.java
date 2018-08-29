package py.edu.facitec.psmsystem.controlador;

import py.edu.facitec.psmsystem.buscador.BuscadorEmpeno;
import py.edu.facitec.psmsystem.tabla.TablaEmpeno;

public class BuscadorEmpenoControlador {

	private BuscadorEmpeno bEmpeno;
	private TablaEmpeno tEmpeno;
	
	public BuscadorEmpenoControlador (BuscadorEmpeno bEmpeno) {
		this.bEmpeno = bEmpeno;
		
		tEmpeno = new TablaEmpeno();
		
		this.bEmpeno.getTable().setModel(tEmpeno);
	}

}
