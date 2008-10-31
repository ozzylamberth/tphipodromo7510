package edu.ar.uba.fi.services;

import java.math.BigDecimal;
import java.util.List;

public interface ApuestaService {
	
	public void crearApuesta(List<Long> participanteIDs, BigDecimal montoApostado);
	
	public BigDecimal liquidarApuesta(long apuestaId);
	
}
