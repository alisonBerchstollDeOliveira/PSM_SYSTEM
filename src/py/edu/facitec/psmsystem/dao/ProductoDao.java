package py.edu.facitec.psmsystem.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.psmsystem.entidad.Producto;

public class ProductoDao extends GenericDao<Producto>{
	
	public ProductoDao() {
		super (Producto.class);
	}

	public List<Producto> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();

		String sql = "from Producto where UPPER(descripcion) like :descri "
				+"or id = :id order by id ";
		
		Query<Producto> query = getSession().createQuery(sql);

		query.setParameter("descri", "%" + filtro.toUpperCase() + "%");
		
		
		int id = 0;
		try {
			id = Integer.parseInt(filtro);
		} catch (Exception e) {}
		query.setParameter("id", id);
		
		List<Producto> lista = query.getResultList();
		commit();
		return lista;
	}
}
