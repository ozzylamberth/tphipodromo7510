package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

public class CampoListaItem {
	
	private String id;
	
	private String descripcion;
	
	public CampoListaItem(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getId() {
		return id;
	}
}
