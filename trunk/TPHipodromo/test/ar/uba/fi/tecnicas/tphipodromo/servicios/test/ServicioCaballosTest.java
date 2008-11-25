package ar.uba.fi.tecnicas.tphipodromo.servicios.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioCaballosImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTrasnformerToDTO;
import junit.framework.TestCase;

public class ServicioCaballosTest extends TestCase {

	private ServicioCaballosImpl servicioCaballos = new ServicioCaballosImpl();
	private ArrayList<Long> ids = new ArrayList<Long>();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		vaciarBase();
		
		Caballo caballoMorochoAlado = new Caballo();
		caballoMorochoAlado.setCaballeriza("La caballeriza");
		caballoMorochoAlado.setCriador("Haras Santa Inés");
		caballoMorochoAlado.setEdad(3);
		caballoMorochoAlado.setMadre(null);
		caballoMorochoAlado.setNombre("Morocho Alado");
		caballoMorochoAlado.setPadre(null);
		caballoMorochoAlado.setPelaje("Marrón");
		caballoMorochoAlado.setPeso(new BigDecimal(120));
		caballoMorochoAlado.setPuraSangre(Boolean.TRUE);
		
		Caballo caballoRideLi = new Caballo();
		caballoRideLi.setCaballeriza("otra caballeriza");
		caballoRideLi.setCriador("Santa Inés");
		caballoRideLi.setEdad(2);
		caballoRideLi.setMadre(null);
		caballoRideLi.setNombre("Ride Li");
		caballoRideLi.setPadre(null);
		caballoRideLi.setPelaje("Negro");
		caballoRideLi.setPeso(new BigDecimal(130));
		caballoRideLi.setPuraSangre(Boolean.FALSE);
		
		Caballo caballoPegazo = new Caballo();
		caballoPegazo.setCaballeriza("Caballeriza Real");
		caballoPegazo.setCriador("Irigoyen");
		caballoPegazo.setEdad(2);
		caballoPegazo.setMadre(caballoMorochoAlado);
		caballoPegazo.setNombre("Pegazo");
		caballoPegazo.setPadre(caballoRideLi);
		caballoPegazo.setPelaje("Blanco");
		caballoPegazo.setPeso(new BigDecimal(150));
		caballoPegazo.setPuraSangre(Boolean.TRUE);

		CaballoTrasnformerToDTO caballoTrasnformerToDTO = new CaballoTrasnformerToDTO();
		
		Long id;
		
		id  = servicioCaballos.guardar((CaballoDTO) caballoTrasnformerToDTO.transform(caballoMorochoAlado));
		ids.add(id);
		
		id = servicioCaballos.guardar((CaballoDTO) caballoTrasnformerToDTO.transform(caballoRideLi));
		ids.add(id);
		
		id = servicioCaballos.guardar((CaballoDTO) caballoTrasnformerToDTO.transform(caballoPegazo));
		ids.add(id);
		
	}
	
	private void vaciarBase() {
		
		Iterator<CaballoDTO> it = servicioCaballos.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			CaballoDTO caballoDTO = (CaballoDTO) it.next();
			try {
				servicioCaballos.borrar(caballoDTO.getId());
			} catch (EntidadInexistenteException e) {
			}
		}
	}
	
	public void testBuscarTodos() {
		
		Iterator<CaballoDTO> it = servicioCaballos.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			CaballoDTO caballoDTO = (CaballoDTO) it.next();
			assertTrue(ids.contains(caballoDTO.getId()));
		}
		
	}
	
	public void testBuscarPorId() {
		
		try {
			
			Iterator<Long> it = ids.iterator();
			
			while (it.hasNext()) {
				
				Long id = (Long) it.next();
				servicioCaballos.buscarPorId(id);
			}
			
		} catch (EntidadInexistenteException e) {
			fail("Esta excepción no se debería haber lanzado");
		}
		
	}
	
	public void testEntidadInexistenteException() {
		
		Random generator = new Random();
		Long id = generator.nextLong();
	
		while (ids.contains(id)) {
			id = generator.nextLong();
		}
		
		try {
			servicioCaballos.buscarPorId(id);
			fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
		} catch (EntidadInexistenteException e) {
		}
	}

	public void testBorrar() {

		Iterator<Long> it = ids.iterator();

		while (it.hasNext()) {

			Long id = (Long) it.next();
			try {
				
				servicioCaballos.borrar(id);
				servicioCaballos.buscarPorId(id);
				fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
				
			} catch (EntidadInexistenteException e) {
			}
		}
		
	}
}
