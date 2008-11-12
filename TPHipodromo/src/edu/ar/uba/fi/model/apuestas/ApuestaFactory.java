package edu.ar.uba.fi.model.apuestas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.ImposibleFabricarApuestaException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.exceptions.TipoApuestaInvalidoException;
import edu.ar.uba.fi.model.Carrera;
import edu.ar.uba.fi.model.Configuracion;
import edu.ar.uba.fi.model.ConfiguracionManager;
import edu.ar.uba.fi.model.Participante;

/**
 * Se encarga de la creacion de los distintos tipos de apuestas. Antes de
 * retornar una Apuesta, le asigna su correspondiente Bolsa de Apuestas
 * 
 * @author ncampos
 * @author jgrande
 */
public class ApuestaFactory {
	private static final ApuestaFactory instance = new ApuestaFactory();

	public ApuestaFactory() {
	}

	public static ApuestaFactory getInstance() {
		return instance;
	}

	public <T extends Apuesta> Apuesta crear(Class<T> clazz,
			Participante participante, BigDecimal montoApostado)
			throws ImposibleFabricarApuestaException,
			CantidadParticipantesInvalidaException,
			CarreraCerradaAApuestasException,
			ParticipantesEnDistintasCarrerasException,
			TipoApuestaInvalidoException {

		Collection<Participante> participantes = new LinkedList<Participante>();

		participantes.add(participante);

		return this.crear(clazz, participantes, montoApostado);
	}

	public <T extends Apuesta> Apuesta crear(Class<T> clazz,
			Collection<Participante> participantes, BigDecimal montoApostado)
			throws ImposibleFabricarApuestaException,
			CantidadParticipantesInvalidaException,
			CarreraCerradaAApuestasException,
			ParticipantesEnDistintasCarrerasException,
			TipoApuestaInvalidoException {

		try {

			Set<Carrera> carreras = getCarreras(participantes);

			Carrera primerCarrera = Collections.min(getCarreras(participantes));
			Configuracion configuracion = null;
			boolean apuestaHabilitada = false;

			// Si no existe ningua configuración para la carrera se asume que no
			// hay restricciones.
			if (ConfiguracionManager.getInstancia().existenConfiguraciones(
					primerCarrera)) {
				configuracion = ConfiguracionManager.getInstancia()
						.getConfiguracion(primerCarrera, clazz);
				apuestaHabilitada = configuracion != null;
			} else {
				apuestaHabilitada = true;
			}
			if (apuestaHabilitada) {

				Constructor<T> constructor = clazz.getConstructor();
				T apuesta = constructor.newInstance();
				apuesta.setParticipantes(participantes);
				apuesta.setMontoApostado(montoApostado);

				BolsaApuestasAbstracta bolsaApuestas = BolsasApuestasManager
						.getInstance().getBolsaApuestas(clazz, carreras, configuracion);
				bolsaApuestas.addApuesta(apuesta);

				return apuesta;
			} else {
				throw new TipoApuestaInvalidoException();
			}

		} catch (SecurityException e) {
			throw new ImposibleFabricarApuestaException(e);
		} catch (NoSuchMethodException e) {
			throw new ImposibleFabricarApuestaException(e);
		} catch (IllegalArgumentException e) {
			throw new ImposibleFabricarApuestaException(e);
		} catch (InstantiationException e) {
			throw new ImposibleFabricarApuestaException(e);
		} catch (IllegalAccessException e) {
			throw new ImposibleFabricarApuestaException(e);
		} catch (InvocationTargetException e) {
			throw new ImposibleFabricarApuestaException(e);
		}

	}

	private Set<Carrera> getCarreras(Collection<Participante> participantes) {
		Set<Carrera> carreras = new HashSet<Carrera>();

		for (Participante participante : participantes) {
			carreras.add(participante.getCarrera());
		}

		return carreras;
	}

}