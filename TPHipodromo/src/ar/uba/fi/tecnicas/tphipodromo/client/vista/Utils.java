package ar.uba.fi.tecnicas.tphipodromo.client.vista;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;


public class Utils {
	
	public static String FORMATO_ARG = "dd/MM/yyyy";
	public static String FORMATO_HORA_24 = "HH:mm";
	public static String FORMATO_ARG_HORA = "dd/MM/yyyy HH:mm";

	public static String getFechaFormateada(Date fecha, String formato) {
		return DateTimeFormat.getFormat(formato).format(fecha);
	}
	
	public static Date parsearFecha(String fecha, String formato) {
		return DateTimeFormat.getFormat(formato).parseStrict(fecha);
	}
	
}
