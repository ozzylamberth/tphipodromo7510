package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;
import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CaballoDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.CaballoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoDTOTransformer;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.DetalleCaballoDTOTrasnformer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServicioCaballosImpl extends RemoteServiceServlet implements ServicioCaballos {
	
	private CaballoDao caballoDao = new CaballoDaoMockImpl();
	private CaballoDTOTransformer caballoDTOTransformer = new CaballoDTOTransformer();
	private DetalleCaballoDTOTrasnformer detalleCaballoDTOTrasnformer = new DetalleCaballoDTOTrasnformer();

	@SuppressWarnings("unchecked")
	public Collection<CaballoDTO> buscarTodos() { 
		Collection<Caballo> caballos = this.caballoDao.buscarTodos();
		return CollectionUtils.collect(caballos, this.caballoDTOTransformer);
	}
	
	public DetalleCaballoDTO buscarPorId(Long id) throws CaballoInexistenteException {
		try {
			Caballo caballo = this.caballoDao.buscarPorId(id);
			return ((DetalleCaballoDTO) this.detalleCaballoDTOTrasnformer.transform(caballo));
		} catch (ObjetoInexistenteException e) {
			throw new CaballoInexistenteException();
		}
	}
	
	public void guardar(DetalleCaballoDTO detalleCaballoDTO) throws CaballoInexistenteException {
		try {
			Caballo caballo = this.convertToCaballo(detalleCaballoDTO);
			this.caballoDao.guardar(caballo);
		} catch (ObjetoInexistenteException e) {
			throw new CaballoInexistenteException();
		}
	}
	
	private Caballo convertToCaballo(DetalleCaballoDTO detalleCaballoDTO) throws ObjetoInexistenteException {
		Caballo caballo = new Caballo();
		caballo.setId(detalleCaballoDTO.getId());
		caballo.setCaballeriza(detalleCaballoDTO.getCaballeriza());
		caballo.setCriador(detalleCaballoDTO.getCriador());
		caballo.setEdad(detalleCaballoDTO.getEdad());
		caballo.setNombre(detalleCaballoDTO.getNombre());
		caballo.setPelaje(detalleCaballoDTO.getPelaje());
		caballo.setPeso(new BigDecimal(detalleCaballoDTO.getPeso().toString()));
		caballo.setPuraSangre(detalleCaballoDTO.isPuraSangre());
		if (!detalleCaballoDTO.getPadreId().equals(new Long(0))) {
			caballo.setPadre(this.caballoDao.buscarPorId(detalleCaballoDTO.getPadreId()));
		}
		if (!detalleCaballoDTO.getMadreId().equals(new Long(0))) {
			caballo.setPadre(this.caballoDao.buscarPorId(detalleCaballoDTO.getMadreId()));
		}
		return caballo;
	}

}
