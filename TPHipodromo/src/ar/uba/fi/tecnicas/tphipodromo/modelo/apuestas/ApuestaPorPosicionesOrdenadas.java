package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.util.Iterator;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;


/**
 * Representa a las apuestas que son ganadas cuando los participantes llegan en
 * determinadas posiciones teniendo en cuenta el orden de los mismos. Es decir,
 * el primer participante tiene que salir en la primera posicion determinada, el
 * segundo en la segunda, y asi sucesivamente
 */
public abstract class ApuestaPorPosicionesOrdenadas extends Apuesta {
	
	public ApuestaPorPosicionesOrdenadas(String tipoApuesta) {
		super(tipoApuesta);
	}

	/**
	 * Se sobreescribe el metodo isAcertada de la clase Apuesta para que se
	 * consideren los posibles ordenes de llegada en el orden exacto
	 */
	public boolean isAcertada() {
		int ordenLLegadaIndex = 0;
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			try {
				int ordenLLegada = participante.getResultado()
						.getOrdenLlegada();

				if (!this.getPosiblesOrdenesLLegada().get(ordenLLegadaIndex)
						.equals(new Integer(ordenLLegada))) {
					return false;
				}
			} catch (ResultadosCarreraInvalidosException e) {
				return false;
			}

			ordenLLegadaIndex++;
		}
		return true;
	}

}
