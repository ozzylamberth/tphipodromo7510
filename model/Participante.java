package model;

public class Participante {

	private int nroParticipante;
	private Caballo caballo;
	private Jinete jinete;
	//private Carrera carrera;

	public Participante() {

	}

	public Participante(Caballo caballo, Jinete jinete) {
		
		this.caballo = caballo;
		this.jinete = jinete;
	}
}