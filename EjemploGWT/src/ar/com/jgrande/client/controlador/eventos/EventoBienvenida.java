package ar.com.jgrande.client.controlador.eventos;

import ar.com.jgrande.client.controlador.ControladorBienvenida;
import ar.com.jgrande.client.vista.VistaBienvenida;

/**
 * Eventos lanzados por el controlador <code>ControladorBienvenida</code>.
 * 
 * @see ControladorBienvenida
 * @author Juan
 *
 */
public enum EventoBienvenida implements Evento<VistaBienvenida> {
	
	/** @see VistaBienvenida.onBienvenida */
	BIENVENIDA { public void resolver(VistaBienvenida v, Object[] args) { v.onBienvenida(); } },

}
