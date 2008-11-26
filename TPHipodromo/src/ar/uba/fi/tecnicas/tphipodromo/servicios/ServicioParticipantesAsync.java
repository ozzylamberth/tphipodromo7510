package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioParticipantesAsync {
	
	public void buscarTodos(AsyncCallback<Collection<ParticipanteDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<ParticipanteDTO> callback);
	
	public void guardar(ParticipanteDTO participanteDTO, AsyncCallback<Long> callback);
	
	public void borrar(Long id, AsyncCallback<Void> callback);
	
	public Collection<ParticipanteDTO> buscarPorCarrera(CarreraDTO carrera, AsyncCallback<Collection<ParticipanteDTO>> callback);
	
}
