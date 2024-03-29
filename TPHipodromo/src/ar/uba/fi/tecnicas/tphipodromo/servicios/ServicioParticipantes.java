package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioParticipantes")
public interface ServicioParticipantes extends RemoteService{
	
	public Collection<ParticipanteDTO> buscarTodos();
	
	public ParticipanteDTO buscarPorId(Long id) throws EntidadInexistenteException;
	
	public Collection<ParticipanteDTO> buscarPorCarrera(CarreraDTO carrera) throws EntidadInexistenteException;
	
}
