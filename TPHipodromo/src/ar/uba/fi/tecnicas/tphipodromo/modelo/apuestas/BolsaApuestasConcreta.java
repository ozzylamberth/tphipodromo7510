package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.util.Iterator;

import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;


/**
 * Implementación de una Bolsa de Apuestas con un algoritmo de cálculo de
 * dividendos particular.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class BolsaApuestasConcreta extends BolsaApuestasAbstracta {

	public BolsaApuestasConcreta() {
	}

	/**
	 * @return Valor del dividendo. Se tiene en cuenta el pozo mínimo y el
	 *         incremento.
	 * @throws HipodromoException
	 */
	public BigDecimal getDividendo() throws HipodromoException {
		BigDecimal totalApostado = getIncrementoPozo();
		BigDecimal totalGanadores = new BigDecimal(0);
		Iterator<Apuesta> it = this.apuestas.iterator();
		while (it.hasNext()) {
			Apuesta apuesta = (Apuesta) it.next();
			totalApostado = totalApostado.add(apuesta.getMontoApostado());
			if (apuesta.isAcertada()) {
				totalGanadores = totalGanadores.add(apuesta.getMontoApostado());
			}
		}

		if (totalApostado.compareTo(getPozoMinimo()) < 0) {
			totalApostado = getPozoMinimo();
		}

		BigDecimal porcentajeARepartir = new BigDecimal(1).subtract(this
				.getPorcentajeComisionHipodromo());
		BigDecimal totalARepartir = totalApostado.multiply(porcentajeARepartir);
		BigDecimal dividendo = totalARepartir.divide(totalGanadores, DECIMALES,
				ROUNDING_MODE);
		// si el dividendo de menor que uno, se retorna 1
		if (new BigDecimal(1).compareTo(dividendo) > 0) {
			return new BigDecimal(1);
		} else {
			return dividendo;
		}
	}
}
