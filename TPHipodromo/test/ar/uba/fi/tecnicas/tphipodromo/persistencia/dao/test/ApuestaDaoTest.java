package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.*;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.InscripcionCarreraCerradaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipanteNoCalificadoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;

public class ApuestaDaoTest extends PersistenciaTestCase{
	public void testApuesta () throws ParticipanteNoCalificadoException, ParticipantesEnDistintasCarrerasException, InscripcionCarreraCerradaException, TransicionInvalidaEstadoParticipanteException{
		Apuesta apuesta = new ApuestaExacta();
		Carrera carrera = new Carrera();

		Jockey jockey1 = new Jockey();
		jockey1.setApellido("Wasserman");
		jockey1.setNombre("Damian");
		jockey1.setPeso(new BigDecimal(100));
		
		Caballo nuevoCaballo = new Caballo();
		nuevoCaballo.setCaballeriza("caballeriza test");
		nuevoCaballo.setCriador("criador");
		nuevoCaballo.setEdad(4);
		nuevoCaballo.setNombre("pepe");
		nuevoCaballo.setPelaje("peludo");
		nuevoCaballo.setPeso(new BigDecimal(10));
		nuevoCaballo.setPuraSangre(true);

		Participante part1 = new Participante(nuevoCaballo, jockey1, carrera);
		part1.setNroParticipante(10);
		part1.setCarrera(carrera);
		part1.setEstado(EstadoParticipante.LARGADA_PENDIENTE);
		
		carrera.setDistancia(new BigDecimal(130.0));
		carrera.setFechaYHora(new Date());
		carrera.setNombre("ssss3");
		carrera.setNumero(132);
		carrera.setEstadoCarrera(EstadoCarrera.INSCRIPCION_PARTICIPANTES);
		carrera.addParticipante(part1);
		
		apuesta.setMontoApostado(new BigDecimal(10));
		apuesta.setNroTicket(1340);
		apuesta.addParticipante(part1);
		
		ApuestaDao dao = HibernateDaoFactory.getInstance().getApuestaDAO();
		dao.guardar(apuesta);
		
	}
}
