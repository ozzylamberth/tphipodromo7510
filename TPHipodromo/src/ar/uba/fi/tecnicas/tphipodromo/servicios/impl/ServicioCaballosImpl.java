package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CaballoDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServicioCaballosImpl extends RemoteServiceServlet implements ServicioCaballos {
	
	private CaballoDao caballoDao = new CaballoDaoMockImpl();

	public Collection<CaballoDTO> buscarTodos() {
		Collection<CaballoDTO> caballosDTO = new LinkedList<CaballoDTO>(); 
		Collection<Caballo> caballos = this.caballoDao.buscarTodos();
		Iterator<Caballo> it = caballos.iterator();
		while (it.hasNext()) {
			Caballo caballo = (Caballo) it.next();
			caballosDTO.add(GeneradorDTO.getInstance().convertToCaballoDTO(caballo));
		}
		return caballosDTO;
	}
	
	public DetalleCaballoDTO buscarCaballoPorNombre(String nombre) {
		Caballo caballo;
		try {
			caballo = this.caballoDao.buscarPorNombre(nombre);
		} catch (ObjetoInexistenteException e) {
			return null;
		}
		return GeneradorDTO.getInstance().convertToDetalleCaballoDTO(caballo);
	}

}
