package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.collections.CollectionUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaCuaterna;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaDoble;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaExacta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaFactory;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaGanador;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaImperfecta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaSegundo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaTercero;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaTrifecta;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.ApuestaTriplo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CarreraException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoApuestaException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TipoApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TransicionInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ApuestaTransformerToDTO;

public class ServicioApuestasImpl extends RemoteServiceServlet implements ServicioApuestas {
	
	private ApuestaDao apuestaDao;
	private ApuestaTransformerToDTO apuestaTransformerToDTO = new ApuestaTransformerToDTO();
	private ParticipanteDao participanteDao;
	
	@SuppressWarnings("unchecked")
	private HashMap<String, Class> tiposApuestaSimples = new HashMap<String, Class>();
	@SuppressWarnings("unchecked")
	private HashMap<String, Class> tiposApuestaCompuestas = new HashMap<String, Class>();
	
	public ServicioApuestasImpl() {
		this.apuestaDao = (ApuestaDao) ServicioSpring.getInstance().getBean("apuestaDao");
		this.participanteDao = (ParticipanteDao) ServicioSpring.getInstance().getBean("participanteDao");
		this.initTiposApuestas();
	}
	
	public void initTiposApuestas() {
		this.tiposApuestaSimples.put(ApuestaGanador.TIPO_APUESTA, ApuestaGanador.class);
		this.tiposApuestaSimples.put(ApuestaSegundo.TIPO_APUESTA, ApuestaSegundo.class);
		this.tiposApuestaSimples.put(ApuestaTercero.TIPO_APUESTA, ApuestaTercero.class);
		
		this.tiposApuestaCompuestas.put(ApuestaCuaterna.TIPO_APUESTA, ApuestaCuaterna.class);
		this.tiposApuestaCompuestas.put(ApuestaDoble.TIPO_APUESTA, ApuestaDoble.class);
		this.tiposApuestaCompuestas.put(ApuestaImperfecta.TIPO_APUESTA, ApuestaImperfecta.class);
		this.tiposApuestaCompuestas.put(ApuestaExacta.TIPO_APUESTA, ApuestaExacta.class);
		this.tiposApuestaCompuestas.put(ApuestaTrifecta.TIPO_APUESTA, ApuestaTrifecta.class);
		this.tiposApuestaCompuestas.put(ApuestaTriplo.TIPO_APUESTA, ApuestaTriplo.class);
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
	
	public void crearApuesta(ApuestaDTO apuestaDTO) throws ApuestaInvalidaException, TipoApuestaInvalidaException {
		try {
			if (this.tiposApuestaSimples.get(apuestaDTO.getTipoApuesta()) != null) {
				this.crearApuestaSimple(apuestaDTO);
			} else if (this.tiposApuestaSimples.get(apuestaDTO.getTipoApuesta()) != null) {
				this.crearApuestaCompuesta(apuestaDTO);
			} else {
				throw new TipoApuestaInvalidaException();
			}
		} catch (ApuestaException e) {
			throw new ApuestaInvalidaException();
		} catch (CarreraException e) {
			throw new ApuestaInvalidaException();
		}
	}
	
	public Collection<String> obtenerTiposApuesta() {
		Collection<String> tiposApuesta = new LinkedList<String>();
		tiposApuesta.addAll(this.tiposApuestaSimples.keySet());
		tiposApuesta.addAll(this.tiposApuestaCompuestas.keySet());
		return tiposApuesta;
	}
	
	@SuppressWarnings("unchecked")
	private Apuesta crearApuestaSimple(ApuestaDTO apuestaDTO) throws ApuestaException, CarreraException, ApuestaInvalidaException {
		try {
			Class tipoApuesta = this.tiposApuestaSimples.get(apuestaDTO.getTipoApuesta());
			BigDecimal montoApostado = new BigDecimal(apuestaDTO.getMontoApostado().toString());
			Participante participante = this.participanteDao.buscarPorId(apuestaDTO.getParticipantesIds().iterator().next());
			return ApuestaFactory.getInstance().crear(tipoApuesta, participante, montoApostado);
		} catch (ObjetoInexistenteException e) {
			throw new ApuestaInvalidaException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Apuesta crearApuestaCompuesta(ApuestaDTO apuestaDTO) throws ApuestaException, CarreraException, ApuestaInvalidaException {
		Class tipoApuesta = this.tiposApuestaSimples.get(apuestaDTO.getTipoApuesta());
		BigDecimal montoApostado = new BigDecimal(apuestaDTO.getMontoApostado().toString());
		Collection<Participante> participantes = this.obtenerParticipantes(apuestaDTO.getParticipantesIds());
		return ApuestaFactory.getInstance().crear(tipoApuesta, participantes, montoApostado);
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

}
