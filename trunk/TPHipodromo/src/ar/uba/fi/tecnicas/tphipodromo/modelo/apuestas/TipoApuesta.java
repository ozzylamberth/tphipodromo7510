package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

public enum TipoApuesta {
	
	GANADOR("Apuesta Ganador", ApuestaGanador.class),
	SEGUNDO("Apuesta Segundo", ApuestaSegundo.class),
	TERCERO("Apuesta Tercero", ApuestaTercero.class),
	DOBLE("Apuesta Doble", ApuestaDoble.class),
	TRIPLO("Apuesta Triplo", ApuestaTriplo.class),
	CUATERNA("Apuesta Cuaterna", ApuestaCuaterna.class);
	//EXACTA("Apuesta Exacta", ApuestaExacta.class),
	//IMPERFECTA("Apuesta Imperfecta", ApuestaImperfecta.class),
	//TRIFECTA("Apuesta Trifecta", ApuestaTrifecta.class);
	
	private String nombre;
	@SuppressWarnings("unchecked")
	private Class tipoApuestaClass;
	
	@SuppressWarnings("unchecked")
	private TipoApuesta(String nombre, Class tipoApuestaClass) {
		this.nombre = nombre;
		this.tipoApuestaClass = tipoApuestaClass;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	@SuppressWarnings("unchecked")
	public Class getTipoApuestaClass() {
		return this.tipoApuestaClass;
	}
}
