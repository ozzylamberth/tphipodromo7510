package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Iterator;

import edu.ar.uba.fi.exceptions.HipodromoException;

public class BolsaApuestasConcreta extends BolsaApuestasAbstracta {


	
	public BolsaApuestasConcreta() {
	}

	public BigDecimal getDividendo() throws HipodromoException {
		BigDecimal totalApostado = new BigDecimal(0);
		BigDecimal totalGanadores = new BigDecimal(0);
		Iterator<Apuesta> it = this.apuestas.iterator();
		while (it.hasNext()) {
			Apuesta apuesta = (Apuesta) it.next();
			totalApostado = totalApostado.add(apuesta.getMontoApostado());
			if (apuesta.isAcertada()) {
				totalGanadores = totalGanadores.add(apuesta.getMontoApostado());
			}
		}
		BigDecimal porcentajeARepartir = new BigDecimal(1).subtract(this.getPorcentajeComisionHipodromo());
		BigDecimal totalARepartir = totalApostado.multiply(porcentajeARepartir);
		BigDecimal dividendo = totalARepartir.divide(totalGanadores, DECIMALES, ROUNDING_MODE);
		// si el dividendo de menor que uno, se retorna 1
		if (new BigDecimal(1).compareTo(dividendo) > 0) {
			return new BigDecimal(1);
		} else {
			return dividendo;
		}
	}
}
