package ar.uba.fi.tecnicas.tphipodromo.servicios.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioJockeysImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.JockeyTransformerToDTO;
import junit.framework.TestCase;

public class ServicioJockeysTest extends TestCase {

	private ServicioJockeysImpl servicioJockeys = new ServicioJockeysImpl();
	private ArrayList<Long> ids = new ArrayList<Long>();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		vaciarBase();
		
		Jockey jockey1 = getJockey("Juan", "Grande", new BigDecimal(70));
		Jockey jockey2 = getJockey("Tomas", "Lobbe", new BigDecimal(60));
		Jockey jockey3 = getJockey("Damian", "Wasserman", new BigDecimal(80));
		Jockey jockey4 = getJockey("El", "Facha", new BigDecimal(50));

		JockeyTransformerToDTO jockeyTransformerToDTO = new JockeyTransformerToDTO();
		
		Long id;
		
		id  = servicioJockeys.guardar((JockeyDTO) jockeyTransformerToDTO.transform(jockey1));
		ids.add(id);
		
		id  = servicioJockeys.guardar((JockeyDTO) jockeyTransformerToDTO.transform(jockey2));
		ids.add(id);
		
		id  = servicioJockeys.guardar((JockeyDTO) jockeyTransformerToDTO.transform(jockey3));
		ids.add(id);
		
		id  = servicioJockeys.guardar((JockeyDTO) jockeyTransformerToDTO.transform(jockey4));
		ids.add(id);
		
	}
	
	private Jockey getJockey(String nombre, String apellido, BigDecimal peso) {
		Jockey jockey = new Jockey();
		jockey.setNombre(nombre);
		jockey.setApellido(apellido);
		jockey.setPeso(peso);
		return jockey;
	}
	
	private void vaciarBase() {
		
		Iterator<JockeyDTO> it = servicioJockeys.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			JockeyDTO jockeyDTO = (JockeyDTO) it.next();
			try {
				servicioJockeys.borrar(jockeyDTO.getId());
			} catch (EntidadInexistenteException e) {
			}
		}
	}
	
	public void testBuscarTodos() {
		
		Iterator<JockeyDTO> it = servicioJockeys.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			JockeyDTO jockeyDTO = (JockeyDTO) it.next();
			assertTrue(ids.contains(jockeyDTO.getId()));
		}
		
	}
	
	public void testBuscarPorId() {
		
		try {
			
			Iterator<Long> it = ids.iterator();
			
			while (it.hasNext()) {
				
				Long id = (Long) it.next();
				servicioJockeys.buscarPorId(id);
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
			servicioJockeys.buscarPorId(id);
			fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
		} catch (EntidadInexistenteException e) {
		}
	}

	public void testBorrar() {

		try {
			
			Iterator<Long> it = ids.iterator();
			while (it.hasNext()) {
				Long id = (Long) it.next();

				servicioJockeys.borrar(id);
				servicioJockeys.buscarPorId(id);
				fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
			}
			
		} catch (EntidadInexistenteException e) {
		}

	}

}
