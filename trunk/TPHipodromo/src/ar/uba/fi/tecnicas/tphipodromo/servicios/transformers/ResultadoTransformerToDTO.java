package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

public class ResultadoTransformerToDTO implements Transformer {

	@Override
	public Object transform(Object arg0) {
		Resultado resultado = (Resultado) arg0;
		ResultadoDTO resultadoDTO = new ResultadoDTO(); 
		resultadoDTO.setId(resultado.getId());
		resultadoDTO.setOrdenLlegada(resultado.getOrdenLlegada());
		resultadoDTO.setTiempo(resultado.getTiempo());
		return resultadoDTO;
	}

}
