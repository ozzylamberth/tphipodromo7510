/**
 * 
 */
package ar.uba.fi.tecnicas.tphipodromo.servicios.facades;

import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TipoApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TransicionInvalidaException;

/**
 * @author Anibal
 *
 */
public interface FacadeAgenciaApuestas {
	

	public Collection<ApuestaDTO> buscarApuestas();
	
	public ApuestaDTO buscarApuestaPorId(Long id) throws EntidadInexistenteException;
	
	public void crearApuesta(ApuestaDTO apuestaDTO) throws ApuestaInvalidaException, TipoApuestaInvalidaException;
		
	public Collection<String> obtenerTiposApuesta();
	
	public Double liquidarApuesta(ApuestaDTO apuestaDTO) throws EntidadInexistenteException, ErrorHipodromoException ;
			
	public void pagarApuesta(ApuestaDTO apuestaDTO) throws EntidadInexistenteException, TransicionInvalidaException;
	
	public Collection<CarreraDTO> buscarCarrerasApostablesPorFecha(Date fecha);
}
