package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ErrorHipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTransformerToDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerToDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.JockeyTransformerToDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerFromDTO;

@SuppressWarnings("serial")
public class ServicioCarrerasImpl extends ServicioIdentificableImpl<Carrera, CarreraDTO> implements ServicioCarreras {
	
	private CarreraDao carreraDao;
	private CaballoDao caballoDao;
	private JockeyDao jockeyDao;
	private CarreraTransformerFromDTO carreraTransformerFromDTO = new CarreraTransformerFromDTO();
	private CarreraTransformerToDTO carreraTransformerToDTO = new CarreraTransformerToDTO();
	
	public ServicioCarrerasImpl() {
		this.carreraDao = HibernateDaoFactory.getInstance().getCarreraDAO();
		this.caballoDao = HibernateDaoFactory.getInstance().getCaballoDAO();
		this.jockeyDao = HibernateDaoFactory.getInstance().getJockeyDAO();
	}

	public DAOGenerico<Carrera> getDao() {
		return this.carreraDao;
	}

	public Transformer getTransformerFromDTO() {
		return this.carreraTransformerFromDTO;
	}

	public Transformer getTransformerToDTO() {
		return this.carreraTransformerToDTO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CarreraDTO> buscarPorFecha(Date fecha) {
		Collection<Carrera> carreras = this.carreraDao.buscarPorFecha(fecha); 
		return CollectionUtils.collect(carreras, this.getTransformerToDTO());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CarreraDTO> buscarCarrerasApostablesPorFecha(Date fecha) {
		Collection<Carrera> carreras = this.carreraDao.buscarCarrerasApostablesPorFecha(fecha); 
		return CollectionUtils.collect(carreras, this.getTransformerToDTO());
	}

	@Override
	public void asignarParticipantes(CarreraDTO carreraDTO, Collection<ParticipanteDTO> participatesDTO)
		throws ErrorHipodromoException, EntidadInexistenteException {
		
		try {
			Carrera carrera = this.carreraDao.buscarPorId(carreraDTO.getId());
			carrera.getParticipantes().clear();
			Iterator<ParticipanteDTO> it = participatesDTO.iterator();
			while (it.hasNext()) {
				ParticipanteDTO participanteDTO = (ParticipanteDTO) it.next();
				Participante participante = (Participante) (new ParticipanteTransformerFromDTO().transform(participanteDTO));
				carrera.addParticipante(participante);
			}
			this.carreraDao.guardar(carrera);
		} catch (HipodromoException e) {
			throw new ErrorHipodromoException();
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
	}
	
	/**
	 * Este metodo no es nada lindo, pero como era solo una linea por opcion,
	 * no tenia mucho sentido hacer un strategy
	 */
	private void cambiarEstadoCarrera(Carrera carrera, String estado) throws HipodromoException {
		if (EstadoCarrera.ABIERTA_A_APUESTAS.getNombre().equals(estado)){
			carrera.abrirApuestas();
		} else if (EstadoCarrera.CERRADA_A_APUESTAS.getNombre().equals(estado)){
			carrera.cerrarApuestas();
		} else if (EstadoCarrera.EN_CURSO.getNombre().equals(estado)){
			carrera.comenzar();
		} else if (EstadoCarrera.A_AUDITAR.getNombre().equals(estado)){
			carrera.terminar();
		} else if (EstadoCarrera.FINALIZADA.getNombre().equals(estado)){
			carrera.aprobarResultados();
		} else if (EstadoCarrera.CANCELADA.getNombre().equals(estado)){
			carrera.cancelar();
		}
	}

	@Override
	public void cambiarEstadoCarrera(CarreraDTO carreraDTO, String estado) throws ErrorHipodromoException {
		try {
			Carrera carrera = (Carrera) this.getTransformerFromDTO().transform(carreraDTO);
			this.cambiarEstadoCarrera(carrera, estado);
			this.carreraDao.guardar(carrera);
		} catch (HipodromoException e) {
			throw new ErrorHipodromoException();
		}
	}

	@Override
	public Collection<String> obtenerSiguientesEstadosPosibles(CarreraDTO carreraDTO) {
		Collection<String> result = new ArrayList<String>();
		Carrera carrera = (Carrera) this.getTransformerFromDTO().transform(carreraDTO);
		EstadoCarrera[] estadosSiguientes = carrera.getEstadoCarrera().getEstadosValidos();
		for (int i = 0; i < estadosSiguientes.length; i++) {
			result.add(estadosSiguientes[i].getNombre());
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CaballoDTO> buscarCaballosFueraDeCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = (Carrera) this.getTransformerFromDTO().transform(carreraDTO);
		Collection<Caballo> caballosFueraDeCarrera = new ArrayList<Caballo>();
		Iterator<Caballo> it = this.caballoDao.buscarTodos().iterator();
		while (it.hasNext()) {
			Caballo caballo = (Caballo) it.next();
			if (!this.caballoEstaEnCarrera(carrera, caballo)) {
				caballosFueraDeCarrera.add(caballo);
			}
		}
		return CollectionUtils.collect(caballosFueraDeCarrera, new CaballoTransformerToDTO());
	}
	
	private boolean caballoEstaEnCarrera(Carrera carrera, Caballo caballo) {
		Iterator<Participante> it = carrera.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			if (participante.getCaballo().equals(caballo)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<JockeyDTO> buscarJockeysFueraDeCarrera(CarreraDTO carreraDTO) {
		Carrera carrera = (Carrera) this.getTransformerFromDTO().transform(carreraDTO);
		Collection<Jockey> jockeysFueraDeCarrera = new ArrayList<Jockey>();
		Iterator<Jockey> it = this.jockeyDao.buscarTodos().iterator();
		while (it.hasNext()) {
			Jockey jockey = (Jockey) it.next();
			if (!this.jockeyFueraDeCarrera(carrera, jockey)) {
				jockeysFueraDeCarrera.add(jockey);
			}
		}
		return CollectionUtils.collect(jockeysFueraDeCarrera, new JockeyTransformerToDTO());
	}
	
	private boolean jockeyFueraDeCarrera(Carrera carrera, Jockey jockey) {
		Iterator<Participante> it = carrera.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			if (participante.getJockey().equals(jockey)) {
				return true;
			}
		}
		return false;
	}

}
