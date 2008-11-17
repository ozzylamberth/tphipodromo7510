package ar.com.jgrande.client.controlador.eventos;

import ar.com.jgrande.client.vista.PantallaPrincipal;

public enum EventoPrincipal implements Evento<PantallaPrincipal>  {
	
	MOSTRAR{ public void resolver(PantallaPrincipal v, Object[] args) { v.onMostrar(); } }
	
}
