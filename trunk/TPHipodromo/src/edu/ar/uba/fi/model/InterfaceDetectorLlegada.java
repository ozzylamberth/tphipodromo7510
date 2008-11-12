package edu.ar.uba.fi.model;

import java.util.List;

import edu.ar.uba.fi.exceptions.HipodromoException;

/**
 * Es la interface que define los métodos que podrá utilizar un dispositivo
 * detector de llegada. La clase carrera la implementa. Esto se realiza para que
 * el detector solo pueda utilizar los métodos de la clase carrera que sean
 * necesarios para determinar cuando los participantes llegan a la meta.
 * 
 * @author Fernando E. Mansilla - 84567
 * 
 */
public interface InterfaceDetectorLlegada {

	/**
	 * Finaliza la carrera y la deja a la espera de que se auditen los
	 * resultados. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li> Todos los participantes esten en estado ABANDONADO o bien EN_CURSO.</li>
	 * <li>La carrera este en un estado que permita finalizar la Misma.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li>Se pasa el estado de todos los participantes en estado EN_CURSO a
	 * A_AUDITAR.</li>
	 * <li> Se cambia el estado de la carrera a A_AUDITAR.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void terminar() throws HipodromoException;

	/**
	 * @return Lista de participantes asociados a la carrera.
	 */
	public List<Participante> getParticipantes();
}
