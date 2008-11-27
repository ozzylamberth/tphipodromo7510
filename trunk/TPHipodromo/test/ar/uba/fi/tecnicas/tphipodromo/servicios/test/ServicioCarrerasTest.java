package ar.uba.fi.tecnicas.tphipodromo.servicios.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioCarrerasImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerToDTO;
import junit.framework.TestCase;

public class ServicioCarrerasTest extends TestCase {

	private ServicioCarrerasImpl servicioCarreras = new ServicioCarrerasImpl();
	private ArrayList<Long> ids = new ArrayList<Long>();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		vaciarBase();

		Carrera carrera1 = new Carrera();
		carrera1.setDistancia(new BigDecimal(100));
		carrera1.setFechaYHora(new Date());
		carrera1.setNombre("Carrera 1");
		carrera1.setNumero(1);		
	
		Carrera carrera2 = new Carrera();
		carrera2.setDistancia(new BigDecimal(400));
		carrera2.setFechaYHora(new Date());
		carrera2.setNombre("Carrera 2");
		carrera2.setNumero(2);
		
		CarreraTransformerToDTO caballoTransformerToDTO = new CarreraTransformerToDTO();
		
		Long id;
		
		id  = servicioCarreras.guardar((CarreraDTO) caballoTransformerToDTO.transform(carrera1));
		ids.add(id);
		
		id = servicioCarreras.guardar((CarreraDTO) caballoTransformerToDTO.transform(carrera2));
		ids.add(id);
		
	}
	
	private void vaciarBase() {
		
		Iterator<CarreraDTO> it = servicioCarreras.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			CarreraDTO carreraDTO = (CarreraDTO) it.next();
			try {
				servicioCarreras.borrar(carreraDTO.getId());
			} catch (EntidadInexistenteException e) {
			}
		}
	}
	
	public void testBuscarTodos() {
		
		Iterator<CarreraDTO> it = servicioCarreras.buscarTodos().iterator();
		
		while (it.hasNext()) {
			
			CarreraDTO carreraDTO = (CarreraDTO) it.next();
			assertTrue(ids.contains(carreraDTO.getId()));
		}
		
	}
	
	public void testBuscarPorId() {
		
		try {
			
			Iterator<Long> it = ids.iterator();
			
			while (it.hasNext()) {
				
				Long id = (Long) it.next();
				servicioCarreras.buscarPorId(id);
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
			servicioCarreras.buscarPorId(id);
			fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
		} catch (EntidadInexistenteException e) {
		}
	}

	public void testBorrar() {

		try {
			
			Iterator<Long> it = ids.iterator();
			while (it.hasNext()) {
				Long id = (Long) it.next();

				servicioCarreras.borrar(id);
				servicioCarreras.buscarPorId(id);
				fail("El método buscarPorId debería haber lanzado la excepción EntidadInexistenteException");
			}
			
		} catch (EntidadInexistenteException e) {
		}

	}
}
