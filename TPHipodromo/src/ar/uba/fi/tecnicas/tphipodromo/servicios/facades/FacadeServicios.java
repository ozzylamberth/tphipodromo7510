/**
 * 
 */
package ar.uba.fi.tecnicas.tphipodromo.servicios.facades;


import java.util.Collection;
import java.util.Date;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TipoApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TransicionInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioApuestasImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioCarrerasImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioParticipantesImpl;


/**
 * @author Anibal Irrera
 *
 */

public class FacadeServicios implements FacadeAgenciaApuestas,FacadeAgenciaNoticias{
	
	private ServicioApuestasImpl servicioApuesta;
	private ServicioCarrerasImpl servicioCarreras;
	private ServicioParticipantesImpl servicioParticipantes;
	
	public FacadeServicios() {
		super();
		this.servicioApuesta = new ServicioApuestasImpl();
		this.servicioCarreras = new ServicioCarrerasImpl();
		this.servicioParticipantes = new ServicioParticipantesImpl();
	}

	
	public Collection<ApuestaDTO> buscarApuestas(){
		return servicioApuesta.buscarTodos();
	}
	
	public ApuestaDTO buscarApuestaPorId(Long id) 
			throws EntidadInexistenteException{
		return servicioApuesta.buscarPorId(id);
	}
	
	public void crearApuesta(ApuestaDTO apuestaDTO) 
			throws ApuestaInvalidaException, TipoApuestaInvalidaException {
		servicioApuesta.crearApuesta(apuestaDTO);
	}
		
	public Collection<String> obtenerTiposApuesta() {
		return servicioApuesta.obtenerTiposApuesta();
	}
	
	public Double liquidarApuesta(ApuestaDTO apuestaDTO) 
			throws EntidadInexistenteException, ErrorHipodromoException {
		return servicioApuesta.liquidarApuesta(apuestaDTO);
	}
			
	public void pagarApuesta(ApuestaDTO apuestaDTO) 
			throws EntidadInexistenteException, TransicionInvalidaException {
		servicioApuesta.pagarApuesta(apuestaDTO);
	}
	
	public Collection<CarreraDTO> buscarCarrerasPorFecha(Date fecha) {
		return servicioCarreras.buscarPorFecha(fecha); 	
	}

	public Collection<CarreraDTO> buscarCarrerasApostablesPorFecha(Date fecha) { 
		return servicioCarreras.buscarCarrerasApostablesPorFecha(fecha);
	}
	
	public Collection<ParticipanteDTO> buscarParticipantesPorCarrera(CarreraDTO carreraDTO) 
			throws EntidadInexistenteException {
		return servicioParticipantes.buscarPorCarrera(carreraDTO);
	}
	
	public ParticipanteDTO buscarParticipante(CarreraDTO carreraDTO)
			throws EntidadInexistenteException {
		
		return null;
	}
		
}
