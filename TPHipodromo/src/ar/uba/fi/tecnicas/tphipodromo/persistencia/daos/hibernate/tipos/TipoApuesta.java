package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.tipos;

public class TipoApuesta extends EnumUserType<ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.TipoApuesta> {
	public TipoApuesta(){
		super(ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.TipoApuesta.class);
	}
}
