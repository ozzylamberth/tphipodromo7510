package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class ApuestaDaoHibernate extends HibernateDaoGenerico<Apuesta> implements ApuestaDao {

	@SuppressWarnings("unchecked")
	@Override
	public Long buscarMayorNroTicket() throws MultiplesObjetosException {
		List<Long> id = getSession().createQuery("select max(a.nroTicket) from Apuesta a ").list();
			
		if (id.size()==0)
			return new Long(0);
		
		if (id.size()>1)
			throw new MultiplesObjetosException();
		
		if(id.get(0)==null){
			return new Long(0); 
		}
				
		return id.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Apuesta buscarPorNroTicket(Long nroTicket) throws ObjetoInexistenteException, MultiplesObjetosException {
					
			 List<Apuesta> apu = getSession()
			.createQuery("from Apuesta a where a.nroTicket = :p").setLong("p", nroTicket)
			.list();
				
			if (apu.size()==0)
				throw new ObjetoInexistenteException();
			
			if (apu.size()>1)
				throw new MultiplesObjetosException();
					
			return apu.get(0);
	}
}

