package model;
public class Resultado {

	private int ordenLlegada;
	private int tiempo;
	private Participante paricipante;

	public Resultado(Participante paricipante) {
		this.paricipante = paricipante;
	}

	public Resultado() {

	}
	
	public int getOrdenLlegada() {
		return ordenLlegada;
	}

	public void setOrdenLlegada(int ordenLlegada) {
		this.ordenLlegada = ordenLlegada;
	}

}