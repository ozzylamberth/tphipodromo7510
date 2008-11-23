package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioCarreras")
public interface ServicioCarreras extends RemoteService{
	
	public Collection<CarreraDTO> buscarTodos();
	
	public CarreraDTO buscarPorId(Long id) throws EntidadInexistenteException;
	
	public Long guardar(CarreraDTO carreraDTO);
	
	public void borrar(Long id) throws EntidadInexistenteException;
	
}
