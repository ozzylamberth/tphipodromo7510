package ar.edu.uba.fi.model.businessobjet;

import java.util.ArrayList;

public class Estudiante {

	private Integer id;
	private String apellido = "";
	private String habilitado = "";
	private String nombre = "";
	private Integer nroDocumento = new Integer(0);
	private String tipoDocumento = "";
	ArrayList<Integer> listaNotas = new ArrayList<Integer>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public ArrayList<Integer> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(ArrayList<Integer> listaNotas) {
		this.listaNotas = listaNotas;
	}

	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Estudiante() {

	}

	public Estudiante(Integer id, String apellido, String habilitado,
			String nombre, Integer nroDocumento, String tipoDocumento) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.habilitado = habilitado;
		this.nombre = nombre;
		this.nroDocumento = nroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.listaNotas.add(10);
		this.listaNotas.add(9);
		this.listaNotas.add(8);
	}

	public void operacion1() {
		System.out.println("Operacion 1");
	}

	public void operacion2() {
		System.out.println("Operacion 2");
	}

}