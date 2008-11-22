package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CaballoDTO implements IsSerializable {
	
	private Long id;
	private String caballeriza = "";
	private String criador = "";
	private Integer edad = new Integer(0);
	private String nombre = "";
	private String pelaje = "";
	private Double peso = new Double(0);
	private boolean puraSangre = false;
	private String padre = "";
	private String madre = "";
	
	// atributos para la creacion de un nuevo caballo
	private Long padreId = new Long(0);
	private Long madreId = new Long(0);

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaballeriza() {
		return caballeriza;
	}

	public void setCaballeriza(String caballeriza) {
		this.caballeriza = caballeriza;
	}

	public String getCriador() {
		return criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPelaje() {
		return pelaje;
	}

	public void setPelaje(String pelaje) {
		this.pelaje = pelaje;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public boolean isPuraSangre() {
		return puraSangre;
	}

	public void setPuraSangre(boolean puraSangre) {
		this.puraSangre = puraSangre;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getMadre() {
		return madre;
	}

	public void setMadre(String madre) {
		this.madre = madre;
	}

	public Long getPadreId() {
		return this.padreId;
	}

	public void setPadreId(Long padreId) {
		this.padreId = padreId;
	}

	public Long getMadreId() {
		return this.madreId;
	}

	public void setMadreId(Long madreId) {
		this.madreId = madreId;
	}
	
}
