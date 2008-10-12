package model;
import java.math.BigDecimal;

public class Apuesta {

	protected float montoApostado;
	protected int nroTicket;
	protected float valorBase;
	private Participante[] participantes;

	public Apuesta() {
	}
	
	public float getMontoApostado() {
		return montoApostado;
	}

	public void setMontoApostado(float montoApostado) {
		this.montoApostado = montoApostado;
	}

	private BigDecimal calcularMontoAPagar() {
		return null;
	}

	public int getCantidadParticipantes() {
		return 0;
	}

	public BigDecimal getValorBase() {
		return null;
	}

	public BigDecimal liquidar() {
		return null;
	}

	public void validarApuestaGanada()
	{
	}
}