 package py.edu.facitec.psmsystem.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.facitec.psmsystem.entidad.Empeno;

public class EmpenoDao extends GenericDao<Empeno> {
	
	public EmpenoDao() {
		super(Empeno.class);
	}
	
	public List<Empeno> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();

		String sql = "from Empeno where UPPER(cliente) like :descri "
				+ "or numero = :numero order by numero ";
		
		Query<Empeno> query = getSession().createQuery(sql);
		
		query.setParameter("descri", "%" + filtro.toUpperCase() + "%");


		int numero = 0;
		try {
			numero = Integer.parseInt(filtro);
		} catch (Exception e) {}
		query.setParameter("id", numero);
		
		List<Empeno> lista = query.getResultList();
		commit();
		return lista;
	}

}
