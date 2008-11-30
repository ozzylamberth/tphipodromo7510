/**
 * 
 */
package ar.uba.fi.tecnicas.tphipodromo.servicios.facades;

import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;



/**
 * @author Anibal Irrera
 *
 */

public interface FacadeAgenciaNoticias {
	
	public Collection<CarreraDTO> buscarCarrerasPorFecha(Date fecha);
		
	public Collection<ParticipanteDTO> buscarParticipantesPorCarrera(CarreraDTO carreraDTO) throws EntidadInexistenteException ;
	
	public ParticipanteDTO buscarParticipante(CarreraDTO carreraDTO) throws EntidadInexistenteException ;

	
}
