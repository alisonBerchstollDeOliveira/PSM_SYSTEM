package py.edu.facitec.psmsystem.tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.psmsystem.entidad.Empeno;

public class TablaEmpeno extends AbstractTableModel {

	private String[] columnas = { "NUMERO", "CLIENTE", "MONTO", "ESTADO"};
	
	private List<Empeno> lista = new ArrayList<>();

	public void setLista(List<Empeno> lista) {
		this.lista = lista;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lista.get(rowIndex).getNumero();
		case 1:
			return lista.get(rowIndex).getCliente().getNombre();
		case 2:
			return lista.get(rowIndex).getValorTotal();
		case 3:
			if(lista.get(rowIndex).getEstado() == 0){
				return "Activo";
			} else {
				if(lista.get(rowIndex).getEstado() == 1){
					return "Vencido";
				} else {
					if(lista.get(rowIndex).getEstado() == 2){
						return "Cobrado";
					} else {
						if(lista.get(rowIndex).getEstado() == 3){
							return "Anulado";
						} else {
						}
					}
				}
			}
		default:
			break;
		}
		return null;
	}

}
