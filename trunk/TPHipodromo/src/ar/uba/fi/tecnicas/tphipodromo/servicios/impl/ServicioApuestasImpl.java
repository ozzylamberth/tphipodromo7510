package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.collections.CollectionUtils;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.TipoApuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TipoApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TransicionInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ApuestaTransformerToDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerToDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ServicioApuestasImpl extends RemoteServiceServlet implements ServicioApuestas {
	
	private ApuestaDao apuestaDao;
	private ApuestaTransformerToDTO apuestaTransformerToDTO = new ApuestaTransformerToDTO();
	private ParticipanteDao participanteDao;
	
	public ServicioApuestasImpl() {
		this.apuestaDao = HibernateDaoFactory.getInstance().getApuestaDAO();
		this.participanteDao = HibernateDaoFactory.getInstance().getParticipanteDAO();
		this.initSiguienteNroTicket();
	}
	
	private void initSiguienteNroTicket() {
		long siguienteNroTicket = this.apuestaDao.buscarMayorNroTicket().longValue() + 1;
		ApuestaFactory.initSiguienteNroTicket(siguienteNroTicket);
	}

	@SuppressWarnings("unchecked")
	public Collection<ApuestaDTO> buscarTodos() { 
		Collection<Apuesta> apuestas = this.apuestaDao.buscarTodos();
		return CollectionUtils.collect(apuestas, this.apuestaTransformerToDTO);
	}
	
	public ApuestaDTO buscarPorId(Long id) throws EntidadInexistenteException {
		Apuesta apuesta;
		try {
			apuesta = this.apuestaDao.buscarPorId(id);
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
		return ((ApuestaDTO) apuestaTransformerToDTO.transform(apuesta));
	}
	
	@SuppressWarnings("unchecked")
	public void crearApuesta(ApuestaDTO apuestaDTO) throws ApuestaInvalidaException, TipoApuestaInvalidaException {
		try {
			Class tipoApuesta = this.obtenerTipoApuestaClass(apuestaDTO.getTipoApuesta());
			BigDecimal montoApostado = new BigDecimal(apuestaDTO.getMontoApostado().toString());
			Collection<Participante> participantes = this.obtenerParticipantes(apuestaDTO.getParticipantesIds());
			
			if(participantes.size()==0)
				throw new ApuestaInvalidaException();
			
			ApuestaFactory.getInstance().crear(tipoApuesta, participantes, montoApostado);
		} catch (ApuestaException e) {
			throw new ApuestaInvalidaException();
		} catch (CarreraException e) {
			throw new ApuestaInvalidaException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Class obtenerTipoApuestaClass(String tipoApuesta) throws TipoApuestaInvalidaException {
		TipoApuesta[] tiposApuesta = TipoApuesta.values();
		for (int i = 0; i < tiposApuesta.length; i++) {
			if (tiposApuesta[i].getNombre().equals(tipoApuesta)) {
				return tiposApuesta[i].getTipoApuestaClass();
			}
		}
		throw new TipoApuestaInvalidaException();
	}
	
	public Collection<String> obtenerTiposApuesta() {
		Collection<String> tiposApuestaDTO = new LinkedList<String>();
		TipoApuesta[] tiposApuesta = TipoApuesta.values();
		for (int i = 0; i < tiposApuesta.length; i++) {
			tiposApuestaDTO.add(tiposApuesta[i].getNombre());
		}
		return tiposApuestaDTO;
	}
	
	private Collection<Participante> obtenerParticipantes(Collection<Long> participantesIds) throws ApuestaInvalidaException {
		Collection<Participante> participantes = new LinkedList<Participante>();
		Iterator<Long> it = participantesIds.iterator();
		try {
			while (it.hasNext()) {
				Long participanteId = (Long) it.next();
				participantes.add(this.participanteDao.buscarPorId(participanteId));
			}
		} catch (ObjetoInexistenteException e) {
			throw new ApuestaInvalidaException();
		}
		return participantes;
	}

	@Override
	public Double liquidarApuesta(ApuestaDTO apuestaDTO) throws EntidadInexistenteException, ErrorHipodromoException {
		try {
			Apuesta apuesta = this.apuestaDao.buscarPorId(apuestaDTO.getId());
			BigDecimal liquidacion = apuesta.liquidar();
			return new Double(liquidacion.doubleValue());
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		} catch (HipodromoException e) {
			throw new ErrorHipodromoException();
		}
	}

	@Override
	public void pagarApuesta(ApuestaDTO apuestaDTO) throws EntidadInexistenteException, TransicionInvalidaException {
		try {
			Apuesta apuesta = this.apuestaDao.buscarPorId(apuestaDTO.getId());
			apuesta.pagar();
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		} catch (TransicionInvalidaEstadoApuestaException e) {
			throw new TransicionInvalidaException();
		}
	}

	@Override
	public ApuestaDTO buscarPorNroTicket(Long nroTicket) throws EntidadInexistenteException {
		//TODO implementar
		return this.buscarPorId(nroTicket);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<ParticipanteDTO> obtenerParticipantesApuesta(ApuestaDTO apuestaDTO) throws EntidadInexistenteException {
		try {
			Apuesta apuesta = this.apuestaDao.buscarPorId(apuestaDTO.getId());
			return CollectionUtils.collect(apuesta.getParticipantes(), new ParticipanteTransformerToDTO());
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
	}

}
