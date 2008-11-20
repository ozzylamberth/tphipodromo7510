package ar.uba.fi.tecnicas.tphipodromo.modelo;

import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;


/**
 * Es la interface que define los métodos que podrá utilizar un auditor de la
 * carrera. La clase carrera la implementa. Esto se realiza para que el auditor
 * solo pueda utilizar los métodos de la clase carrera que sean necesarios para
 * auditar.
 * 
 * @author Fernando E. Mansilla - 84567
 * 
 */
public interface InterfaceAuditor {

	/**
	 * Finaliza completamente la carrera, asegurando que los resultados son
	 * correctos. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li> Todos los participantes esten en estado ABANDONADO, FINALIZADO o
	 * bien DESCALIFICADO.</li>
	 * <li>La carrera este en un estado que permita aprobar los resultados.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li> Se cambia el estado de la carrera a FINALIZADA.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void aprobarResultados() throws HipodromoException;

	/**
	 * @return Lista de participantes asociados a la carrera.
	 */
	public List<Participante> getParticipantes();
}
