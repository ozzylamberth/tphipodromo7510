package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;

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
	public Collection<CarreraDTO> buscarCarrerasApostablesPorFecha(Date fecha);
	
	/**
	 * Desasigna todos los participantes que tiene la carrera y asigna la
	 * nueva coleccion de participantes
	 */
	public void asignarParticipantes(CarreraDTO carreraDTO, Collection<ParticipanteDTO> participatesDTO)
		throws ErrorHipodromoException, EntidadInexistenteException;
	
	public Collection<String> obtenerSiguientesEstadosPosibles(CarreraDTO carreraDTO);
	
	public void cambiarEstadoCarrera(CarreraDTO carreraDTO, String estado) throws ErrorHipodromoException;
	
	public Collection<JockeyDTO> buscarJockeysFueraDeCarrera(CarreraDTO carreraDTO);
	
	public Collection<CaballoDTO> buscarCaballosFueraDeCarrera(CarreraDTO carreraDTO);
	
}
