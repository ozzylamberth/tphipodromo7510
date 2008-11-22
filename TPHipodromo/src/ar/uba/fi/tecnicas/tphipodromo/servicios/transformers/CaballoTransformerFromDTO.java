package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CaballoDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

public class CaballoTransformerFromDTO implements Transformer {
	
	private CaballoDao caballoDao = new CaballoDaoMockImpl();

	public Object transform(Object arg0) {
		CaballoDTO caballoDTO = (CaballoDTO) arg0;
		Caballo caballo = new Caballo();
		caballo.setId(caballoDTO.getId());
		caballo.setCaballeriza(caballoDTO.getCaballeriza());
		caballo.setCriador(caballoDTO.getCriador());
		caballo.setEdad(caballoDTO.getEdad());
		caballo.setNombre(caballoDTO.getNombre());
		caballo.setPelaje(caballoDTO.getPelaje());
		caballo.setPeso(new BigDecimal(caballoDTO.getPeso().toString()));
		caballo.setPuraSangre(caballoDTO.isPuraSangre());
		if (!caballoDTO.getPadreId().equals(new Long(0))) {
			caballo.setPadre(this.buscarCaballo(caballoDTO.getPadreId()));
		}
		if (!caballoDTO.getMadreId().equals(new Long(0))) {
			caballo.setPadre(this. buscarCaballo(caballoDTO.getMadreId()));
		}
		return caballo;
	}
	
	private Caballo buscarCaballo(Long id) {
		try {
			return this.caballoDao.buscarPorId(id);
		} catch (ObjetoInexistenteException e) {
			return null;
		}
	}

}
