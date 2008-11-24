package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.math.BigDecimal;
import java.util.Iterator;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class CaballoDaoMockImpl extends DAOGenericoMockImpl<Caballo> implements CaballoDao {
	
	Caballo morochoAldao, rideLi, pegazo;
	
	public CaballoDaoMockImpl() {
		morochoAldao = getMorochoAlado();
		rideLi = getRideLi();
		pegazo = getPegazo();
		
		this.guardar(morochoAldao);
		this.guardar(rideLi);
		this.guardar(pegazo);
	}
	
	public Caballo buscarPorNombre(String nombre) throws ObjetoInexistenteException {
		Iterator<Caballo> it = this.buscarTodos().iterator();
		while (it.hasNext()) {
			Caballo caballo = (Caballo) it.next();
			if (caballo.getNombre().equals(nombre)) {
				return caballo;
			}
		}
		throw new ObjetoInexistenteException();
	}
	
	private Caballo getMorochoAlado() {
		Caballo caballo = new Caballo();
		caballo.setId(0l);
		caballo.setCaballeriza("La caballeriza");
		caballo.setCriador("Haras Santa Inés");
		caballo.setEdad(3);
		caballo.setMadre(null);
		caballo.setNombre("Morocho Aldao");
		caballo.setPadre(null);
		caballo.setPelaje("Marrón");
		caballo.setPeso(new BigDecimal(120));
		caballo.setPuraSangre(Boolean.TRUE);
		return caballo;
	}
	
	private Caballo getRideLi() {
		Caballo caballo = new Caballo();
		caballo.setId(0l);
		caballo.setCaballeriza("otra caballeriza");
		caballo.setCriador("Santa Inés");
		caballo.setEdad(2);
		caballo.setMadre(null);
		caballo.setNombre("Ride Li");
		caballo.setPadre(null);
		caballo.setPelaje("Negro");
		caballo.setPeso(new BigDecimal(130));
		caballo.setPuraSangre(Boolean.FALSE);
		return caballo;
	}
	
	private Caballo getPegazo() {
		Caballo caballo = new Caballo();
		caballo.setId(0l);
		caballo.setCaballeriza("Caballeriza Real");
		caballo.setCriador("Irigoyen");
		caballo.setEdad(2);
		caballo.setMadre(morochoAldao);
		caballo.setNombre("Pegazo");
		caballo.setPadre(rideLi);
		caballo.setPelaje("Blanco");
		caballo.setPeso(new BigDecimal(150));
		caballo.setPuraSangre(Boolean.TRUE);
		return caballo;
	}

}
