package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioCarrerasAsync {
	
	public void buscarTodos(AsyncCallback<Collection<CarreraDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<CarreraDTO> callback);
	
	public void guardar(CarreraDTO carreraDTO, AsyncCallback<Long> callback);
	
	public void borrar(Long id, AsyncCallback<Void> callback);
	
	public void buscarPorFecha(Date fecha, AsyncCallback<Collection<CarreraDTO>> callback);
	
	public void buscarCarrerasApostablesPorFecha(Date fecha, AsyncCallback<Collection<CarreraDTO>> callback);
	
	public void asignarParticipantes(CarreraDTO carreraDTO, Collection<ParticipanteDTO> participatesDTO, AsyncCallback<Void> callback);
	
}
