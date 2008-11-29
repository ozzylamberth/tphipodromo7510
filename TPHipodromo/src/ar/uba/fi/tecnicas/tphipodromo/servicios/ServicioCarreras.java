package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;
import java.util.Date;

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
	
	public Collection<CarreraDTO> buscarPorFecha(Date fecha);
	
	/**
	 * Retorna las carreras que estan en esa fecha y que tengan el estado
	 * Abierta a Apuestas
	 */
	public Collection<CarreraDTO> buscarCarrerasApostables(Date fecha);
	
}
